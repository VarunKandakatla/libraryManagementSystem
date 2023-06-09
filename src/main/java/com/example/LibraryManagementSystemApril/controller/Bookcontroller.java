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

    @GetMapping("/getBookbyName")
    public List<BookResponseDTO> getBookbyName(@RequestParam("name") String name)
    {
        return bookService.getByname(name);
    }

    @PutMapping("/resetBooks")
    public String ResetBooks()
    {
        return bookService.resetAllBooks();
    }

    @GetMapping("/getBookbyId")
    public BookResponseDTO getBookbyId(@RequestParam("id") int id)
    {
        return bookService.getBookbyId(id);
    }



    @PutMapping("/updateName/{id}/{name}")
    public BookResponseDTO updateNamebyId(@PathVariable("id")int id,@PathVariable("name") String newName)
    {
        return bookService.updateName(id,newName);
    }

    @PutMapping("/updatePriceById")
   public BookResponseDTO updatePrice(@RequestParam int id, int price)
    {
        return bookService.updatePrice(id,price);
    }

    @DeleteMapping("/deleteBook")
    public String deleteBook(@RequestParam("id") int id)
    {
        return bookService.DeleteBook(id);
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll()
    {
        return bookService.deleteAll();
    }

}
