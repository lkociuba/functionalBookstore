package com.example.functionalBookstore.domain.inventory.core;

import com.example.functionalBookstore.domain.inventory.core.model.Book;
import com.example.functionalBookstore.domain.inventory.core.model.BookAddCommand;
import com.example.functionalBookstore.domain.inventory.core.ports.outgoing.BookDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookDatabase bookDatabaseMock;

    @InjectMocks
    private BookService bookService;

    @Test
    void shouldAddNewBook() {
        //given
        Book book = new Book(
                "Geographical Atlas",
                "A lot of usefull maps",
                new BigDecimal(56));

        given(bookDatabaseMock.save(Mockito.any(Book.class))).willReturn(book);

        //when
        Book result = bookService.save(new BookAddCommand());

        //then
        assertThat(result, is(book));
    }

    @Test
    void shouldThrowExceptionFromNullBookAddCommand() {
        assertThrows(NullPointerException.class, () ->
                bookService.save(null));
    }
}