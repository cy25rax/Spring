create table issues
(
    id              bigserial    primary key,
    book_id          bigserial,
    reader_id        bigserial,
    issued_at      timestamp,
    returned_at      timestamp
);

insert into issues (book_id, reader_id, issued_at) values
(1, 1, '2009-06-04 18:13:56');


create table readers
(
    id         bigserial primary key,
    user_name   varchar(36) not null,
    max_book_count smallint,
    password   varchar(80) not null
);

insert into readers (user_name, password) values
('reader 1', '100'), ('reader 2', '111');

create table roles
(
    id         bigserial primary key,
    name       varchar(50) not null
);

create table readers_roles
(
    reader_id    bigint not null references readers (id),
    role_id    bigint not null references roles (id),
    primary key (reader_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into readers_roles (reader_id, role_id)
values (1, 1),
       (2, 1),
       (2, 2);

create table books
(
    id         bigserial primary key,
    name       varchar(50) not null
);

insert into books (name) values
('война и мир'), ('мертвые души'), ('чистый код');
