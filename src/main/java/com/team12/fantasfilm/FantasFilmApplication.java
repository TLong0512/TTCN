package com.team12.fantasfilm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class })
public class FantasFilmApplication{

    public static void main(String[] args) {
        SpringApplication.run(FantasFilmApplication.class, args);
    }

}
