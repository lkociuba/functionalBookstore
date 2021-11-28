package com.example.functionalBookstore.domain.inventory.core.ports.incoming;

import com.example.functionalBookstore.domain.inventory.core.model.Book;

public interface GetBookById {
    Book getBookById(Long bookId);
}
