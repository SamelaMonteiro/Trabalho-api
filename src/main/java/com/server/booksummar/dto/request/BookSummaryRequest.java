package com.server.booksummar.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookSummaryRequest {

    @NotBlank(message = "O campo 'resumo' não pode estar em branco.")
    private String summary;

    @NotBlank(message = "O campo 'nome do livro' não pode estar em branco.")
    private String bookName;

    @NotBlank(message = "O campo 'autor do livro' não pode estar em branco.")
    private String bookAuthor;

    @NotBlank(message = "O campo 'nome do livro' não pode estar em branco.")
    private String bookImage;

}