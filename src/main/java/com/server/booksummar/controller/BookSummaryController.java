package com.server.booksummar.controller;

import com.server.booksummar.dto.request.BookSummaryRequest;
import com.server.booksummar.dto.request.CommentRequest;
import com.server.booksummar.dto.response.BookSummaryResponse;
import com.server.booksummar.dto.response.CommentResponse;
import com.server.booksummar.dto.response.LikesResponse;
import com.server.booksummar.repository.BookSummaryRepository;
import com.server.booksummar.service.BookSummaryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bookSummary")
public class BookSummaryController {

    @Autowired
    private BookSummaryRepository bookSummaryRepository;
    @Autowired
    private BookSummaryService bookSummaryService;

    @PostMapping("/{userId}")
    @Operation(summary = "Cria um novo feedback de livro")
    public ResponseEntity<BookSummaryResponse> create(@RequestBody @Valid BookSummaryRequest bookSummaryRequest, @PathVariable UUID userId) {
        BookSummaryResponse bookSummaryResponse = bookSummaryService.create(bookSummaryRequest, userId);
        return ResponseEntity.created(URI.create("/bookSummary" + bookSummaryResponse.getId())).body(bookSummaryResponse);
    }

    @GetMapping
    @Operation(summary = "Busca todos os feedback de livros")
    public ResponseEntity<List<BookSummaryResponse>> findAll() {
        return ResponseEntity.ok(bookSummaryService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um feedback pelo Id")
    public ResponseEntity<BookSummaryResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(bookSummaryService.findById(id));
    }

    @PutMapping("{bookId}/{userId}")
    @Operation(summary = "Atualiza um feedback pelo Id")
    public ResponseEntity<BookSummaryResponse> update(@RequestBody @Valid BookSummaryRequest bookSummaryRequest, @PathVariable UUID bookId, @PathVariable UUID userId) {
        return ResponseEntity.ok(bookSummaryService.update(bookSummaryRequest, bookId, userId));
    }

    @GetMapping("/bookName/{bookName}")
    @Operation(summary = "Busca feedback pelo nome do livro")
    public ResponseEntity<List<BookSummaryResponse>> findByBookName(@PathVariable String bookName) {
        return ResponseEntity.ok(bookSummaryService.findByBookName(bookName));
    }

    @GetMapping("/bookAuthor/{bookAuthor}")
    @Operation(summary = "Busca feedback pelo nome do autor")
    public ResponseEntity<List<BookSummaryResponse>> findByBookAuthor(@PathVariable String bookAuthor) {
        return ResponseEntity.ok(bookSummaryService.findByBookAuthor(bookAuthor));
    }

    @GetMapping("/userId/{userId}")
    @Operation(summary = "Busca feedback pelo Id do usuário")
    public ResponseEntity<List<BookSummaryResponse>> findByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(bookSummaryService.findByUserId(userId));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deleta um feedback pelo Id")
    public void delete(@PathVariable UUID id) {
        bookSummaryService.delete(id);
        ResponseEntity.ok();
    }

    @PostMapping("/sendSummaryEmail/{bookId}/{userId}")
    @Operation(summary = "Envia um feedback de um livro para um usuário")
    public ResponseEntity<BookSummaryResponse> sendSummaryEmail(@PathVariable UUID bookId, @PathVariable UUID userId) {
        BookSummaryResponse bookSummaryResponse = bookSummaryService.sendSummaryEmail(bookId, userId);
        return ResponseEntity.created(URI.create("/bookSummary" + bookSummaryResponse.getId())).body(bookSummaryResponse);
    }

    @PostMapping("/commentBookSummary")
    @Operation(summary = "Comenta um feedback de um livro")
    public ResponseEntity<CommentResponse> commentBookSummary(@RequestBody @Valid CommentRequest commentRequest) {
        CommentResponse commentResponse = bookSummaryService.commentSummary(commentRequest);
        return ResponseEntity.created(URI.create("/CommentResponse" + commentResponse.getId())).body(commentResponse);
    }

    @PostMapping("/likeBookSummary/{bookId}/{userId}")
    @Operation(summary = "Curte um feedback de um livro")
    public ResponseEntity<LikesResponse> likeBookSummary(@PathVariable UUID bookId, @PathVariable UUID userId) {
        LikesResponse likesResponse = bookSummaryService.likeBookSummary(bookId, userId);
        return ResponseEntity.created(URI.create("/LikesResponse" + likesResponse.getId())).body(likesResponse);
    }
}