create table admin
(
    id       uuid default gen_random_uuid() primary key,
    login    text not null unique,
    password text not null
);

insert into admin (login, password)
values ('admin12345', '$2a$10$d/ZFGsxkvu8zrVu0jUMnAO1tY.wEouF3y.OY6sZ1EjJcZAvmSkAqa');

--изменеие полей юзера
    alter table account
        add column date_registration timestamp not null default now();

--изменеие полей категории
alter table category
    add column is_overall boolean not null default false;

alter table category
    alter column user_id drop not null;

--изменеие полей активности
alter table activity
    alter column category_id drop not null;

alter table activity
    drop constraint if exists activity_category_id_fkey,
    add constraint fk_category_id foreign key (category_id) references category (id) on delete set null;