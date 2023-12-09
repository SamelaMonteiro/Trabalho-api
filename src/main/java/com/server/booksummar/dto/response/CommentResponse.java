package com.server.booksummar.dto.response;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class CommentResponse {

    private UUID id;

    private String text;

    private UserResponse user;

    private ZonedDateTime commentDate;

    private UUID bookSummaryId;

}
