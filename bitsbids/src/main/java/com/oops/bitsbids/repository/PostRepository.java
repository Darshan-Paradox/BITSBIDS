package com.oops.bitsbids.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oops.bitsbids.model.User;
import com.oops.bitsbids.model.Post;

import java.util.*;

@Repository
public interface PostRepository extends JpaRepository <Post, Long> {

	//add custom search

	//Post getById(Long id);

	@Query("SELECT p FROM Post p WHERE p.id >= :start AND p.id <= :end")
	List<Post> findPostByIdLimits(@Param("start") Long begin, @Param("end") Long end);

	List<Post> findByUser(User user);

}
