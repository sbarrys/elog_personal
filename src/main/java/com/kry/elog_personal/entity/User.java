package com.kry.elog_personal.entity;

import com.kry.elog_personal.common.Role;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class User {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (length = 10, nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String picture;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    @Builder
    public User(Long id, String name, String password, String email, String picture,Role role) {
        Assert.hasText(name, "name must not be empty");
        this.id= id;
        this.name = name;
//        this.password = password;
        this.email= email;
        this.picture= picture;
        this.role= role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }
    public String getRoleKey() {
        return this.role.getKey();
    }

}
