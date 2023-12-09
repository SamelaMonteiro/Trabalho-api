package com.server.booksummar.mapper;

import com.server.booksummar.domain.Comment;
import com.server.booksummar.dto.response.CommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "user", source = "user")
    public CommentResponse CommentToCommentResponse(Comment comment);

}
