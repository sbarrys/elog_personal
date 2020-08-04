package com.kry.elog_personal.config.auth.dto;


import com.kry.elog_personal.common.Role;
import com.kry.elog_personal.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

//세션에 저장할때 직렬화된 객체가 필요한데,
// Entity User객체를 직접 직렬화해주면 안된다.  @OneToMany 등 자식들까지 직렬화가 되어 성능문제가 생길수잇음.
// 따라서 비슷한 모양의 객체를 생성하고 직렬화해준다.
@Getter
@AllArgsConstructor
@Data
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
    public User toEntity() {
        return User.builder()
                .name(this.name)
                .email(this.email)
                .picture(this.picture)
                .role(Role.USER)
                .build();
    }

}