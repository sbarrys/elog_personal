package com.kry.elog_personal.api;

import com.kry.elog_personal.config.auth.dto.OAuthAttributes;
import com.kry.elog_personal.config.auth.dto.SessionUser;
import com.kry.elog_personal.entity.User;
import com.kry.elog_personal.repositories.UserRepository;
import lombok.Builder;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    UserRepository userRepository;


    @GetMapping("/saveOrUpdateUser")
    public ResponseEntity<?> login(@RequestHeader("access_token") String access_token) {
        //1. header에 있는 access_token받기.
        HttpHeaders headers = new HttpHeaders();


        headers.set("Authorization", "Bearer "+access_token);

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);


        //2. access_token이용해서 유저정보 받아오기.
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                "https://www.googleapis.com/oauth2/v3/userinfo", HttpMethod.GET,entity, String.class);
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody().toString());

            String name=((String)jsonObject.get("name"));
            String email=((String)jsonObject.get("email"));
            String picture=((String)jsonObject.get("picture"));
            SessionUser sessionUser = new SessionUser(name,email,picture);
            User user =saveOrUpdate(sessionUser);

            headers.remove("Authorization");
            HttpHeaders headers1 = new HttpHeaders();
            headers1.set("Access-Control-Allow-Origin","*");
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(sessionUser);

        } catch (ParseException e) {
            return ResponseEntity.status(200).body(e);
        }

    }

    private User saveOrUpdate(SessionUser sessionUser) {
        User user = userRepository.findByEmail(sessionUser.getEmail())
                .map(entity -> entity.update(sessionUser.getName(), sessionUser.getPicture()))
                .orElse(sessionUser.toEntity());

        return userRepository.save(user);
    }


}