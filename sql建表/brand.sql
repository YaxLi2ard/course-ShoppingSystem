create table brand
(
    id           int auto_increment
        primary key,
    brand_name   varchar(30) not null,
    company_name varchar(30) not null,
    date         varchar(11) not null,
    type         varchar(30) not null,
    stock_price  int         not null,
    retail_price int         not null,
    count        int         not null,
    constraint brand_id_uindex
        unique (id)
);


