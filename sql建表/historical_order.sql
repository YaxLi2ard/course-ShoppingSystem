create table historical_order
(
    user_id  int         not null,
    brand_id int         not null,
    count    int         not null,
    total    int         not null,
    date     varchar(30) not null
);


