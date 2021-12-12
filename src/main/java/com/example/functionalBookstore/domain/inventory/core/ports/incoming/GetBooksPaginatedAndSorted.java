package com.example.functionalBookstore.domain.inventory.core.ports.incoming;

import com.example.functionalBookstore.domain.inventory.core.model.Book;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface GetBooksPaginatedAndSorted {
    Optional<Page<Book>> getBooksPaginatedAndSorted(
            int pageNumber, int pageSize, String sortField, String sortDirection);
}
