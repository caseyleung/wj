create table user
(
    id       int auto_increment
        primary key,
    username char(255)    not null,
    password varchar(255) null,
    salt     varchar(255) null,
    name     varchar(255) null,
    phone    varchar(255) null,
    email    varchar(255) null,
    enabled  tinyint(1)   null
);

INSERT INTO wj.user (id, username, password, salt, name, phone, email, enabled) VALUES (1, 'admin', '35b9529f89cfb9b848060ca576237e17', '8O+vDNr2sI3N82BI31fu1A==', '管理员', '12312312312', 'evan_nightly@163.com', 1);
INSERT INTO wj.user (id, username, password, salt, name, phone, email, enabled) VALUES (2, 'test', '85087738b6c1e1d212683bfafc163853', 'JBba3j5qRykIPJQYTNNH9A==', '测试', '12312312312', '123@123.com', 1);
INSERT INTO wj.user (id, username, password, salt, name, phone, email, enabled) VALUES (3, 'editor', '8583a2d965d6159edbf65c82d871fa3e', 'MZTe7Qwf9QgXBXrZzTIqJQ==', '编辑', null, null, 1);
INSERT INTO wj.user (id, username, password, salt, name, phone, email, enabled) VALUES (110, '10000', 'c41530f0e6ae76c03a682abaa69c5935', 'csL2gFqZwRar6s9pB4cihg==', 'casey', '15723830906', '15724140906@163.com', 1);
