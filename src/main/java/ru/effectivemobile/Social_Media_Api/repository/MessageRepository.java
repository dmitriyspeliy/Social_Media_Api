package ru.effectivemobile.Social_Media_Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.effectivemobile.Social_Media_Api.entity.Message;


/**
 * Реозиторий для сообщений
 */

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>  {
}

