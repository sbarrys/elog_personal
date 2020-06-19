package com.kry.elog_personal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kry.elog_personal.common.PostType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity(name ="Board")
@AllArgsConstructor
@NoArgsConstructor
public class Board extends BaseEntity{
    @Id
    @Column(name="board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)//기본 키 생성을 데이터베이스에 위임한다
    private Long id;

    private String title;

    private String content;

    private PostType postType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


    //방문자 개수
    @Column(columnDefinition = "BIGINT default 0" )
    private Long cntVisitor;


    //누적 방문자 개수
    @Column(columnDefinition = "BIGINT default 0" )
    private Long cntAccuVisitor;



}
