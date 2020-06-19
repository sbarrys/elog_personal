package com.kry.elog_personal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ElogPersonalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElogPersonalApplication.class, args);
    }
}
