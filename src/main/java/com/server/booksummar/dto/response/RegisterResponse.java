package com.server.booksummar.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterResponse {

    private UUID id;

    private String name;

    private String login;

    private String avatarURL;

}
