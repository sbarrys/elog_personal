package com.kry.elog_personal.api;

import com.kry.elog_personal.config.auth.dto.SessionUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthController {
    HttpSession httpSession;

}
