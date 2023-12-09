package com.server.booksummar.mapper;

import com.server.booksummar.domain.BookSummary;
import com.server.booksummar.dto.request.BookSummaryRequest;
import com.server.booksummar.dto.response.BookSummaryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookSummaryMapper {

    @Mapping(target = "user", ignore = true)
    public BookSummary bookSummaryRequestToBookSummary(BookSummaryRequest bookSummaryRequest);

    @Mapping(target = "user", source = "user")
    @Mapping(target = "comments", source = "comments")
    public BookSummaryResponse bookSummaryToBookSummaryResponse(BookSummary bookSummary);

    public void bookSummaryUpdate(BookSummaryRequest bookSummaryRequest, @MappingTarget BookSummary bookSummary);

}
