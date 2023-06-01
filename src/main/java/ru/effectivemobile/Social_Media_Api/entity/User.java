package ru.effectivemobile.Social_Media_Api.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность юзера
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Users")
@Entity
public class User {

    /**
     * Идентификатор юзера
     */
    @Id
    Long userId;

    /**
     * Имя юзера
     */
    @Column(name = "user_name")
    String userName;

    /**
     * Почта юзера
     */
    @Column(name = "user_email")
    String userEmail;

    /**
     * Пароль юзера
     */
    @Column(name = "user_password")
    String userPassword;

    /**
     * Посты юзера
     */
    @OneToMany(mappedBy = "postList", fetch = FetchType.LAZY)
    List<Post> postList;

    /**
     * Запросы от юзера
     */
    @OneToMany(mappedBy = "requestIdTo", fetch = FetchType.LAZY)
    List<Request> requestsTo;

    /**
     * Запросы к юзеру
     */
    @OneToMany(mappedBy = "requestIdFrom", fetch = FetchType.LAZY)
    List<Request> requestsFrom;

    /**
     * Лист тех, кто подписался
     */
    @OneToMany(mappedBy = "subscriberId", fetch = FetchType.LAZY)
    List<Subscription> subscriberId;

    /**
     * Лист тех, на кого подписались
     */
    @OneToMany(mappedBy = "subscribedId", fetch = FetchType.LAZY)
    List<Subscription> subscribedId;

    /**
     * Лист друзей
     */
    @OneToMany(mappedBy = "firstFriendId", fetch = FetchType.LAZY)
    List<Friendship> firstFriendId;

    /**
     * Лист друзей
     */
    @OneToMany(mappedBy = "secondFriendId", fetch = FetchType.LAZY)
    List<Friendship> secondFriendId;

    /**
     * Лист чата
     */
    @OneToMany(mappedBy = "firstId", fetch = FetchType.LAZY)
    List<Chat> firstId;

    /**
     * Лист чата
     */
    @OneToMany(mappedBy = "secondId", fetch = FetchType.LAZY)
    List<Chat> secondId;


}
