package com.example.functionalBookstore.domain.inventory.application;

import com.example.functionalBookstore.domain.inventory.core.model.Book;
import com.example.functionalBookstore.domain.inventory.core.ports.incoming.GetBooksPaginatedAndSorted;
import com.example.functionalBookstore.domain.inventory.core.ports.outgoing.BookDatabase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BookListController {

    private final GetBooksPaginatedAndSorted getBooksPaginatedAndSorted;

    @GetMapping("/paginatedBookList")
    public String findPaginatedBookList(ModelMap model) {
        return showPaginatedBookList(1, "name", "asc", model);
    }

    @GetMapping("/page/{pageNumber}")
    public String showPaginatedBookList(
            @PathVariable(value = "pageNumber") int pageNumber,
            @RequestParam("sortField") String sortField,
            @RequestParam("sortDirection") String sortDirection,
            ModelMap model) {

        var pageSize = 5;

        Optional<Page<Book>> page = getBooksPaginatedAndSorted.getBooksPaginatedAndSorted(
                pageNumber, pageSize, sortField, sortDirection);
        List<Book> bookList = page.get().getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.get().getTotalPages());
        model.addAttribute("totalItems", page.get().getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

        model.addAttribute("bookList", bookList);

        return "paginatedBookList";
    }

}
