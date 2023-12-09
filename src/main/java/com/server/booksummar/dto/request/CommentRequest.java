package com.server.booksummar.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class CommentRequest {

    @NotBlank(message = "O campo 'texto' não pode estar em branco.")
    private String text;

    private UUID idUser;

    private UUID idBookSummary;

}
