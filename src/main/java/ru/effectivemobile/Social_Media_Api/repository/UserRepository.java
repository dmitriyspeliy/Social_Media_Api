package ru.effectivemobile.Social_Media_Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.effectivemobile.Social_Media_Api.entity.User;

import java.util.Optional;

/**
 * Реозиторий для юзера
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserEmail(String userEmail);
}
