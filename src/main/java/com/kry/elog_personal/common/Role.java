package com.kry.elog_personal.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
//스프링 시큐리티에서는 권한 코드에 항상 ROLE_이 앞에 있어야함.
// 코드별 키 값을 ROLE_GUEST, ROLE_USER 등으로 지정합니다.

@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN("ROLE_ADMIN", "관리자" ),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;

}
