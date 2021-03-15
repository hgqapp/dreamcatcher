package com.hgq.dreamcatcher.music;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URI;

public class MusicDownload {

    public static final String url = "http://music.onlychen.cn/";

    public static void main(String[] args) throws UnirestException {
        String input = "BEYOND,金莎,范玮琪,蔡健雅,宋东野,刘德华,张国荣,逃跑计划,水木年华,王菲,张信哲,程响,猛然";
        for (String item : input.split(",")) {
            try {
                int page = 10;
                System.out.println(getJsonNode(item, page));
                page++;
                for (int i = 1; i < page; i++) {
                    download(item,i);
                }
            } catch (UnirestException e) {
                e.printStackTrace();
            }
        }
    }

    private static void download(String input, int page) throws UnirestException {
        System.out.println("第" + page + "页");
        String target = "E:\\Music\\" + input + "\\";
        File dir = new File(target);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        JsonNode body = getJsonNode(input, page);
        JSONObject root = body.getObject();
        if (root.has("code")) {
            if (root.getInt("code") == 404) {
                return;
            }
        }
        JSONArray data = root.getJSONArray("data");
        System.out.println(data);
        System.out.println("共" + data.length() + "首");
        for (int i = 0; i < data.length(); i++) {
            JSONObject obj = data.getJSONObject(i);
            String author = obj.getString("author");
            String title = obj.getString("title");
            Object u = obj.get("url");
            if (!(u instanceof String)) {
                continue;
            }
            int index = (page - 1) * 10 + i + 1;
            String url = obj.getString("url");
            String lrc = obj.getString("lrc");
            String pic = obj.getString("pic");
            URI uri = URI.create("http://122.226.161.16/amobile.music.tc.qq.com/M500002ExFMX2Jt6gv.mp3?guid=8187471&vkey=5207359AD664EA77C501B531E202AE88AAC32F0F563F1B6A6C072A3B2E7FF94C634E95C87F489C05903891CA5192EE555B03A4B9C3C934F5&uin=2626&fromtag=66");
            String filenameExtension = StringUtils.getFilenameExtension(uri.getPath());
            HttpResponse<InputStream> inputStreamHttpResponse = Unirest.get(url).asBinary();
            InputStream inputBody = inputStreamHttpResponse.getBody();
            try (OutputStream outputStream = new FileOutputStream(new File(dir, index + "-" + author + "-" + title + "." + filenameExtension))){
                FileCopyUtils.copy(inputBody, outputStream);
                FileCopyUtils.copy(lrc, new FileWriter(new File(dir, index + "-" + author + "-" + title + ".lrc")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(body);
    }

    private static JsonNode getJsonNode(String input, int page) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.post(url)
                .header("Accept", "application/json, q=0.01")
                .header("Host", "music.onlychen.cn")
                .header("Origin", "http://music.onlychen.cn")
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("X-Requested-With", "XMLHttpRequest")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.83 Safari/537.36 Edg/85.0.564.44")
                .field("input", input)
                .field("filter", "name")
                .field("type", "qq")
                .field("page", page)
                .asJson();
        return response.getBody();
    }
}
