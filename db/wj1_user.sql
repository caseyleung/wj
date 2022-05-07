create table user
(
    id       int(11) unsigned auto_increment
        primary key,
    username varchar(255) null,
    password varchar(255) null
)
    auto_increment = 2;

INSERT INTO wj1.user (id, username, password) VALUES (1, 'admin', '123');
