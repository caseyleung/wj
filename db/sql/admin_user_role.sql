create table admin_user_role
(
    id  int auto_increment
        primary key,
    uid int null,
    rid int null
);

create index fk_operator_role_operator_1
    on admin_user_role (uid);

create index fk_operator_role_role_1
    on admin_user_role (rid);

INSERT INTO wj.admin_user_role (id, uid, rid) VALUES (40, 24, 2);
INSERT INTO wj.admin_user_role (id, uid, rid) VALUES (63, 3, 2);
INSERT INTO wj.admin_user_role (id, uid, rid) VALUES (64, 1, 1);
INSERT INTO wj.admin_user_role (id, uid, rid) VALUES (67, 2, 3);
INSERT INTO wj.admin_user_role (id, uid, rid) VALUES (72, 110, 1);
INSERT INTO wj.admin_user_role (id, uid, rid) VALUES (73, 110, 2);
INSERT INTO wj.admin_user_role (id, uid, rid) VALUES (74, 110, 3);
INSERT INTO wj.admin_user_role (id, uid, rid) VALUES (75, 110, 9);
