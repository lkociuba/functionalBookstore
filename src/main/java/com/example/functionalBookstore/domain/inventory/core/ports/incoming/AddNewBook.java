package com.example.functionalBookstore.domain.inventory.core.ports.incoming;

import com.example.functionalBookstore.domain.inventory.core.model.Book;
import com.example.functionalBookstore.domain.inventory.core.model.BookAddCommand;

public interface AddNewBook {
    Book save(BookAddCommand bookAddCommand);
}
