package com.server.booksummar.dto.response;

import java.util.UUID;

public record LoginResponse(String token, UUID userId) {

}
