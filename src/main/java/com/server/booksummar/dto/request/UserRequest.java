package com.server.booksummar.dto.request;

import com.server.booksummar.domain.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {

    @NotBlank(message = "O campo 'nome' não pode estar em branco.")
    @Size(min = 3, max = 30, message = "O campo 'nome' deve ter entre 3 e 30 caracteres")
    private String name;

    @NotBlank(message = "O campo 'email' não pode estar em branco.")
    @Email(message = "O campo 'email' deve ser um endereço de e-mail válido.")
    private String login;

    private String avatarURL;

    @NotBlank(message = "O campo 'senha' não pode estar em branco.")
    @Size(min = 8, max = 20, message = "A 'senha' deve ter entre 8 e 20 caracteres")
    private String password;

    private UserRole role;

}
