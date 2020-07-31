CREATE TABLE user(
    `id` BIGINT(20) UNSIGNED NOT NULL  AUTO_INCREMENT
    , `sub` BIGINT(25) NOT NULL UNIQUE
    , `name` VARCHAR(20) NOT NULL
    , `email` VARCHAR(30) NOT NULL
    , `role` VARCHAR(10) NOT NULL default 'USER'
    , `picture` VARCHAR(200) NOT NULL default ' '
    , PRIMARY KEY (`id`)
    , INDEX `user_index` (email)
)ENGINE = InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE board(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT
    , `title` TEXT NOT NULL
    , `content` TEXT NOT NULL
    , `post_type` TINYINT NOT NULL default 2
    , `cnt_accu_visitor` BIGINT(20) default 0
    , `cnt_visitor` BIGINT(20) default 0
    , `user_id` BIGINT(20) NOT NULL
    , `modified_time`datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
    , `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    , PRIMARY KEY (`id`)
    , INDEX `board_index` (created_time)
)ENGINE = InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO USER(name,email,sub) values ('kim tae yun','sbarrys@ajou.ac.kr','1112') , ('hong','hong@google.com','1111');

INSERT INTO BOARD(title,content,post_Type,user_id,cnt_accu_visitor,cnt_visitor) values('hello',' 2212222222222222122222222222221222222222222212222222222222122222222222221222222222222212222222222222122222222222221222222222222212222222222222122222222222221222222222222212222222222222122222222222222 ',1,1,0,0);
INSERT INTO BOARD(title,content,post_Type,user_id,cnt_accu_visitor,cnt_visitor) values('222',' 2212222222222222122222222222221222222222222212222222222222122222222222221222222222222212222222222222122222222222221222222222222212222222222222122222222222221222222222222212222222222222122222222222222 ',1,1,0,0);

INSERT INTO BOARD(title,content,post_Type,user_id,cnt_accu_visitor,cnt_visitor) values('333','content3',1,1,0,0);
INSERT INTO BOARD(title,content,post_Type,user_id,cnt_accu_visitor,cnt_visitor) values('444','content4',1,1,0,0);

INSERT INTO BOARD(title,content,post_Type,user_id,cnt_accu_visitor,cnt_visitor) values('555','content5',1,1,0,0);
INSERT INTO BOARD(title,content,post_Type,user_id,cnt_accu_visitor,cnt_visitor) values('666','content6',1,1,0,0);
