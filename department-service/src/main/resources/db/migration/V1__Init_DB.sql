create table departments(
    id uuid not null primary key,
    name varchar(255) not null
);

insert into departments (id, name) values
    ('4373788d-fde1-4ce4-a103-f6111dc88af1', 'dep1'),
    ('4373788d-fde1-4ce4-a103-f6111dc88af2', 'dep2')