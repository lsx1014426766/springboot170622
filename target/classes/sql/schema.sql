-- 数据库初始化脚本
CREATE DATABASE springtest;
USE springtest;

CREATE TABLE user(
 `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户id',
`name` VARCHAR(100) NOT NULL COMMENT '用户名称',
 `phone` INT NOT NULL COMMENT '用户手机号',
`email` VARCHAR(50) NOT NULL COMMENT '用户邮箱',
 `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '记录更新时间',
 `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
 PRIMARY KEY(id),
 KEY idx_phone(phone),
 KEY idx_update_time(update_time),
 KEY idx_create_time(create_time)
)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='用户信息表'

