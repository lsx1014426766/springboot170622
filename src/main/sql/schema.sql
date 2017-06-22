-- 数据库初始化脚本
CREATE DATABASE seckill;
USE seckill;

CREATE TABLE seckill(
 `seckill_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
`name` VARCHAR(120) NOT NULL COMMENT '商品名称',
 `number` INT NOT NULL COMMENT '库存数量',
`start_time` TIMESTAMP NOT NULL COMMENT '秒杀开始时间',
 `end_time` TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
 `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 PRIMARY KEY(seckill_id),
 KEY idx_start_time(start_time),
 KEY idx_end_time(end_time),
 KEY idx_create_time(create_time)
)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表'

--初始化数据
INSERT INTO seckill(NAME,number,start_time,end_time)
VALUES
 ('1000元iphone6',100,'2015-11-01 00:00:00','2015-11:02 00:00:00'),
  ('500元ipad2',200,'2015-11-01 00:00:00','2015-11:02 00:00:00'),
   ('300元小米5',300,'2015-11-01 00:00:00','2015-11:02 00:00:00'),
    ('200元红米2',400,'2015-11-01 00:00:00','2015-11:02 00:00:00'),
--秒杀成功明细表
    --用户登录认证相关的信息
    CREATE TABLE success_killed(
   `seckill_id` BIGINT NOT NULL COMMENT '秒杀商品id',
   `user_phone` BIGINT NOT NULL COMMENT '用户手机号',
    `state` TINYINT NOT NULL DEFAULT -1 COMMENT '-1无效 0成功1已付款',
   `create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY(`seckill_id`,`user_phone`),
    KEY idx_create_time(create_time)
    )ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表'
    
    --连接数据库控制台
    mysql -uroot -proot
    SHOW TABLES;
    SHOW CREATE TABLE `seckill`\G
    
    --DDL
    ALTER TABLE seckill
    DROP INDEX idx_create_time,
    ADD index_idx_c_s(start_time,create_time);