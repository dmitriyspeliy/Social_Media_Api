package ru.effectivemobile.Social_Media_Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.effectivemobile.Social_Media_Api.entity.Post;

/**
 * Реозиторий для постов
 */

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    @Query(nativeQuery = true, value = "SELECT MAX(post_id) FROM posts")
    int findMaxID();
}

