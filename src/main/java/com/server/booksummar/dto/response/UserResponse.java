package com.server.booksummar.dto.response;

import com.server.booksummar.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponse {

    private UUID id;

    private String name;

    private String login;

    private String avatarURL;

    private UserRole role;

}
