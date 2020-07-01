package com.kry.elog_personal.config.auth;

import com.kry.elog_personal.entity.User;
import lombok.Getter;

import java.io.Serializable;
//왜 User 클래스 사용 안하나요?

//엔티티에는 직렬화 사용 금지
//엔티티는 다른 엔티티와 다른 관계가 생길수도 있으니 새로운 "인증된 사용자 정보"를 담는 클래스를 생성한것
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
