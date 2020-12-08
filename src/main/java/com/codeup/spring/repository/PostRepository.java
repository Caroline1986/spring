package com.codeup.spring.repository;

import com.codeup.spring.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository <Post, Long>{
    Post findByBody(String body); // mysql> select * from ads where description = ?;
    List<Post> findAllByTitleIsLike(String term);

    Post findPostByTitle(String title);
}
