package com.kry.elog_personal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kry.elog_personal.common.PostType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;

//null을 입력시 null이 들어가지 않고 default 로 들어가게 해준다.
@DynamicInsert
@DynamicUpdate
@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//기본 키 생성을 데이터베이스에 위임한다
    private Long id;
    private String title;
    @Column( columnDefinition = "TEXT")
    private String content;
    @Column(name="post_type")
    private PostType postType;
    //방문자 개수
    @Column(name="cnt_visitor",columnDefinition = "BIGINT default 0" )
    private Long cntVisitor;
    //누적 방문자 개수
    @Column(name="cnt_accu_visitor",columnDefinition = "BIGINT default 0" )
    private Long cntAccuVisitor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name="user_id",nullable = false,updatable=false)
    private User user=new User();

}
