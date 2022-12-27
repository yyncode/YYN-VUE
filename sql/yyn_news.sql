drop table if exists news_nav;
create table news_nav
(
    nav_id      bigint(20) not null auto_increment comment '导航id',
    nav_name    varchar(30) default '' comment '导航名称',
    order_num   int(4) default 0 comment '显示顺序',
    create_by   varchar(64) default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64) default '' comment '更新者',
    update_time datetime comment '更新时间',
    primary key (nav_id)
) engine=innodb auto_increment=1 comment = '新闻导航表';


drop table if exists news_content;
create table news_content
(
    news_id          bigint(20) not null auto_increment comment '新闻id',
    news_title       varchar(30)  default '' comment '新闻标题',
    news_description varchar(200) default '' comment '新闻描述',
    news_pic         varchar(500) default '' comment '新闻缩略图',
    news_content     longblob     default '' comment '新闻内容',
    news_nav_id      bigint(20) not null comment '导航',
    status           char(1)      default '1' comment '显示状态（0隐藏 1显示）',
    is_top           char(1)      default '1' comment '是否置顶（0否 1是）',
    audit_status     char(1)      default '1' comment '是否审核（0驳回 1审核成功 2审核中）',
    audit_result     varchar(500) default '' comment '审核原因',
    look_num         bigint(20) default 0 comment '浏览量',
    create_by        varchar(64)  default '' comment '创建者',
    create_time      datetime comment '创建时间',
    update_by        varchar(64)  default '' comment '更新者',
    update_time      datetime comment '更新时间',
    primary key (news_id)
) engine=innodb auto_increment=1 comment = '新闻内容表';