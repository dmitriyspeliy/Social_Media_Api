create table if not exists Messages
(
    message_id   bigint generated always as identity primary key,
    chat_id      bigint not null,
    message_text text,
    created_at   TIMESTAMP default current_date,
    constraint fk_chat_idMessage foreign key (chat_id) references Chats (chat_id)
);