package com.vermau2k01.bsn.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Books, Integer> {

    Optional<Books> findByTitle(String title);
}
