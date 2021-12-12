package com.example.functionalBookstore.domain.inventory.core;

import com.example.functionalBookstore.domain.inventory.core.model.Book;
import com.example.functionalBookstore.domain.inventory.core.model.BookAddCommand;
import com.example.functionalBookstore.domain.inventory.core.ports.incoming.AddNewBook;
import com.example.functionalBookstore.domain.inventory.core.ports.incoming.GetBookById;
import com.example.functionalBookstore.domain.inventory.core.ports.incoming.GetBooksPaginatedAndSorted;
import com.example.functionalBookstore.domain.inventory.core.ports.outgoing.BookDatabase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.Optional;

@RequiredArgsConstructor
public class BookService implements AddNewBook, GetBookById, GetBooksPaginatedAndSorted {

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

    @Override
    public Optional<Book> getBookById(Long bookId) {
        return bookDatabase.findBookById(bookId);
    }

    @Override
    public Optional<Page<Book>> getBooksPaginatedAndSorted(
            int pageNumber, int pageSize, String sortField, String sortDirection) {
        return bookDatabase.getBooksPaginatedAndSorted(
                pageNumber, pageSize, sortField, sortDirection);
    }
}
