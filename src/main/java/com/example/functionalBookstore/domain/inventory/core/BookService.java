package com.example.functionalBookstore.domain.inventory.core;

import com.example.functionalBookstore.domain.inventory.core.model.Book;
import com.example.functionalBookstore.domain.inventory.core.model.BookAddCommand;
import com.example.functionalBookstore.domain.inventory.core.ports.incoming.AddNewBook;
import com.example.functionalBookstore.domain.inventory.core.ports.outgoing.BookDatabase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookService implements AddNewBook {

    private final BookDatabase bookDatabase;

    @Override
    public Book save(BookAddCommand bookAddCommand) {
        Book book = new Book(
                bookAddCommand.getName(),
                bookAddCommand.getDescription(),
                bookAddCommand.getPrice()
        );
        return bookDatabase.save(book);
    }
}
