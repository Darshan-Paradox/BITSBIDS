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

	@Query(value="SELECT p.* FROM Post p WHERE p.user_id != :userId ORDER BY p.id DESC OFFSET :offset ROWS FETCH FIRST :size ROWS ONLY", nativeQuery=true)
	List<Post> findPostByIdLimits(@Param("offset") Long offset, @Param("size") Long size, @Param("userId") Long userId);

	List<Post> findByUser(User user);

}
