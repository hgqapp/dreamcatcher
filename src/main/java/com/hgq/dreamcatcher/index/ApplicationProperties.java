package com.hgq.dreamcatcher.index;

public class ApplicationProperties {


    private String host;
    private String password;

    private BaiDu baiDu = new BaiDu();



    public static class BaiDu {
        private String apiKey;
        private String secretKey;

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
