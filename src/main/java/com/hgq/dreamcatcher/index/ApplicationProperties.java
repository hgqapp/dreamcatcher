package com.hgq.dreamcatcher.index;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

    private String email = "node1595479727493@qq.com";
    private String host = "https://xbsj8789.site";
    private String password = "qq123456";
    private BaiDuApiProperties baiDuApi = new BaiDuApiProperties();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BaiDuApiProperties getBaiDuApi() {
        return baiDuApi;
    }

    public void setBaiDuApi(BaiDuApiProperties baiDuApi) {
        this.baiDuApi = baiDuApi;
    }

    public static class BaiDuApiProperties {
        private String apiKey = "VSDyAn8zeZsRhtVFnSA69IVU";
        private String secretKey = "dAIhvP1feyKg797x4R9K96S2ivZsK4MK";

        public String getApiKey() {
            return apiKey;
        }

        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }
    }


}
