package com.example.functionalBookstore.domain.inventory.core.ports.outgoing;

import com.example.functionalBookstore.domain.inventory.core.model.Book;
import org.springframework.data.domain.Page;

public interface BookDatabase {
    Book save(Book book);

    Page<Book> getBooksPaginatedAndSorted(
            int pageNumber, int pageSize, String sortField, String sortDirection);
}
