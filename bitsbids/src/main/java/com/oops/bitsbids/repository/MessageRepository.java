package com.oops.bitsbids.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oops.bitsbids.model.Message;

import java.util.*;

@Repository
public interface MessageRepository extends JpaRepository <Message, Long> {

	@Query("SELECT msg FROM Message msg WHERE msg.post.id = :postId")
	List<Message> findByPost(@Param("postId") Long postId);

	@Query("SELECT msg FROM Message msg WHERE msg.owner.id = :userId OR msg.bidder.id = :userId")
	List<Message> findByUser(@Param("userId") Long userId);
}
