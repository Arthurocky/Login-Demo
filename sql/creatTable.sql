create database if not exists login;

use login;

-- 用户表
create table user
(
    username     varchar(256) null comment '用户昵称',
    id           bigint auto_increment comment '用户id' primary key,
    userAccount  varchar(256) null comment '用户账号',
    avatarUrl    varchar(1024) null comment '用户头像',
    gender       tinyint null comment '用户性别',
    userPassword varchar(512)       not null comment '用户密码',
    phone        varchar(128) null comment '用户电话',
    email        varchar(512) null comment '用户邮箱',
    userStatus   int      default 0 not null comment '用户状态 0 - 正常',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    isDelete     tinyint  default 0 not null comment '是否删除',
    userRole     int      default 0 not null comment '用户角色 0 - 普通用户 1 - 管理员',
    userCode     varchar(512) null comment '用户编号',
    tags         varchar(1024) null comment '标签 json 列表'
) comment '用户表创建';