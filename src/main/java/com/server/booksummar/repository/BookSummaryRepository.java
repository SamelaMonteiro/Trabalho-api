package com.server.booksummar.repository;

import com.server.booksummar.domain.BookSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookSummaryRepository extends JpaRepository<BookSummary, UUID> {

    List<BookSummary> findByBookNameContaining(String bookName);

    List<BookSummary> findByBookAuthorContaining(String bookAuthor);

    List<BookSummary> findByUserId(UUID userId);

}