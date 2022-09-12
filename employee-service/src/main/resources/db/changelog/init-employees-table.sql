--liquibase formatted sql

--changeset roman.tarasov:employee-1 splitStatements:true endDelimiter:;
create table employees (
    id uuid primary key,
    name varchar(255) not null,
    surname varchar(255) not null,
    position varchar(255) not null
);

insert into employees (id, name, surname, position) values
    ('00bc0e5d-add6-4abe-9699-0f48aa1bc371', 'Name', 'Surname', 'Position'),
    ('00bc0e5d-add6-4abe-9699-0f48aa1bc372', 'Name', 'Surname', 'Position');
--rollback drop table employees;