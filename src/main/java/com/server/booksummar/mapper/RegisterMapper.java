package com.server.booksummar.mapper;

import com.server.booksummar.domain.User;
import com.server.booksummar.dto.request.UserRequest;
import com.server.booksummar.dto.response.RegisterResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterMapper {

    public RegisterResponse RegisterRequestToRegisterResponse(UserRequest userRequest);

    public RegisterResponse UserToRegisterResponse(User user);

}
