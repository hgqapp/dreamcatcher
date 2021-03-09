package com.hgq.dreamcatcher.study;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class AutoStudy {

    static {
        Unirest.setDefaultHeader("Accept", "*/*");
        Unirest.setDefaultHeader("Accept-Language", "zh-CN,zh;q=0.9");
        Unirest.setDefaultHeader("Host", "gz.learn.lawyerpass.com");
        Unirest.setDefaultHeader("Origin", "http://gz.learn.lawyerpass.com");
        Unirest.setDefaultHeader("Referer", "http://gz.learn.lawyerpass.com/service/rest/dm.MVC/tms.Course@mvc-player/execute?entityId=7bdab93874e94414b1850aaf3775925e");
        Unirest.setDefaultHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.182 Safari/537.36 Edg/88.0.705.74");
    }

    private final String cookie;
    private final String csrftoken;

    public AutoStudy(String cookie, String csrftoken) {
        this.cookie = cookie;
        this.csrftoken = csrftoken;
    }

    public void start() throws UnirestException {
        for (int i = 1; i < 5; i++) {
            List<String> courseIds = getCourseIds(i);

            for (String courseId : courseIds) {
                System.out.println();
                System.out.println("===========================================================================================");
                HttpResponse<String> response = Unirest.get("http://gz.learn.lawyerpass.com/service/rest/dm.MVC/tms.Course@mvc-detail/execute")
                        .header("cookie", cookie)
                        .queryString("entityId", courseId)
                        .asString();
                Document document = Jsoup.parse(response.getBody());
                System.out.println("课程名称：" + document.getElementsByClass("about-title").text());
                String startbtn = document.getElementsByClass("startbtn").text();
                System.out.print("课程状态：");
                if (startbtn.contains("选修并学习")) {
                    System.out.println("还未选修");
                } else {
                    System.out.println("已选修");
                }
                System.out.println("课程信息：");
                Elements list = document.getElementsByClass("list-group-item");

                for (Element element : list) {
                    System.out.println("    " + element.text());;
                }
                Number status = 0;
                try {
                    status = NumberFormat.getPercentInstance().parse(list.first().getElementsByTag("strong").text());
                } catch (ParseException e) {
                }
                if (status.intValue() == 1) {
                    System.out.println("课程已经学习完成，将会自动忽略该课程！");
                    continue;
                }

                System.out.println("课件列表：");
                Elements mediaBody = document.getElementsByClass("media-body");
                for (Element mediaItem : mediaBody) {
                    Elements children = mediaItem.children();
                    Element first = children.first();
                    String href = first.attr("href");

                    String entityId = href.substring(href.indexOf("entityId") + 9, href.indexOf("&"));
                    String chapter = href.substring(href.indexOf("chapter")+8);
                    Element last = children.last();
                    System.out.println("    课件名称: " + first.text() + " entityId:" + entityId + " chapterId: " + chapter+ " " + last.text());
                    int chapterSeconds = Integer.parseInt(last.text().split(" ")[1]) * 60 + 60;
                    doStudy(entityId, chapter, chapterSeconds);
                }
            }
        }
    }

    private void doStudy(String entityId, String chapterId, int chapterSeconds) throws UnirestException {
        System.out.println("        ==>> 开始学习中（时长：" + getDate(chapterSeconds) + "）...");

        HttpResponse<String> res = Unirest.get("http://gz.learn.lawyerpass.com/service/rest/dm.MVC/tms.Course@mvc-player/execute")
                .header("cookie", cookie)
                .queryString("entityId", entityId)
                .queryString("chapter", chapterId)
                .asString();
        Document document = Jsoup.parse(res.getBody());
        String taskId = null;
        String wareId = null;
        Integer initTime = 0;
        String script = document.getElementsByTag("script").not("[src]").last().data();
        String[] split = script.split("[;\\s\\=]");
        split = Arrays.stream(split).filter(v -> v.length() > 0).toArray(String[]::new);
        for (int i = 0; i < split.length; i++) {
            if (split[i].equals("taskId")) {
                taskId = split[i + 1].replace("'", "");
            } else if (split[i].equals("wareId")) {
                wareId = split[i + 1].replace("'", "");
            } else if (split[i].equals("initTime")) {
                initTime = Double.valueOf(split[i + 1]).intValue();
            }
        }

        System.out.println("        ==>> 学习进度：" + getDate(initTime) + " taskId: " + taskId);

        JSONObject body = new JSONObject();
        body.put("taskId", taskId);
        body.put("wareId", wareId);
        body.put("currentTime", initTime);
        body.put("override", false);

        HttpResponse<JsonNode> response = Unirest.post("http://gz.learn.lawyerpass.com/service/rest/dm.DataService/tms.Log@studentLog/invoke?_csrftoken=264cd560-e39a-4555-9e8b-42b5ed552b76")
                .header("content-type", "application/json")
                .header("cookie", cookie)
                .queryString("_csrftoken", csrftoken)
                .body(body).asJson();
        String logId = response.getBody().getObject().getString("result");
        body.put("logId", logId);
        System.out.println("        ==>> 获取到日志ID：" + logId);
        int left = (chapterSeconds - initTime);
        while (left > 0) {
            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
            }
            initTime+=30;
            body.put("currentTime", initTime);
            HttpResponse<JsonNode> result = Unirest.post("http://gz.learn.lawyerpass.com/service/rest/dm.DataService/tms.Log@studentLog/invoke?_csrftoken=264cd560-e39a-4555-9e8b-42b5ed552b76")
                    .header("content-type", "application/json")
                    .header("cookie", cookie)
                    .queryString("_csrftoken", csrftoken)
                    .body(body).asJson();
            System.out.println("        ==>> 学习进度：" + getDate(initTime));
            left = (chapterSeconds - initTime);
        }
    }
    public static String getDate(Integer date ) {
        if (date<60) {
            return date+"秒";
        }else if (date>60&&date<3600) {
            int m = date/60;
            int s = date%60;
            return m+"分"+s+"秒";
        }else {
            int h = date/3600;
            int m = (date%3600)/60;
            int s = (date%3600)%60;
            return h+"小时"+m+"分"+s+"秒";
        }
    }

    public List<String> getCourseIds(int page) throws UnirestException {
        HttpResponse<String> response = Unirest.get("http://gz.learn.lawyerpass.com/service/rest/dm.MVC/tms.Course@mvc-select/execute?active=14c2b72ee1aa903d75e76db51157209b")
                .header("cookie", cookie)
                .queryString("page", page)
                .asString();
        Document document = Jsoup.parse(response.getBody());
        Elements elements = document.getElementsByAttributeValue("type", "checkbox");
        return elements.stream().map(v -> v.attr("data-id")).collect(Collectors.toList());
    }

    public static void main(String[] args) throws UnirestException {
        AutoStudy study = new AutoStudy(
                "JSESSIONID=14CDA51C3C1FB3E269C9F233C5D59CF3; qimo_seosource_5c794df0-6a90-11ea-9d1b-45a6baa6494a=其他网站; qimo_seokeywords_5c794df0-6a90-11ea-9d1b-45a6baa6494a=未知; qimo_xstKeywords_5c794df0-6a90-11ea-9d1b-45a6baa6494a=; href=http://gz.learn.lawyerpass.com/; accessId=5c794df0-6a90-11ea-9d1b-45a6baa6494a; Hm_lvt_4a4d458c50b10ea42830d077410c9553=1615252234; bad_id5c794df0-6a90-11ea-9d1b-45a6baa6494a=3ef41f81-8074-11eb-ba77-6b7179278297; nice_id5c794df0-6a90-11ea-9d1b-45a6baa6494a=3ef41f82-8074-11eb-ba77-6b7179278297; Hm_lpvt_4a4d458c50b10ea42830d077410c9553=1615253845; pageViewNum=86",
                "917b69eb-a866-4900-b917-96764623b0a3");
        study.start();
    }
}
