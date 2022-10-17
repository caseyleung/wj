create table admin_role
(
    id      int auto_increment
        primary key,
    name    varchar(100) null,
    name_zh varchar(100) null,
    enabled tinyint(1)   null
);

INSERT INTO wj.admin_role (id, name, name_zh, enabled) VALUES (1, 'sysAdmin', '系统管理员', 1);
INSERT INTO wj.admin_role (id, name, name_zh, enabled) VALUES (2, 'contentManager', '内容管理员', 1);
INSERT INTO wj.admin_role (id, name, name_zh, enabled) VALUES (3, 'visitor', '访客', 1);
INSERT INTO wj.admin_role (id, name, name_zh, enabled) VALUES (9, 'test', '测试角色', 1);
