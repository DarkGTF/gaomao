-- 创建用户信息表
drop table if EXISTS tb_user_info;
create table if not EXISTS tb_user_info (
    user_id int(32)  not NULL COMMENT 'Id',
    username varchar(128)  not NULL COMMENT 'Id',
    profile_img int(32) not null COMMENT '头像图片id',
    email varchar(128) not null COMMENT '邮箱',
    gender varchar(2) not null COMMENT '性別, 0:女,1:男',
    enable_status  varchar(2) not null comment '当前用户状态,0:禁用,1允许使用',
    user_type varchar(2) not null comment '用户类型: 0,游客,1: 超级管理员，2:前端开发者,3:后端开发者,4:DBA',
    create_time datetime not null comment '创建时间',
    create_user int(32) DEFAULT NULL comment '创建者',
    last_edit_time datetime  default null comment  '最后修改时间',
    deleted boolean DEFAULT 0 COMMENT '逻辑删除标志:0,未删除，1已删除',
    PRIMARY key (`user_id`),
    UNIQUE index U_pk_name (`username`)  using BTREE
) engine = INNODB;

-- 2019-12-24