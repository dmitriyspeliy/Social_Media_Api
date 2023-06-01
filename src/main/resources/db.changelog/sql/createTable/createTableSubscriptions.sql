create table if not exists Subscriptions
(
    subscription_id bigint generated always as identity primary key,
    subscriber_id   bigint not null,
    subscribed_id   bigint not null,
    constraint fk_subscriber_id foreign key (subscriber_id) references Users (user_id),
    constraint fk_subscribed_id foreign key (subscribed_id) references Users (user_id)
);