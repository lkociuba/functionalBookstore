package com.example.functionalBookstore.domain.inventory.application;

import com.example.functionalBookstore.domain.inventory.core.model.BookAddCommand;
import com.example.functionalBookstore.domain.inventory.core.ports.incoming.AddNewBook;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/addBook")
@RequiredArgsConstructor
public class AddBookController {

    private final AddNewBook addNewBook;

    @GetMapping
    public String showAddNewBookPage() {
        return "addBook";
    }

    @ModelAttribute("book")
    public BookAddCommand bookAddCommand() {
        return new BookAddCommand();
    }

    @PostMapping
    public String addNewBook(@ModelAttribute("book") BookAddCommand bookAddCommand){
        addNewBook.save(bookAddCommand);
        return "redirect:/addBook?success";
    }
}
