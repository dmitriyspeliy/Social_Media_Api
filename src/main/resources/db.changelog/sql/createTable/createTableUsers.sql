create table if not exists Users
(
    user_id          bigint generated always as identity primary key,
    user_name        varchar(20)         not null,
    user_email       varchar(100) unique not null,
    user_password    varchar             not null,
    user_friend      bigint[],
    user_subscribers bigint[],
    user_subscribed  bigint[],
    user_request     bigint[],
    user_chats       bigint[]
)
