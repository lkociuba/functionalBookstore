package com.example.functionalBookstore.domain.inventory.core.ports.incoming;

import com.example.functionalBookstore.domain.inventory.core.model.Book;

import java.util.Optional;

public interface GetBookFromBookId {
    Optional<Book> getBookFromBookId(Long bookId);
}
