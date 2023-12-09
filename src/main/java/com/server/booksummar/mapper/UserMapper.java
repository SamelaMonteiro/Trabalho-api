package com.server.booksummar.mapper;

import com.server.booksummar.domain.User;
import com.server.booksummar.dto.request.UserRequest;
import com.server.booksummar.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    public User userRequestToUser(UserRequest userRequest);

    public UserResponse userToUserResponse(User user);

    public void userUpdate(UserRequest userRequest, @MappingTarget User user);

}

