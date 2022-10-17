create table category
(
    id   int          not null
        primary key,
    name varchar(255) not null
);

INSERT INTO wj.category (id, name) VALUES (1, '文学');
INSERT INTO wj.category (id, name) VALUES (2, '流行');
INSERT INTO wj.category (id, name) VALUES (3, '文化');
INSERT INTO wj.category (id, name) VALUES (4, '生活');
INSERT INTO wj.category (id, name) VALUES (5, '经管');
INSERT INTO wj.category (id, name) VALUES (6, '科技');
