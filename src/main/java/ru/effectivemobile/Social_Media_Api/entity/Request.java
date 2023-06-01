package ru.effectivemobile.Social_Media_Api.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

/**
 * Cущность для запросов в друзья
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Requests")
@Entity
public class Request {

    /**
     * Id заявки
     */
    @Id
    @Column(name = "request_id")
    Long requestId;

    /**
     * К кому запрос заявки
     */
    @ManyToOne
    @JoinColumn(name = "request_id_to")
    User requestIdTo;

    /**
     * От кого запрос заявки
     */
    @ManyToOne
    @JoinColumn(name = "request_id_from")
    User requestIdFrom;

    /**
     * Статус заявки
     */
    @Column(name = "status")
    boolean status;

}
