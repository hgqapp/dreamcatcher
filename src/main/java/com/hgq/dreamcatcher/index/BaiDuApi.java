package com.hgq.dreamcatcher.index;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

@Component
public class BaiDuApi {

    private String accessToken;
    private static final Logger logger = LoggerFactory.getLogger(BaiDuApi.class);
    private ApplicationProperties properties;

    public BaiDuApi(ApplicationProperties properties) {
        this.properties = properties;
    }

    @Scheduled(fixedDelay = 864000000L, initialDelay = 864000000L)
    public void refreshAccessToken() throws UnirestException {
        logger.info("Refresh BaiDu access token");
        ApplicationProperties.BaiDuApiProperties baiDuApiProperties = properties.getBaiDuApi();
        HttpResponse<JsonNode> response = Unirest.post("https://aip.baidubce.com/oauth/2.0/token")
                .field("grant_type", "client_credentials")
                .field("client_id", baiDuApiProperties.getApiKey())
                .field("client_secret", baiDuApiProperties.getSecretKey())
                .asJson();
        JsonNode body = response.getBody();
        logger.info("BaiDu Token : {}", body);
        JSONObject object = body.getObject();
        if (object.has("access_token")) {
            accessToken = object.getString("access_token");
        } else {
            throw new IllegalArgumentException("BaiDu Token Failed: " + body);
        }
    }


    public String doOCR(BufferedImage image) throws Exception {
        while (accessToken == null) {
            refreshAccessToken();
        }
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", output);
        String img = Base64.getMimeEncoder().encodeToString(output.toByteArray());
        final HttpResponse<JsonNode> response = Unirest
                .post("https://aip.baidubce.com/rest/2.0/ocr/v1/webimage")
                .queryString("access_token", accessToken)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .field("image", img)
                .field("language_type", "ENG")
                .asJson();
        JsonNode body = response.getBody();
        logger.info("BaiDu OCR Result：{}", body);
        JSONArray wordsResult = body.getObject().getJSONArray("words_result");
        if (wordsResult.length() == 0) {
            return null;
        }
        return wordsResult.getJSONObject(0).getString("words");
    }
}
