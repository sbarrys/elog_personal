package com.kry.elog_personal.api;

import com.kry.elog_personal.config.auth.dto.SessionUser;
import com.kry.elog_personal.entity.User;
import com.kry.elog_personal.repositories.UserRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserRepository userRepository;
    HttpSession httpSession;
    @GetMapping("/saveOrUpdateUser")
    public Object login(@RequestHeader("access_token") String access_token) {

        //1. header에 있는 access_token받기.
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer"+access_token);
        HttpEntity entity = new HttpEntity("parameters", headers);


        //2. access_token이용해서 유저정보 받아오기.
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity response = restTemplate.exchange(
                "https://www.googleapis.com/oauth2/v3/userinfo", HttpMethod.GET, entity, String.class);
        User user = new User();

        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody().toString());

            user.setName(jsonObject.get("name").toString());
            user.setEmail(jsonObject.get("email").toString());
            user.setPicture(jsonObject.get("picture").toString());
            user.setSub(Long.parseLong(( jsonObject.get("sub").toString()));
        } catch (ParseException e) {

        }

        SessionUser sessionUser = new SessionUser(user);

        return sessionUser;


    }

}
//        SessionUser sessionUser= new SessionUser();
        //3. 유저정보 받아와서 존재하는 회원인지 확인하기 = >  update or save
//        User user = userRepository.findByEmail(sessionUser.getEmail())
//                .map(entity -> entity.update(sessionUser.getName(),sessionUser.getPicture()))
//                .orElse(sessionUser.toEntity());
//        return ResponseEntity.ok().body(user);
//        //4. 유저정보와함께 client로 반환   >> 클라이언트는 이를 받아서 데이터바인딩딩

