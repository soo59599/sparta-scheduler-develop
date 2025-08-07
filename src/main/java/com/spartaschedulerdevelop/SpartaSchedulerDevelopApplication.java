package com.spartaschedulerdevelop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpartaSchedulerDevelopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpartaSchedulerDevelopApplication.class, args);
    }

}
