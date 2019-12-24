-- /** 创建用户权限表 */
drop table if exists tb_user_auth ;
create table if NOT EXISTS  tb_user_auth  (
	user_auth_id int(32)  not NULL COMMENT 'Id',
	user_id int(32) not null COMMENT '用户id',
	username varchar(128) not NULL COMMENT '用户姓名',
	password varchar(128) not null COMMENT '密码',
	auth_type varchar(32) not NULL COMMENT '授权类型',
	create_time datetime DEFAULT NULL comment '创建时间',
	create_user int(32) DEFAULT NULL COMMENT '创建人',
	last_edit_time datetime default NULL COMMENT '最后编辑时间',
	deleted boolean  DEFAULT 0 COMMENT '逻辑删除标志',
	PRIMARY KEY(`user_auth_id`),
	UNIQUE INDEX Upk_user_id (`user_id`) USING BTREE,
	UNIQUE INDEX Upk_user_name (`username`) USING BTREE
) ENGINE = INNODB;
-- /** 2019-12-24 */