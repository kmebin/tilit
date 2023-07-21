package com.programmers.tilit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TilitApplication {
    public static void main(String[] args) {
        SpringApplication.run(TilitApplication.class, args);
    }
}
