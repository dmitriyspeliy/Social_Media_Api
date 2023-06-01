package ru.effectivemobile.Social_Media_Api.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

/**
 * Сущность дружбы
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Friendships")
@Entity
public class Friendship {

    /**
     * Id дружбы
     */
    @Id
    @Column(name = "friend_id")
    Long friendId;

    /**
     * Первый друг
     */
    @ManyToOne
    @JoinColumn(name = "first_friend_id")
    User firstFriendId;

    /**
     * Второй друг, вместе мы тут тук
     */
    @ManyToOne
    @JoinColumn(name = "second_friend_id")
    User secondFriendId;
}

