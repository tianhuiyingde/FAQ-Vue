-- 1、业务模块表
-- ----------------------------
drop table if exists axx_servicemodule;
create table axx_servicemodule (
  servicemodule_id           bigint(20)      not null auto_increment    comment '业务模块id',
  parent_id         bigint(20)      default 0                  comment '父部门id',
  ancestors         varchar(50)     default ''                 comment '祖级列表',
  servicemodule_name         varchar(30)     default ''                 comment '业务模块名称',
  order_num         int(4)          default 0                  comment '显示顺序',
  leader            varchar(20)     default null               comment '负责人',
  email             varchar(50)     default null               comment '邮箱',
  status            char(1)         default '0'                comment '业务模块状态（0正常 1停用）',
  del_flag          char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (servicemodule_id)
) engine=innodb auto_increment=200 comment = '业务模块表';

-- ----------------------------
-- 初始化-业务模块表数据
-- ----------------------------
insert into axx_servicemodule values(100,  0,   '0',          '在线研发部',   0, '陈冲',  '', '0', '0', 'admin', sysdate(), '', null);
insert into axx_servicemodule values(101,  0,   '0',          '双师研发部',   1, '陈志琴',  '', '0', '0', 'admin', sysdate(), '', null);
insert into axx_servicemodule values(102,  100, '0,100',      'TOL', 1, '若依',  'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into axx_servicemodule values(103,  100, '0,100',      '学生端APP', 2, '若依',  'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into axx_servicemodule values(104,  100, '0,100',      '在线小班', 3, '若依',  'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into axx_servicemodule values(105,  101, '0,101',  '双师在线',   1, '若依',  'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into axx_servicemodule values(106,  101, '0,101',  'AI好课',   2, '若依',  'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into axx_servicemodule values(107,  102, '0,100,102',  '北极星Windows',   1, '若依',  'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into axx_servicemodule values(108,  102, '0,100,102',  '直播间',   2, '若依',  'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into axx_servicemodule values(109,  102, '0,100,102',  '课后作业',   3, '若依',  'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into axx_servicemodule values(110,  103, '0,100,103',  '自我巩固',   1, '若依',  'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into axx_servicemodule values(111,  104, '0,100,103',  '语音作业',   2, '若依',  'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);


-- ----------------------------
-- 问题详情表
-- ----------------------------
drop table if exists axx_question;
create table axx_question (
  question_id           bigint(20)      not null auto_increment    comment '问题id',
  servicemodule_id        bigint(20)      not null                   comment '业务模块id',
  question_desc               varchar(30)     default ''                 comment '问题概述',
	question_answer                varchar(2000)    default ''               comment '问题答案',
	question_key					varchar(500)    default null               comment '问题关键字',
  remark                varchar(500)    default null               comment '备注',
 status            char(1)         default '0'                comment '问题状态（0正常 1停用）',
 del_flag          char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
 create_by         varchar(64)     default ''                 comment '创建者',
 create_time       datetime                                   comment '创建时间',
 update_by         varchar(64)     default ''                 comment '更新者',
 update_time       datetime                                   comment '更新时间',
  primary key (question_id)
) engine=innodb auto_increment=1 comment = '问题详情表';






-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('业务模块', '1', '1', 'servicemodule', 'aixuexi/servicemodule/index', 1, 0, 'C', '0', '0', 'aixuexi:servicemodule:list', '#', 'admin', sysdate(), '', null, '业务模块菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('业务模块查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'aixuexi:servicemodule:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('业务模块新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'aixuexi:servicemodule:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('业务模块修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'aixuexi:servicemodule:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('业务模块删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'aixuexi:servicemodule:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('业务模块导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'aixuexi:servicemodule:export',       '#', 'admin', sysdate(), '', null, '');


-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('问题', '1', '1', 'question', 'aixuexi/question/index', 1, 0, 'C', '0', '0', 'aixuexi:question:list', '#', 'admin', sysdate(), '', null, '问题菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('问题查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'aixuexi:question:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('问题新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'aixuexi:question:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('问题修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'aixuexi:question:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('问题删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'aixuexi:question:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('问题导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'aixuexi:question:export',       '#', 'admin', sysdate(), '', null, '');