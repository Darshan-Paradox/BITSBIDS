package com.oops.bitsbids.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oops.bitsbids.model.Bid;

import java.util.*;

@Repository
public interface BidRepository extends JpaRepository <Bid, Long> {

	@Query("SELECT b FROM Bid b WHERE b.post.id = :postId")
	List<Bid> findByPost(@Param("postId") Long postId);

}
