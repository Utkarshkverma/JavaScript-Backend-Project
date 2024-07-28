package com.vermau2k01.bsn.books;

import org.springframework.security.core.Authentication;

public interface IBookService {

    public Integer save(BookRequest request, Authentication connectedUser);
}
