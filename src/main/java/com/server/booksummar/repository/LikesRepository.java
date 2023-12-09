package com.server.booksummar.repository;

import com.server.booksummar.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LikesRepository extends JpaRepository<Likes, UUID> {
}
