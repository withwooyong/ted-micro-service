package com.ted.micro.review;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication
@ComponentScan("com.ted.micro")
public class ReviewApplication {

    public static void main(String[] args) {
//        SpringApplication.run(ReviewApplication.class, args);
        ConfigurableApplicationContext ctx = SpringApplication.run(ReviewApplication.class, args);
        String mysqlUri = ctx.getEnvironment().getProperty("spring.datasource.url");
        log.info("Connected to MySQL: " + mysqlUri);

    }
}
