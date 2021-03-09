package com.hgq.dreamcatcher;

import com.hgq.dreamcatcher.index.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableConfigurationProperties(ApplicationProperties.class)
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DreamcatcherApplication {

    public static void main(String[] args) {
        SpringApplication.run(DreamcatcherApplication.class, args);
    }
}
