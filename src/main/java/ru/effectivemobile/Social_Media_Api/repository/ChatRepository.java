package ru.effectivemobile.Social_Media_Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.effectivemobile.Social_Media_Api.entity.Chat;


/**
 * Реозиторий для чата
 */

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long>{
}

