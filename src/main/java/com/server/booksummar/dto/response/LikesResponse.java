package com.server.booksummar.dto.response;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class LikesResponse {

    private UUID id;

    private UserResponse user;

    private ZonedDateTime likeDate;

    private UUID bookSummaryId;

}
