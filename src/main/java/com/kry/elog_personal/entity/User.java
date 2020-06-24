package com.kry.elog_personal.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (length = 10, nullable = false)
    private String name;
    @Column (length = 20, nullable = false)
    private String password;

    @Builder
    public User(Long id,String name, String password) {
        Assert.hasText(name, "name must not be empty");
        this.id= id;
        this.name = name;
        this.password = password;
    }
}
