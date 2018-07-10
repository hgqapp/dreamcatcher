package com.hgq.dreamcatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DreamcatcherApplication {

    public static void main(String[] args) {
        SpringApplication.run(DreamcatcherApplication.class, args);
    }
}
