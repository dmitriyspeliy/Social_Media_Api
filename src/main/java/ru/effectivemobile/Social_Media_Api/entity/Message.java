package ru.effectivemobile.Social_Media_Api.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Cущность для сообщение в чате
 */


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Messages")
@Entity
public class Message {

    /**
     * Идентификатор сообщения
     */
    @Id
    @Column(name = "message_id")
    Long messageId;

    /**
     * Идентификатор чата
     */
    @ManyToOne
    @JoinColumn(name = "chat_id")
    Chat messageList;

    /**
     * Текст сообщения
     */
    @Column(name = "message_text")
    String messageText;

    /**
     * Дата создания поста
     */
    @Column(name = "created_at")
    LocalDateTime createdAt;
}
