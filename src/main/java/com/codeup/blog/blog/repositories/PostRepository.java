package com.codeup.blog.blog.repositories;

import com.codeup.blog.blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findByTitle(String title);

    @Query("FROM Post WHERE p.id LIKE ?1")
    Post getPostById(long id);

    @Query("SELECT title FROM p.id WHERE LENGTH(title) < 10")
    List<String> getPostsOfCertainTitleLength();

    @Query(nativeQuery = true, value = "SELECT title FROM Post WHERE LENGTH(title) < 10")
    List<String> getPostsOfCertainTitleLengthNative();

}