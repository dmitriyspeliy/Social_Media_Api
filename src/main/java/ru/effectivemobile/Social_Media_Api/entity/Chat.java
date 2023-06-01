package ru.effectivemobile.Social_Media_Api.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;


/**
 * Cущность чата
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Chats")
@Entity
public class Chat {

    /**
     * Id чата
     */
    @Id
    @Column(name = "chat_id")
    Long chatId;

    /**
     * Первый участник переписки
     */
    @ManyToOne
    @JoinColumn(name = "first_id")
    User firstId;

    /**
     * Второй участник переписки
     */
    @ManyToOne
    @JoinColumn(name = "second_id")
    User secondId;

    /**
     * Сообщения в чате
     */
    @OneToMany(mappedBy = "messageList")
    List<Message> messageList;
}