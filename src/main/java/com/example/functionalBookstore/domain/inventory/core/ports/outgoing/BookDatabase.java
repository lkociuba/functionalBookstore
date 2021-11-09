package com.example.functionalBookstore.domain.inventory.core.ports.outgoing;

import com.example.functionalBookstore.domain.inventory.core.model.Book;

public interface BookDatabase {
    Book save(Book book);
}
