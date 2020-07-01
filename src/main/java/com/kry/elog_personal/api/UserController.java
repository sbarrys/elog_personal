package com.kry.elog_personal.api;

import com.kry.elog_personal.entity.User;
import com.kry.elog_personal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
        public List<User> findAll(){
            return userRepository.findAll();

        }
}
