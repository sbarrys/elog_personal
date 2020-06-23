
CREATE TABLE user(
    `id` BIGINT(20) UNSIGNED NOT NULL  AUTO_INCREMENT,
    `name` VARCHAR(10) NOT NULL ,
    `password` VARCHAR(20) NOT NULL,
    `email` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `user_index` (email)

)ENGINE = InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE board(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `title` TEXT NOT NULL,
    `content` TEXT NOT NULL,
    `post_type` TINYINT NOT NULL,
    `cnt_accu_visitor` BIGINT(20) default 0,
    `cnt_visitor` BIGINT(20) default 0,
    `user_id` BIGINT(20) NOT NULL,
    `modified_time`datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     PRIMARY KEY (`id`),
    INDEX `board_index` (created_time)
)ENGINE = InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO USER(name,password,email) values ('kty','1234','kty@naver.com') , ('hong','3333','hong@google.com');

INSERT INTO BOARD(title,content,post_Type,user_id,cnt_accu_visitor,cnt_visitor) values('hello','content',1,1,0,0);
