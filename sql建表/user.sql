create table user
(
    id            int auto_increment,
    username      varchar(30)             not null,
    password      varchar(33)             not null,
    email         varchar(30)             not null,
    type          tinyint(1) default 0    not null,
    register_time varchar(11)             not null,
    level         char(2)    default '铜牌' not null,
    phone         char(11)                not null,
    total         int        default 0    not null,
    error_cnt     int        default 0    not null,
    constraint user_email_uindex
        unique (email),
    constraint user_id_uindex
        unique (id)
);


