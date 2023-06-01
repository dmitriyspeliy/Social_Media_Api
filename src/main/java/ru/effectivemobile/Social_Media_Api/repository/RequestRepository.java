package ru.effectivemobile.Social_Media_Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.effectivemobile.Social_Media_Api.entity.Request;

/**
 * Реозиторий для запросов в друзья
 */

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
}
