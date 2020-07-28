package com.kry.elog_personal.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
@RestController
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("/home")
    public ResponseEntity login(){

        String loginURI = "http://localhost:8080/oauth2/authorization/google";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccessControlAllowOrigin("*");
        httpHeaders.setLocation(URI.create(loginURI));
        return new ResponseEntity<URI>(URI.create(loginURI),httpHeaders, HttpStatus.OK);

    }
}
