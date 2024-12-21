package com.jujuu.ourlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OurlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(OurlogApplication.class, args);
    }

}
