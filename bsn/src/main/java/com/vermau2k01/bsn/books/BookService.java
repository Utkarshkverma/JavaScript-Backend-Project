package com.vermau2k01.bsn.books;

import com.vermau2k01.bsn.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public Integer save(BookRequest request, Authentication connectedUser) {
        Users user = (Users) connectedUser.getPrincipal();
        Books book = bookMapper.toBook(request);
        book.setOwner(user);
        bookRepository.save(book);
        return book.getId();
    }
}
