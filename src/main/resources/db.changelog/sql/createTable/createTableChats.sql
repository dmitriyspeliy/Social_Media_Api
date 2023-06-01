create table if not exists Chats
(
    chat_id   bigint generated always as identity primary key,
    first_id  bigint not null,
    second_id bigint not null,
    constraint fk_id_first_id foreign key (first_id) references Users (user_id),
    constraint fk_id_second_id foreign key (second_id) references Users (user_id)
);