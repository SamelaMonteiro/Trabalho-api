package com.server.booksummar.mapper;

import com.server.booksummar.domain.Likes;
import com.server.booksummar.dto.response.LikesResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LikesMapper {

    @Mapping(target = "user", source = "user")
    public LikesResponse LikesToLikesResponse(Likes likes);

}
