create table if not exists Posts
(
    post_id    bigint generated always as identity primary key,
    user_id    bigint not null,
    title      varchar(30) not null,
    text       text not null,
    path       varchar not null,
    created_at TIMESTAMP default current_date,
    constraint fk_user_id foreign key (user_id) references Users (user_id)
);