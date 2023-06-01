package ru.effectivemobile.Social_Media_Api.entity;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
@TypeDefs({
        @TypeDef(
                name = "list",
                typeClass = ListArrayType.class
        )
})
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
    @OneToMany(mappedBy = "userPosts", fetch = FetchType.LAZY)
    List<Post> userPosts;

    /**
     * Лист с id друзей
     */
    @Type(type = "list")
    @Column(name = "user_friend", columnDefinition = "bigint[]")
    Set<Long> userFriends;

    /**
     * Лист с id подписчиков
     */
    @Type(type = "list")
    @Column(name = "user_subscribers", columnDefinition = "bigint[]")
    Set<Long> userSubscribers;

    /**
     * Лист с id подписок на кого-то
     */
    @Type(type = "list")
    @Column(name = "user_subscribed", columnDefinition = "bigint[]")
    Set<Long> userSubscribed;

    /**
     * Лист с id запросов в друзья
     */
    @Type(type = "list")
    @Column(name = "user_request", columnDefinition = "bigint[]")
    Set<Long> userRequests;

    /**
     * Лист с id запросов в друзья
     */
    @Type(type = "list")
    @Column(name = "user_chats", columnDefinition = "bigint[]")
    Set<Long> userChats;




}
