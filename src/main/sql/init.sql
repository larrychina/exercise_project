create database seckill;

use seckill ;


create table seckill(
   seckill_id  bigint not null auto_increment comment '商品库存id',

   goods_name varchar(30) not null comment '商品名称',

   number int not null comment '商品数量',

   start_time timestamp not null comment '秒杀开始时间',

   end_time timestamp not null comment '秒杀结束时间',

   create_time timestamp not null default current_timestamp comment '创建时间',

   primary key(seckill_id),

   key idx_start_time(start_time),

   key idx_end_time(end_time),

   key idx_create_time(create_time)

)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 comment= '秒杀库存表'


insert into seckill(goods_name,number,start_time,end_time) values
	('1000元秒杀iphone7',100,'2017-02-21 00:00:00','2017-02-25 00:00:00'),
	('800元秒杀华为mete9',200,'2017-02-23 00:00:00','2017-02-25 00:00:00'),
	('600元秒杀小米6S',300,'2017-02-24 00:00:00','2017-02-25 00:00:00'),
	('400元秒杀红米6A',400,'2017-02-24 00:00:00','2017-02-25 00:00:00')

create table success_skilled(

    seckill_id  bigint not null comment '秒杀商品id',

    user_phone bigint not null comment '手机号',

    state tinyint not null default -1 comment '秒杀状态提示：-1 无效，0 成功 1 已付款',

    create_time timestamp not null comment '创建时间',

    primary key(seckill_id,user_phone),

    key idx_create_time(create_time)

)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment= '秒杀成功明细表'




