package com.server.booksummar.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PasswordRecoveryRequest {

    @NotBlank(message = "O campo 'email' não pode estar em branco.")
    @Email(message = "O campo 'email' deve ser um endereço de e-mail válido.")
    private String login;

}
