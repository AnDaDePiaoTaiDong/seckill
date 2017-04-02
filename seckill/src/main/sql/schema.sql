--数据库初始化脚本

CREATE DATABASE seckill;

use seckill;

CREATE TABLE  seckill(
seckill_id  BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
name VARCHAR(120) NOT NULL COMMENT '商品名称',
number INT NOT NULL COMMENT '库存',
start_time timestamp NOT NULL COMMENT '开始时间',
end_time timestamp NOT NULL COMMENT '结束时间',
create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY(seckill_id),
key idx_start_time (start_time),
key idx_end_time (end_time),
key idx_create_time (create_time)
)ENGINE=InnoDB AUTO_INCREMENT 1000 DEFAULT CHARSET=utf8 COMMENT '秒杀库存表';

--初始化数据
INSERT INTO
    seckill(name,number,start_time,end_time)
VALUES
    ('5元秒杀苹果',1000,'2017-3-25 00:00:00','2017-3-27 00:00:00'),
    ('2元秒杀苹果',1000,'2017-3-25 00:00:00','2017-3-27 00:00:00'),
    ('3元秒杀苹果',1000,'2017-3-25 00:00:00','2017-3-27 00:00:00'),
    ('1元秒杀苹果',1000,'2017-3-25 00:00:00','2017-3-27 00:00:00');

DELETE FROM seckill where seckill_id=1008 or seckill_id=1009 or seckill_id=1010 or seckill_id=1011;


--秒杀成功明细表
CREATE TABLE success_killed(
    seckill_id BIGINT NOT NULL COMMENT '秒杀商品id',
    user_phone BIGINT NOT NULL COMMENT '用户手机号',
    state TINYINT NOT NULL DEFAULT -1 COMMENT '-1:无效 0：成功 1：已付款 2：已收货'，
    create_time timestamp NOT NULL COMMENT '创建时间'，
    PRIMARY KEY (seckill_id,user_phone), /*联合主键*/
    key idx_create_time(create_time)
)ENGINE=Inn0DB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';

ALTER TABLE seckill
DROP index idx_create_time,
ADD index idx_c_t(create_time);

