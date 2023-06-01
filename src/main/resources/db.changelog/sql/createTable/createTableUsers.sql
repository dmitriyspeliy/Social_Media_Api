create table if not exists Users
(
    user_id       bigint generated always as identity primary key,
    user_name     varchar(20)  not null,
    user_email    varchar(100) unique not null,
    user_password varchar      not null
)
