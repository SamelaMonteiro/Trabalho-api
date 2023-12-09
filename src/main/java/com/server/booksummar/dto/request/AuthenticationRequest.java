package com.server.booksummar.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AuthenticationRequest {

    @NotBlank(message = "O campo 'email' não pode estar em branco.")
    @Email(message = "O campo 'email' deve ser um endereço de e-mail válido.")
    private String login;

    @NotBlank(message = "O campo 'senha' não pode estar em branco.")
    @Size(min = 8, max = 20, message = "A 'senha' deve ter entre 8 e 20 caracteres")
    private String password;

}
