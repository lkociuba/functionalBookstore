package com.example.functionalBookstore.domain.inventory.infrastructure;

import com.example.functionalBookstore.domain.inventory.core.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BookDatabaseAdapterTest {

    @Mock
    private BookRepository bookRepositoryMock;

    @InjectMocks
    private BookDatabaseAdapter bookDatabaseAdapter;

    @Test
    void shouldReturnSavedBookAfterAddNewBookToDatabase() {
        //given
        Book book = new Book(
                "Geographical Atlas",
                "A lot of useful maps",
                new BigDecimal(56)
        );

        given(bookRepositoryMock.save(Mockito.any(Book.class))).willReturn(book);

        //when
        Book result = bookDatabaseAdapter.save(new Book());

        //then
        assertThat(result, is(book));
    }

    @Test
    void shouldGetEmptyOptionalFromNoDatabaseConnection() {
        //when
        Optional<Page<Book>> result = bookDatabaseAdapter.
                getBooksPaginatedAndSorted(1, 2, "name", "asc");

        //then
        assertThat(result, is(Optional.empty()));
    }

    @Test
    void shouldReturnBookFromBookId() {
        //given
        Book book = new Book(
                "Geographical Atlas",
                "A lot of useful maps",
                new BigDecimal(56)
        );

        given(bookRepositoryMock.findById(anyLong())).willReturn(Optional.of(book));

        //when
        Optional<Book> result = bookDatabaseAdapter.findBookById(1L);

        //then
        assertThat(result, is(Optional.of(book)));
    }

    @Test
    void shouldReturnEmptyOptionalFromNoDatabaseConnection() {
        //when
        Optional<Book> result = bookDatabaseAdapter.findBookById(1L);

        assertThat(result, is(Optional.empty()));
    }
}