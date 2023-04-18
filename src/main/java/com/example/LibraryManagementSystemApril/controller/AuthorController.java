package com.example.LibraryManagementSystemApril.controller;

import com.example.LibraryManagementSystemApril.DTOs.AuthorRequestDTO;
import com.example.LibraryManagementSystemApril.DTOs.AuthorResponseDTO;
import com.example.LibraryManagementSystemApril.DTOs.BookResponseDTO;
import com.example.LibraryManagementSystemApril.Enum.Department;
import com.example.LibraryManagementSystemApril.Exceptions.AuthorNotFound;
import com.example.LibraryManagementSystemApril.entity.Author;
import com.example.LibraryManagementSystemApril.entity.Book;
import com.example.LibraryManagementSystemApril.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;
    @PostMapping("/add")
    public AuthorResponseDTO addAuthor(@RequestBody AuthorRequestDTO authorRequestDTO)
    {
        return  authorService.addAuthor(authorRequestDTO);
    }

    @GetMapping("/getById")
    public AuthorResponseDTO getAuthorById(@RequestParam("id") int id) throws AuthorNotFound {
        return authorService.getAuthorById(id);
    }

    @GetMapping("/getByName/{name}")
    public List< AuthorResponseDTO > getAuthorByName(@PathVariable("name") String string)
    {
        return authorService.getAuthorByName(string);
    }

    @GetMapping("/getall")
    public List<AuthorResponseDTO> getall()
    {
        return authorService.getall();
    }

    @GetMapping("/getCountofBooksByAuthors")
    public List<String> getCountofBooks()
    {
        return authorService.getCountofBooks();
    }

    @GetMapping("/getTotalBooksbyAuthor")
    public List<BookResponseDTO> getAllBooksbyAuthor(@RequestParam("id") int id)
    {
        return authorService.getTotalBooksbyAuthor(id);
    }

    @PutMapping("/updateNamebyId")
    public AuthorResponseDTO updateNamebyId(@RequestParam("id") int id,String name)
    {
        return authorService.updateNamebyId(id,name);
    }

    @DeleteMapping("/deleteAuthorById")
    public String deleteAuthor(@RequestParam("id") int id)
    {
        return authorService.DeleteAuthor(id);
    }

    @DeleteMapping("/deleteAll")
    public String DeleteAll()
    {
        return authorService.DeleteAll();
    }
}
