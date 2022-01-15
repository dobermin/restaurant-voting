create table restaurant
(
    id   bigint       not null
        constraint restaurant_pkey
            primary key,
    name varchar(255) not null
        constraint uk_i6u3x7opncroyhd755ejknses
            unique
        constraint uki6u3x7opncroyhd755ejknses
            unique
);

alter table restaurant
    owner to postgres;

create table menu
(
    id            bigint not null
        constraint menu_pkey
            primary key,
    date_time     timestamp,
    restaurant_id bigint
        constraint fkblwdtxevpl4mrds8a12q0ohu6
            references restaurant
);

alter table menu
    owner to postgres;

create table dish
(
    id      bigint           not null
        constraint dish_pkey
            primary key,
    name    varchar(255)     not null,
    price   double precision not null,
    menu_id bigint
        constraint fkljuksxg35var0r9a3y09l148h
            references menu,
    constraint ukojla7gu4fgb2tr7jfbgh9inpv
        unique (name, price)
);

alter table dish
    owner to postgres;

create table usr
(
    id       bigint       not null
        constraint usr_pkey
            primary key,
    login    varchar(255) not null
        constraint uk_b2j2bjirhqhbg1rsexaq5qs9x
            unique
        constraint ukb2j2bjirhqhbg1rsexaq5qs9x
            unique,
    password varchar(255) not null,
    role     varchar(255)
);

alter table usr
    owner to postgres;

create table vote
(
    id            bigint not null
        constraint vote_pkey
            primary key,
    voted         timestamp,
    voted_date    varchar(255),
    restaurant_id bigint
        constraint fk8cs2ixj0elmaw4psra8ht1mgb
            references restaurant,
    user_id       bigint
        constraint fkc6i3m68apmo9o5oqntyl0s0ip
            references usr,
    constraint ukpdckpv8a318xxp688la60gm63
        unique (user_id, voted_date)
);

alter table vote
    owner to postgres;


