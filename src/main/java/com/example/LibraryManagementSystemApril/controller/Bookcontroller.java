package com.example.LibraryManagementSystemApril.controller;

import com.example.LibraryManagementSystemApril.DTOs.BookRequestDTO;
import com.example.LibraryManagementSystemApril.DTOs.BookResponseDTO;
import com.example.LibraryManagementSystemApril.Exceptions.AuthorNotFound;
import com.example.LibraryManagementSystemApril.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/book")
@RestController
public class Bookcontroller {
    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public BookResponseDTO addBook(@RequestBody BookRequestDTO bookRequestDTO) throws AuthorNotFound {
        return bookService.addBook(bookRequestDTO);
    }

    @GetMapping("/getAllBooksById")
    public List<BookResponseDTO> getAllBooksByAuthor(@RequestParam("id") int id)
    {
        return bookService.getAllBooksById(id);
    }

    @GetMapping("/getall")
    public List<BookResponseDTO> getAll()
    {
        return bookService.getAll();
    }
}
