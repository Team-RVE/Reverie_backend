package com.example.rve.domain.post.repository;

import com.example.rve.domain.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
