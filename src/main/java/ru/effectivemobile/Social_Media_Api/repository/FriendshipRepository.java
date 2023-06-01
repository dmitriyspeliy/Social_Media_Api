package ru.effectivemobile.Social_Media_Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.effectivemobile.Social_Media_Api.entity.Friendship;


/**
 * Реозиторий для дружбы
 */


@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
}
