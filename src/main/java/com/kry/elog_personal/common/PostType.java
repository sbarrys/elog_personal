package com.kry.elog_personal.common;

public enum PostType {
    NOTICE(1),
    NORMAL(2);

    public final int value;

    PostType(int value) {this.value = value;}
}
