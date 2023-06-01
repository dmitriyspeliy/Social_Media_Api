package ru.effectivemobile.Social_Media_Api.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Сущность поста
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Posts")
@Entity
public class Post {

    /**
     * Идентификатор поста
     */
    @Id
    @Column(name = "post_id")
    Long postId;

    /**
     * Какому юзеры принадлежим
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    User postList;

    /**
     * Заголовок поста
     */
    @Column(name = "title")
    String title;

    /**
     * Картинка поста
     */
    @Column(name = "path")
    String path;

    /**
     * Дата создания поста
     */
    @Column(name = "created_at")
    LocalDateTime createdAt;

}
