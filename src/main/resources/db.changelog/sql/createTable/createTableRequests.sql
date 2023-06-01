create table if not exists Requests
(
    request_id      bigint generated always as identity primary key,
    request_id_to   bigint not null,
    request_id_from bigint not null,
    status          bool default false,
    constraint fk_request_id_to foreign key (request_id_to) references Users (user_id),
    constraint fk_request_id_from foreign key (request_id_from) references Users (user_id)
);
