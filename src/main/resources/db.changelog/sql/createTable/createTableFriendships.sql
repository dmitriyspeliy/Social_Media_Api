create table if not exists Friendships
(
    friend_id        bigint generated always as identity primary key,
    first_friend_id  bigint not null,
    second_friend_id bigint not null,
    constraint fk_first_friend_id foreign key (first_friend_id) references Users (user_id),
    constraint fk_second_friend_id foreign key (second_friend_id) references Users (user_id)
);