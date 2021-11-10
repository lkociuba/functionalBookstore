package com.example.functionalBookstore.domain.inventory.infrastructure;

import com.example.functionalBookstore.domain.inventory.core.model.Book;
import com.example.functionalBookstore.domain.inventory.core.ports.outgoing.BookDatabase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookDatabaseAdapter implements BookDatabase {

    private final BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }
}
