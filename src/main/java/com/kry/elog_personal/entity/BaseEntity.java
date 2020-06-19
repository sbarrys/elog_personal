package com.kry.elog_personal.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 이 클래스를 상속받는 클래스들은 아래의 필드들을 column에 가지게 된다.
@EntityListeners(AuditingEntityListener.class) // @CreatedDate 등과 같은 어노테이션으로 필드값이 자동으로 관리된다.
public abstract class BaseEntity {
    @CreatedDate
    private LocalDateTime createdTime;
    @LastModifiedDate
    private LocalDateTime modifiedTime;
}
