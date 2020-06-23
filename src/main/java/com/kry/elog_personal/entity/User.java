package com.kry.elog_personal.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

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
}
