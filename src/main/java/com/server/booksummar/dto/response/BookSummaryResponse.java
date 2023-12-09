package com.server.booksummar.dto.response;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class BookSummaryResponse {

    private UUID id;

    private String summary;

    private String bookName;

    private String bookAuthor;

    private String bookImage;

    private ZonedDateTime summaryDate;

    private UserResponse user;

    private List<CommentResponse> comments;

    private List<LikesResponse> likes;

}