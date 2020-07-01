
CREATE TABLE user(
    `id` BIGINT(20) UNSIGNED NOT NULL  AUTO_INCREMENT
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

INSERT INTO USER(name,email) values ('kim tae yun','sbarrys@ajou.ac.kr') , ('hong','hong@google.com');

INSERT INTO BOARD(title,content,post_Type,user_id,cnt_accu_visitor,cnt_visitor) values('hello','content',1,1,0,0);
