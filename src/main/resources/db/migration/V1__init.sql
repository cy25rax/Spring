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
    max_book_count smallint
);

insert into readers (user_name) values
('reader 1'), ('reader 2');

create table books
(
    id         bigserial primary key,
    name       varchar(50) not null
);

insert into books (name) values
('война и мир'), ('мертвые души'), ('чистый код');
