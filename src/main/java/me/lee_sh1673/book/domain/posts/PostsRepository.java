package me.lee_sh1673.book.domain.posts;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT post FROM Posts post ORDER BY post.id DESC")
    List<Posts> findAllDesc();
}
