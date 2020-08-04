package com.kry.elog_personal;

import com.kry.elog_personal.entity.Board;
import com.kry.elog_personal.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@EnableJpaAuditing
@SpringBootApplication
public class ElogPersonalApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElogPersonalApplication.class, args);
    }


}
