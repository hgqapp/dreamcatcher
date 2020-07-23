package com.hgq.dreamcatcher.index;

import com.google.common.collect.ImmutableMap;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class NodeFetcher {

    private static final Logger logger = LoggerFactory.getLogger(NodeFetcher.class);
    private int r = 115;
    private int g = 115;
    private int b = 120;
    private int x = 70;
    private ApplicationProperties properties;
    private final static Map<String, String> TYPE = ImmutableMap.of(
            "VMESS", "subscribe_link:",
            "TROJAN", "subscribe_link:",
            "SHADOWSOCKS", "subscribe_link:",
            "ALL", "subscribe_link:",
            "QT5", "subscribe_link_trojan_qt5:"
    );

    private String email;
    private BaiDuApi baiDuApi;

    public NodeFetcher(ApplicationProperties properties, BaiDuApi baiDuApi) {
        this.properties = properties;
        this.email = properties.getEmail();
        this.baiDuApi = baiDuApi;
    }

    static {
        Unirest.setDefaultHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
    }

    @Scheduled(fixedDelay = 21000L, initialDelay = 21000L)
    public synchronized void generateEmail() {
        this.email = "node" + System.currentTimeMillis() + "@qq.com";
        logger.info("Generate Email: {}", email);
    }

    public synchronized List<String> nodes(boolean refresh) throws Exception {
        logger.info("Fetch Nodes");
        if (refresh) {
            generateEmail();
        }
        fetch();
        return getNodeList();
    }

    public synchronized String subscribe(String type, boolean refresh) throws Exception {
        logger.info("Subscribe Operation");
        if (refresh) {
            generateEmail();
        }
        fetch();
        String t = type.toUpperCase();
        String typeKey = TYPE.get(t);
        if (typeKey == null) {
            throw new IllegalArgumentException("Not support type: " + type);
        }
        String subscribeLink = getSubscribeLink(typeKey);
        if (!"QT5".equals(t)) {
            subscribeLink += ("?net_type=" + t);
        }
        logger.info("Subscribe Link: {}", subscribeLink);
        String body = Unirest.get(subscribeLink).asString().getBody();
        System.out.println(body);
        return body;
    }

    private void fetch() throws Exception {
        if (email == null) {
            generateEmail();
        }
        Unirest.setHttpClient(HttpClientBuilder.create().build());
        String token = parseCSRFToken();
        final boolean registered = checkIsRegistered(token);
        if (registered) {
            login();
        } else {
            register();
        }
    }

    private void login() throws Exception {
        logger.info("Login by {}", email);
        Unirest.post(properties.getHost() + "/user/account/login")
                .field("email", email)
                .field("password", properties.getPassword())
                .field("agree_terms", 1)
                .field("device_id", "")
                .asString();
    }

    private String getCSRFToken(String initText) throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        engine.eval("var window = {}");
        engine.eval(initText);
        engine.eval("function a(){return G}");
        engine.eval(Js.content);
        Invocable invocable = (Invocable) engine;
        final Object result = invocable.invokeFunction("a");
        ScriptObjectMirror o = (ScriptObjectMirror) result;
        return o.get("csrf_token").toString();
    }

    private String parseCSRFToken() throws Exception {
        final HttpResponse<String> loginBody = Unirest.get(properties.getHost() + "/portal/account/login").asString();
        final Document document = Jsoup.parse(loginBody.getBody());
        final Elements fixedScript = document.getElementsByAttributeValue("data-id", "fixed_script");
        final String data = fixedScript.last().data();
        return getCSRFToken(data);
    }

    private boolean checkIsRegistered(String token) throws Exception {
        logger.info("Check Is Registered");
        final String code = getCode().trim();
        logger.info("Current Token：" + token);
        logger.info("Current Code：" + code);
        HttpResponse<JsonNode> response = Unirest.post(properties.getHost() + "/user/account/check-is-registed")
                .field("email", email)
                .field("csrf_token", token)
                .field("verify_code", code)
                .asJson();
        JSONObject object = response.getBody().getObject();
        int c = object.getInt("code");
        if (c != 0) {
            logger.info("Verification code error [{}]", response.getBody());
            return checkIsRegistered(token);
        }
        final JSONObject data = object.getJSONObject("data");
        return data.getBoolean("is_registed");
    }

    private void register() throws Exception {
        logger.info("Register by {}", email);
        Unirest.post(properties.getHost() + "/user/account/regist")
                .field("email", email)
                .field("password", properties.getPassword())
                .field("inviter_email", "")
                .field("agree_terms", 1)
                .field("device_id", "")
                .field("regist_is_app", 0)
                .asString();
    }

    private String getSubscribeLink(String key) throws Exception {
        HttpResponse<String> response = Unirest.get(properties.getHost() + "/portal/order/node").asString();
        String body = response.getBody();
        body = body.substring(body.indexOf(key));
        String subscribeLink = body.substring(body.indexOf("'")+1);
        subscribeLink = subscribeLink.substring(0, subscribeLink.indexOf("'"));
        return subscribeLink;
    }

    private List<String> getNodeList() throws Exception {
        HttpResponse<String> response = Unirest.get(properties.getHost() + "/portal/order/node").asString();
        String body = response.getBody();
        body = body.substring(body.indexOf("node_data:") + 10);
        body = body.substring(0, body.indexOf("}}") + 2);
        List<String> result = new ArrayList<>();
        JsonNode jsonNode = new JsonNode(body);
        JSONObject object = jsonNode.getObject();
        for (String key : object.keySet()) {
            JSONObject value = (JSONObject) object.get(key);
            if ("FREE".equalsIgnoreCase(value.getString("vip_level"))
                    && "TROJAN".equalsIgnoreCase(value.getString("net_type"))
                    && value.has("quick_url_raw")) {
                String quickUrlRaw = value.getString("quick_url_raw");
                if (quickUrlRaw != null && quickUrlRaw.trim().length() > 0) {
                    result.add(quickUrlRaw);
                }
            }
        }
        if (result.isEmpty()) {
            throw new IllegalArgumentException("没有可用的免费节点");
        }
        return result;
    }

    private String getCode() throws Exception {
        HttpResponse<InputStream> binary = Unirest.get(properties.getHost() + "/portal/account/get-verify-image").asBinary();
        InputStream input = binary.getBody();
        BufferedImage bufferedImage = removeBackground(input);
        String code = baiDuApi.doOCR(bufferedImage);
        if (code == null || code.trim().length() != 4) {
            logger.error("Error Code : {}", code );
            TimeUnit.SECONDS.sleep(1);
            return getCode();
        }
        return code;
    }

    private BufferedImage removeBackground(InputStream input) throws Exception {
        BufferedImage img = ImageIO.read(input);
        int width = img.getWidth();
        int height = img.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (checkColor(img.getRGB(i, j))) {
                    img.setRGB(i, j, Color.WHITE.getRGB());
                }
            }
        }
        return img;
    }

    private boolean checkColor(int rgb) {
        final Color color = new Color(rgb);
        return color.getRed() > (r - x) && color.getRed() < (r + x)
                && color.getGreen() > (g - x) && color.getGreen() < (r + x)
                && color.getBlue() > (b - x) && color.getBlue() < (r + x);

    }
}
