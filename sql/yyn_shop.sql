DROP TABLE IF EXISTS `shop_store_address`;
CREATE TABLE `shop_store_address`
(
    `address_id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '地址ID',
    `type`        tinyint(3) unsigned NOT NULL DEFAULT '10' COMMENT '地址类型(10发货地址 20退货地址)',
    `name`        varchar(30)  NOT NULL DEFAULT '' COMMENT '联系人姓名',
    `phone`       varchar(20)  NOT NULL DEFAULT '' COMMENT '联系电话',
    `province_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '省份ID',
    `city_id`     int(11) unsigned NOT NULL DEFAULT '0' COMMENT '城市ID',
    `region_id`   int(11) unsigned NOT NULL DEFAULT '0' COMMENT '区/县ID',
    `detail`      varchar(255) NOT NULL DEFAULT '' COMMENT '详细地址',
    `sort`        int(11) unsigned NOT NULL DEFAULT '0' COMMENT '排序(数字越小越靠前)',
    `is_delete`   tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
    `store_id`    int(11) unsigned NOT NULL DEFAULT '0' COMMENT '商城ID',
    `create_by`   varchar(64)           default '' comment '创建者',
    `create_time` datetime comment '创建时间',
    `update_by`   varchar(64)           default '' comment '更新者',
    `update_time` datetime comment '更新时间',
    `remark`      varchar(500)          default null comment '备注',
    PRIMARY KEY (`address_id`),
    KEY           `type` (`type`),
    KEY           `store_id` (`store_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='商家地址记录表';


DROP TABLE IF EXISTS `shop_page`;
CREATE TABLE `shop_page`
(
    `page_id`     int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '页面ID',
    `page_type`   tinyint(3) unsigned NOT NULL DEFAULT '10' COMMENT '页面类型(10首页 20自定义页)',
    `page_name`   varchar(255) NOT NULL DEFAULT '' COMMENT '页面名称',
    `page_data`   longtext     NOT NULL COMMENT '页面数据',
    `store_id`    int(11) unsigned NOT NULL DEFAULT '0' COMMENT '商城ID',
    `is_delete`   tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '软删除',
    `create_by`   varchar(64)           default '' comment '创建者',
    `create_time` datetime comment '创建时间',
    `update_by`   varchar(64)           default '' comment '更新者',
    `update_time` datetime comment '更新时间',
    `remark`      varchar(500)          default null comment '备注',
    PRIMARY KEY (`page_id`),
    KEY           `store_id` (`store_id`),
    KEY           `page_type` (`page_type`,`store_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8 COMMENT='店铺页面记录表';

DROP TABLE IF EXISTS `shop_store`;
CREATE TABLE `shop_store`
(
    `store_id`      bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商城ID',
    `store_name`    varchar(50)  NOT NULL DEFAULT '' COMMENT '商城名称',
    `describe`      varchar(500) NOT NULL DEFAULT '' COMMENT '商城简介',
    `logo_image_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '商城logo文件ID',
    `sort`          int(11) unsigned NOT NULL DEFAULT '0' COMMENT '排序(数字越小越靠前)',
    `is_recycle`    tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否回收',
    `is_delete`     tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
    `create_by`     varchar(64)           default '' comment '创建者',
    `create_time`   datetime comment '创建时间',
    `update_by`     varchar(64)           default '' comment '更新者',
    `update_time`   datetime comment '更新时间',
    `remark`        varchar(500)          default null comment '备注',
    PRIMARY KEY (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8 COMMENT='商家(商户)记录表';


DROP TABLE IF EXISTS `shop_article_category`;
CREATE TABLE `shop_article_category`
(
    `category_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文章分类ID',
    `name`        varchar(50) NOT NULL DEFAULT '' COMMENT '分类名称',
    `status`      tinyint(3) NOT NULL DEFAULT '1' COMMENT '状态(1显示 0隐藏)',
    `sort`        int(11) unsigned NOT NULL DEFAULT '0' COMMENT '排序方式(数字越小越靠前)',
    `store_id`    int(11) unsigned NOT NULL DEFAULT '0' COMMENT '商城ID',
    `create_by`   varchar(64)          default '' comment '创建者',
    `create_time` datetime comment '创建时间',
    `update_by`   varchar(64)          default '' comment '更新者',
    `update_time` datetime comment '更新时间',
    `remark`      varchar(500)         default null comment '备注',
    PRIMARY KEY (`category_id`),
    KEY           `store_id` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='文章分类表';


DROP TABLE IF EXISTS `shop_article`;
CREATE TABLE `shop_article`
(
    `article_id`    bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文章ID',
    `title`         varchar(300) NOT NULL DEFAULT '' COMMENT '文章标题',
    `show_type`     tinyint(3) unsigned NOT NULL DEFAULT '10' COMMENT '列表显示方式(10小图展示 20大图展示)',
    `category_id`   int(11) unsigned NOT NULL DEFAULT '0' COMMENT '文章分类ID',
    `image_id`      int(11) unsigned NOT NULL DEFAULT '0' COMMENT '封面图ID',
    `content`       longtext     NOT NULL COMMENT '文章内容',
    `sort`          int(11) unsigned NOT NULL DEFAULT '0' COMMENT '文章排序(数字越小越靠前)',
    `status`        tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '文章状态(0隐藏 1显示)',
    `virtual_views` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '虚拟阅读量(仅用作展示)',
    `actual_views`  int(11) unsigned NOT NULL DEFAULT '0' COMMENT '实际阅读量',
    `is_delete`     int(11) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
    `store_id`      int(11) unsigned NOT NULL DEFAULT '0' COMMENT '商城ID',
    `create_by`     varchar(64)           default '' comment '创建者',
    `create_time`   datetime comment '创建时间',
    `update_by`     varchar(64)           default '' comment '更新者',
    `update_time`   datetime comment '更新时间',
    `remark`        varchar(500)          default null comment '备注',
    PRIMARY KEY (`article_id`),
    KEY             `category_id` (`category_id`),
    KEY             `store_id` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='文章记录表';

DROP TABLE IF EXISTS `shop_upload_group`;
CREATE TABLE `shop_upload_group`
(
    `group_id`    bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分组ID',
    `name`        varchar(30) NOT NULL DEFAULT '' COMMENT '分组名称',
    `parent_id`   int(11) unsigned NOT NULL DEFAULT '0' COMMENT '上级分组ID',
    `sort`        int(11) unsigned NOT NULL DEFAULT '0' COMMENT '排序(数字越小越靠前)',
    `is_delete`   tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
    `store_id`    int(11) unsigned NOT NULL DEFAULT '0' COMMENT '商城ID',
    `create_by`   varchar(64)          default '' comment '创建者',
    `create_time` datetime comment '创建时间',
    `update_by`   varchar(64)          default '' comment '更新者',
    `update_time` datetime comment '更新时间',
    `remark`      varchar(500)         default null comment '备注',
    PRIMARY KEY (`group_id`),
    KEY           `store_id` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8mb4 COMMENT='文件库分组记录表';

DROP TABLE IF EXISTS `shop_upload_file`;
CREATE TABLE `shop_upload_file`
(
    `file_id`     bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文件ID',
    `group_id`    bigint(20) NOT NULL DEFAULT '0' COMMENT '文件分组ID',
    `channel`     tinyint(3) unsigned NOT NULL DEFAULT '10' COMMENT '上传来源(10商户后台 20用户端)',
    `storage`     varchar(10)  NOT NULL DEFAULT '' COMMENT '存储方式',
    `domain`      varchar(255) NOT NULL DEFAULT '' COMMENT '存储域名',
    `file_type`   tinyint(3) unsigned NOT NULL DEFAULT '10' COMMENT '文件类型(10图片 20附件 30视频)',
    `file_name`   varchar(255) NOT NULL DEFAULT '' COMMENT '文件名称(仅显示)',
    `file_path`   varchar(255) NOT NULL DEFAULT '' COMMENT '文件路径',
    `file_size`   bigint(20) NULL DEFAULT '0' COMMENT '文件大小(字节)',
    `file_ext`    varchar(20)  NOT NULL DEFAULT '' COMMENT '文件扩展名',
    `cover`       varchar(255) NOT NULL DEFAULT '' COMMENT '文件封面',
    `uploader_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '上传者用户ID',
    `is_recycle`  tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否在回收站',
    `is_delete`   tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
    `store_id`    int(11) unsigned NOT NULL DEFAULT '0' COMMENT '商城ID',
    `create_by`   varchar(64)           default '' comment '创建者',
    `create_time` datetime comment '创建时间',
    `update_by`   varchar(64)           default '' comment '更新者',
    `update_time` datetime comment '更新时间',
    `remark`      varchar(500)          default null comment '备注',
    PRIMARY KEY (`file_id`),
    KEY           `group_id` (`group_id`),
    KEY           `is_recycle` (`is_recycle`),
    KEY           `store_id` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='文件库记录表';


DROP TABLE IF EXISTS `shop_help`;
CREATE TABLE `shop_help`
(
    `help_id`     bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `title`       varchar(255) NOT NULL DEFAULT '' COMMENT '帮助标题',
    `content`     text         NOT NULL COMMENT '帮助内容',
    `sort`        int(11) unsigned NOT NULL DEFAULT '0' COMMENT '排序(数字越小越靠前)',
    `is_delete`   tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除(1已删除)',
    `store_id`    bigint(20) NOT NULL DEFAULT '0' COMMENT '商城ID',
    `create_by`   varchar(64)           default '' comment '创建者',
    `create_time` datetime comment '创建时间',
    `update_by`   varchar(64)           default '' comment '更新者',
    `update_time` datetime comment '更新时间',
    `remark`      varchar(500)          default null comment '备注',
    PRIMARY KEY (`help_id`),
    KEY           `store_id` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='帮助中心记录表';

DROP TABLE IF EXISTS `shop_category`;
CREATE TABLE `shop_category`
(
    `category_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品分类ID',
    `name`        varchar(50) NOT NULL DEFAULT '' COMMENT '分类名称',
    `parent_id`   bigint(20) NOT NULL DEFAULT '0' COMMENT '上级分类ID',
    `image_id`    bigint(20) NOT NULL DEFAULT '0' COMMENT '分类图片ID',
    `status`      tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1显示 0隐藏)',
    `sort`        int(11) unsigned NOT NULL DEFAULT '0' COMMENT '排序方式(数字越小越靠前)',
    `store_id`    bigint(20) NOT NULL DEFAULT '0' COMMENT '商城ID',
    `create_by`   varchar(64)          default '' comment '创建者',
    `create_time` datetime comment '创建时间',
    `update_by`   varchar(64)          default '' comment '更新者',
    `update_time` datetime comment '更新时间',
    `remark`      varchar(500)         default null comment '备注',
    PRIMARY KEY (`category_id`),
    KEY           `store_id` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8 COMMENT='商品分类表';


DROP TABLE IF EXISTS `shop_goods_service`;
CREATE TABLE `shop_goods_service`
(
    `service_id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品服务ID',
    `name`        varchar(50)  NOT NULL DEFAULT '' COMMENT '服务名称',
    `summary`     varchar(500) NOT NULL DEFAULT '' COMMENT '概述',
    `is_default`  tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否默认(新增商品时)',
    `status`      tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1显示 0隐藏)',
    `sort`        int(11) unsigned NOT NULL DEFAULT '100' COMMENT '排序方式(数字越小越靠前)',
    `is_delete`   tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除(1已删除)',
    `store_id`    bigint(20) NOT NULL DEFAULT '0' COMMENT '商城ID',
    `create_by`   varchar(64)           default '' comment '创建者',
    `create_time` datetime comment '创建时间',
    `update_by`   varchar(64)           default '' comment '更新者',
    `update_time` datetime comment '更新时间',
    `remark`      varchar(500)          default null comment '备注',
    PRIMARY KEY (`service_id`),
    KEY           `store_id` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='商品服务与承诺记录表';

DROP TABLE IF EXISTS `shop_delivery`;
CREATE TABLE `shop_delivery`
(
    `delivery_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '模板ID',
    `name`        varchar(255) NOT NULL DEFAULT '' COMMENT '模板名称',
    `method`      tinyint(3) unsigned NOT NULL DEFAULT '10' COMMENT '计费方式(10按件数 20按重量)',
    `sort`        int(11) unsigned NOT NULL DEFAULT '0' COMMENT '排序方式(数字越小越靠前)',
    `is_delete`   tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
    `store_id`    bigint(20) NOT NULL DEFAULT '0' COMMENT '小程序d',
    `create_by`   varchar(64)           default '' comment '创建者',
    `create_time` datetime comment '创建时间',
    `update_by`   varchar(64)           default '' comment '更新者',
    `update_time` datetime comment '更新时间',
    `remark`      varchar(500)          default null comment '备注',
    PRIMARY KEY (`delivery_id`),
    KEY           `store_id` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='配送模板主表';


DROP TABLE IF EXISTS `shop_region`;
CREATE TABLE `shop_region`
(
    `id`    bigint(20) NOT NULL AUTO_INCREMENT COMMENT '区划信息ID',
    `name`  varchar(255) NOT NULL DEFAULT '' COMMENT '区划名称',
    `pid`   bigint(20) NOT NULL DEFAULT '0' COMMENT '父级ID',
    `code`  varchar(255) NOT NULL DEFAULT '' COMMENT '区划编码',
    `level` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '层级(1省级 2市级 3区/县级)',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='省市区数据表';


DROP TABLE IF EXISTS `shop_delivery_rule`;
CREATE TABLE `shop_delivery_rule`
(
    `rule_id`        bigint(20) NOT NULL AUTO_INCREMENT COMMENT '规则ID',
    `delivery_id`    bigint(20) NOT NULL DEFAULT '0' COMMENT '配送模板ID',
    `region`         text NOT NULL COMMENT '可配送区域(城市id集)',
    `region_text`    text NOT NULL COMMENT '可配送区域(文字展示)',
    `first`          double unsigned NOT NULL DEFAULT '0' COMMENT '首件(个)/首重(Kg)',
    `first_fee`      decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '运费(元)',
    `additional`     double unsigned NOT NULL DEFAULT '0' COMMENT '续件/续重',
    `additional_fee` decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '续费(元)',
    `store_id`       bigint(20) NOT NULL DEFAULT '0' COMMENT '商城ID',
    `create_by`      varchar(64)  default '' comment '创建者',
    `create_time`    datetime comment '创建时间',
    `update_by`      varchar(64)  default '' comment '更新者',
    `update_time`    datetime comment '更新时间',
    `remark`         varchar(500) default null comment '备注',
    PRIMARY KEY (`rule_id`),
    KEY              `delivery_id` (`delivery_id`),
    KEY              `store_id` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='配送模板区域及运费表';


DROP TABLE IF EXISTS `shop_goods`;
CREATE TABLE `shop_goods`
(
    `goods_id`                 bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
    `goods_name`               varchar(255) NOT NULL DEFAULT '' COMMENT '商品名称',
    `goods_no`                 varchar(50)  NOT NULL DEFAULT '' COMMENT '商品编码',
    `selling_point`            varchar(500) NOT NULL DEFAULT '' COMMENT '商品卖点',
    `spec_type`                tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '商品规格(10单规格 20多规格)',
    `goods_price_min`          decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '商品价格(最低)',
    `goods_price_max`          decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '商品价格(最高)',
    `line_price_min`           decimal(10, 2) unsigned DEFAULT '0.00' COMMENT '划线价格(最低)',
    `line_price_max`           decimal(10, 2) unsigned DEFAULT '0.00' COMMENT '划线价格(最高)',
    `stock_total`              int(11) unsigned NOT NULL DEFAULT '0' COMMENT '库存总量(包含所有sku)',
    `deduct_stock_type`        tinyint(3) unsigned NOT NULL DEFAULT '20' COMMENT '库存计算方式(10下单减库存 20付款减库存)',
    `content`                  longtext     NOT NULL COMMENT '商品详情',
    `first_image_id`           bigint(20) NOT NULL COMMENT '头图ID',
    `sales_initial`            int(11) unsigned NOT NULL DEFAULT '0' COMMENT '初始销量',
    `sales_actual`             int(11) unsigned NOT NULL DEFAULT '0' COMMENT '实际销量',
    `delivery_id`              int(11) unsigned NOT NULL DEFAULT '0' COMMENT '配送模板ID',
    `is_points_gift`           tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '是否开启积分赠送(1开启 0关闭)',
    `is_points_discount`       tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '是否允许使用积分抵扣(1允许 0不允许)',
    `is_alone_points_discount` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '积分抵扣设置(0默认抵扣 1单独设置抵扣)',
    `points_discount_config`   varchar(500) NOT NULL DEFAULT '' COMMENT '单独设置积分抵扣的配置',
    `is_enable_grade`          tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '是否开启会员折扣(1开启 0关闭)',
    `is_alone_grade`           tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '会员折扣设置(0默认等级折扣 1单独设置折扣)',
    `alone_grade_equity`       text COMMENT '单独设置折扣的配置',
    `status`                   tinyint(3) unsigned NOT NULL DEFAULT '10' COMMENT '商品状态(10上架 20下架)',
    `sort`                     int(11) unsigned NOT NULL DEFAULT '0' COMMENT '排序(数字越小越靠前)',
    `is_delete`                tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
    `store_id`                 bigint(20) NOT NULL DEFAULT '0' COMMENT '商城ID',
    `create_by`                varchar(64)           default '' comment '创建者',
    `create_time`              datetime comment '创建时间',
    `update_by`                varchar(64)           default '' comment '更新者',
    `update_time`              datetime comment '更新时间',
    `remark`                   varchar(500)          default null comment '备注',
    PRIMARY KEY (`goods_id`),
    KEY                        `goods_no` (`goods_no`),
    KEY                        `store_id` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='商品记录表';

DROP TABLE IF EXISTS `shop_goods_category_rel`;
CREATE TABLE `shop_goods_category_rel`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `goods_id`    bigint(20) NOT NULL COMMENT '商品ID',
    `category_id` bigint(20) NOT NULL COMMENT '商品分类ID',
    `store_id`    bigint(20) NOT NULL DEFAULT '0' COMMENT '商城ID',
    `create_by`   varchar(64)  default '' comment '创建者',
    `create_time` datetime comment '创建时间',
    `update_by`   varchar(64)  default '' comment '更新者',
    `update_time` datetime comment '更新时间',
    `remark`      varchar(500) default null comment '备注',
    PRIMARY KEY (`id`),
    KEY           `store_id` (`store_id`),
    KEY           `goods_id` (`goods_id`),
    KEY           `category_id` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='商品与分类关系记录表';

DROP TABLE IF EXISTS `shop_goods_image`;
CREATE TABLE `shop_goods_image`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `goods_id`    bigint(20) NOT NULL DEFAULT '0' COMMENT '商品ID',
    `image_id`    bigint(20) NOT NULL COMMENT '图片id(关联文件记录表)',
    `store_id`    bigint(20) NOT NULL DEFAULT '0' COMMENT '商城ID',
    `create_by`   varchar(64)  default '' comment '创建者',
    `create_time` datetime comment '创建时间',
    `update_by`   varchar(64)  default '' comment '更新者',
    `update_time` datetime comment '更新时间',
    `remark`      varchar(500) default null comment '备注',
    PRIMARY KEY (`id`),
    KEY           `goods_id` (`goods_id`),
    KEY           `store_id` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='商品图片记录表';

DROP TABLE IF EXISTS `shop_goods_service_rel`;
CREATE TABLE `shop_goods_service_rel`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `goods_id`    bigint(20) NOT NULL COMMENT '商品ID',
    `service_id`  bigint(20) NOT NULL COMMENT '服务承诺ID',
    `store_id`    bigint(20) NOT NULL DEFAULT '0' COMMENT '商城ID',
    `create_by`   varchar(64)  default '' comment '创建者',
    `create_time` datetime comment '创建时间',
    `update_by`   varchar(64)  default '' comment '更新者',
    `update_time` datetime comment '更新时间',
    `remark`      varchar(500) default null comment '备注',
    PRIMARY KEY (`id`),
    KEY           `store_id` (`store_id`),
    KEY           `goods_id` (`goods_id`),
    KEY           `service_id` (`service_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='商品与服务承诺关系记录表';

DROP TABLE IF EXISTS `shop_goods_sku`;
CREATE TABLE `shop_goods_sku`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
    `goods_sku_id`   varchar(255) NOT NULL DEFAULT '0' COMMENT '商品sku唯一标识 (由规格id组成)',
    `goods_id`       bigint(20) NOT NULL DEFAULT '0' COMMENT '商品ID',
    `image_id`       bigint(20) NOT NULL DEFAULT '0' COMMENT '规格图片ID',
    `goods_sku_no`   varchar(100) NOT NULL DEFAULT '' COMMENT '商品sku编码',
    `goods_price`    decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '商品价格',
    `line_price`     decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '商品划线价',
    `stock_num`      bigint(20) NOT NULL DEFAULT '0' COMMENT '当前库存数量',
    `goods_weight`   decimal(10, 2) unsigned NOT NULL DEFAULT '0' COMMENT '商品重量(Kg)',
    `goods_props`    text NULL COMMENT 'SKU的规格属性(json格式)',
    `spec_value_ids` text NULL COMMENT '规格值ID集(json格式)',
    `store_id`       bigint(20) NOT NULL DEFAULT '0' COMMENT '商城ID',
    `create_by`      varchar(64)           default '' comment '创建者',
    `create_time`    datetime comment '创建时间',
    `update_by`      varchar(64)           default '' comment '更新者',
    `update_time`    datetime comment '更新时间',
    `remark`         varchar(500)          default null comment '备注',
    PRIMARY KEY (`id`),
    UNIQUE KEY `sku_idx` (`goods_id`,`goods_sku_id`),
    KEY              `store_id` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='商品规格表';

DROP TABLE IF EXISTS `shop_goods_spec_rel`;
CREATE TABLE `shop_goods_spec_rel`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `goods_id`      bigint(20) NOT NULL DEFAULT '0' COMMENT '商品ID',
    `spec_id`       bigint(20) NOT NULL DEFAULT '0' COMMENT '规格组ID',
    `spec_value_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '规格值ID',
    `store_id`      bigint(20) NOT NULL DEFAULT '0' COMMENT '商城ID',
    `create_by`     varchar(64)  default '' comment '创建者',
    `create_time`   datetime comment '创建时间',
    `update_by`     varchar(64)  default '' comment '更新者',
    `update_time`   datetime comment '更新时间',
    `remark`        varchar(500) default null comment '备注',
    PRIMARY KEY (`id`),
    KEY             `store_id` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='商品与规格值关系记录表';

DROP TABLE IF EXISTS `shop_spec`;
CREATE TABLE `shop_spec`
(
    `spec_id`     bigint(20) NOT NULL AUTO_INCREMENT COMMENT '规格组ID',
    `spec_name`   varchar(255) NOT NULL DEFAULT '' COMMENT '规格组名称',
    `store_id`    bigint(20) NOT NULL DEFAULT '0' COMMENT '商城ID',
    `create_by`   varchar(64)           default '' comment '创建者',
    `create_time` datetime comment '创建时间',
    `update_by`   varchar(64)           default '' comment '更新者',
    `update_time` datetime comment '更新时间',
    `remark`      varchar(500)          default null comment '备注',
    PRIMARY KEY (`spec_id`),
    KEY           `spec_name` (`spec_name`),
    KEY           `store_id` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='商品规格组记录表';

DROP TABLE IF EXISTS `shop_spec_value`;
CREATE TABLE `shop_spec_value`
(
    `spec_value_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '规格值ID',
    `spec_value`    varchar(255) NOT NULL COMMENT '规格值',
    `spec_id`       bigint(20) NOT NULL COMMENT '规格组ID',
    `store_id`      bigint(20) NOT NULL COMMENT '商城ID',
    `create_by`     varchar(64)  default '' comment '创建者',
    `create_time`   datetime comment '创建时间',
    `update_by`     varchar(64)  default '' comment '更新者',
    `update_time`   datetime comment '更新时间',
    `remark`        varchar(500) default null comment '备注',
    PRIMARY KEY (`spec_value_id`),
    KEY             `spec_value` (`spec_value`),
    KEY             `spec_id` (`spec_id`),
    KEY             `store_id` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='商品规格值记录表';


DROP TABLE IF EXISTS `shop_store_setting`;
CREATE TABLE `shop_store_setting`
(
    `key`         varchar(30)  NOT NULL COMMENT '设置项标示',
    `describe`    varchar(255) NOT NULL DEFAULT '' COMMENT '设置项描述',
    `values`      mediumtext   NOT NULL COMMENT '设置内容（json格式）',
    `store_id`    bigint(20) NOT NULL DEFAULT '0' COMMENT '商城ID',
    `create_by`   varchar(64)           default '' comment '创建者',
    `create_time` datetime comment '创建时间',
    `update_by`   varchar(64)           default '' comment '更新者',
    `update_time` datetime comment '更新时间',
    `remark`      varchar(500)          default null comment '备注',
    UNIQUE KEY `unique_key` (`key`,`store_id`),
    KEY           `store_id` (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商家设置记录表';


DROP TABLE IF EXISTS `shop_express`;
CREATE TABLE `shop_express`
(
    `express_id`     bigint(20) NOT NULL AUTO_INCREMENT COMMENT '物流公司ID',
    `express_name`   varchar(255) NOT NULL DEFAULT '' COMMENT '物流公司名称',
    `kuaidi100_code` varchar(30)  NOT NULL DEFAULT '' COMMENT '物流公司编码 (快递100)',
    `sort`           int(11) unsigned NOT NULL DEFAULT '0' COMMENT '排序(数字越小越靠前)',
    `store_id`       bigint(20) NOT NULL DEFAULT '0' COMMENT '商城ID',
    `create_by`      varchar(64)           default '' comment '创建者',
    `create_time`    datetime comment '创建时间',
    `update_by`      varchar(64)           default '' comment '更新者',
    `update_time`    datetime comment '更新时间',
    `remark`         varchar(500)          default null comment '备注',
    PRIMARY KEY (`express_id`),
    KEY              `store_id` (`store_id`),
    KEY              `kuaidi100_code` (`kuaidi100_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='物流公司记录表';


DROP TABLE IF EXISTS `shop_page`;
CREATE TABLE `shop_page`
(
    `page_id`     bigint(20) NOT NULL AUTO_INCREMENT COMMENT '页面ID',
    `page_type`   tinyint(3) unsigned NOT NULL DEFAULT '10' COMMENT '页面类型(10首页 20自定义页)',
    `page_name`   varchar(255) NOT NULL DEFAULT '' COMMENT '页面名称',
    `page_data`   longtext     NOT NULL COMMENT '页面数据',
    `store_id`    bigint(20) NOT NULL DEFAULT '0' COMMENT '商城ID',
    `is_delete`   tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '软删除',
    `create_by`      varchar(64)           default '' comment '创建者',
    `create_time`    datetime comment '创建时间',
    `update_by`      varchar(64)           default '' comment '更新者',
    `update_time`    datetime comment '更新时间',
    `remark`         varchar(500)          default null comment '备注',
    PRIMARY KEY (`page_id`),
    KEY           `store_id` (`store_id`),
    KEY           `page_type` (`page_type`,`store_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='店铺页面记录表';
