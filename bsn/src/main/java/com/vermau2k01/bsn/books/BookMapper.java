package com.vermau2k01.bsn.books;

import org.springframework.stereotype.Service;

@Service
public class BookMapper {


    public Books toBook(BookRequest request) {
        return Books
                .builder()
                .id(request.id())
                .title(request.title())
                .authorName(request.authorName())
                .synopsis(request.synopsis())
                .archived(false)
                .sharable(request.sharable())
                .build();
    }
}
