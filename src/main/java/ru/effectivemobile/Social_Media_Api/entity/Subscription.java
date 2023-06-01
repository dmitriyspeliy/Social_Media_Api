package ru.effectivemobile.Social_Media_Api.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

/**
 * Сущность подписки
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Subscriptions")
@Entity
public class Subscription {

    /**
     * Id подписки
     */
    @Id
    @Column(name = "subscription_id")
    Long subscriptionId;

    /**
     * Кто подписался
     */
    @ManyToOne
    @JoinColumn(name = "subscriber_id")
    User subscriberId;

    /**
     * На кого подписались
     */
    @ManyToOne
    @JoinColumn(name = "subscribed_id")
    User subscribedId;

}
