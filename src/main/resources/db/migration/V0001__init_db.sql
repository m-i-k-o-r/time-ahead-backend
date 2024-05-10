create table account
(
    id       uuid default gen_random_uuid() primary key,
    email    text not null unique,
    password text not null
);

create table category
(
    id      uuid default gen_random_uuid() primary key,
    name    text not null,
    user_id uuid not null,

    foreign key (user_id) references account (id) on delete cascade
);

create table activity
(
    id          uuid default gen_random_uuid() primary key,
    name        text      not null,
    description text,
    start_time  timestamp not null,
    end_time    timestamp,
    user_id     uuid      not null,
    category_id uuid      not null,

    foreign key (user_id) references account (id) on delete cascade,
    foreign key (category_id) references category (id) on delete cascade
);

create table habit
(
    id              uuid default gen_random_uuid() primary key,
    name            text    not null,
    description     text,
    repeat_reminder text    not null,
    num_reminder    int     not null,
    is_done         boolean not null,
    is_end          boolean not null,
    user_id         uuid    not null,

    foreign key (user_id) references account (id) on delete cascade
);
comment on column habit.repeat_reminder is 'Cron-выражениe';

create table task
(
    id          uuid default gen_random_uuid() primary key,
    name        text    not null,
    description text,
    reminder    timestamp,
    is_done     boolean not null,
    user_id     uuid    not null,

    foreign key (user_id) references account (id) on delete cascade
);