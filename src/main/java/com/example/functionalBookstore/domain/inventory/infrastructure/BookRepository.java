package com.example.functionalBookstore.domain.inventory.infrastructure;

import com.example.functionalBookstore.domain.inventory.core.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
