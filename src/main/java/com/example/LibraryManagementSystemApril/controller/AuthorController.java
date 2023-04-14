package com.example.LibraryManagementSystemApril.controller;

import com.example.LibraryManagementSystemApril.DTOs.AuthorRequestDTO;
import com.example.LibraryManagementSystemApril.DTOs.AuthorResponseDTO;
import com.example.LibraryManagementSystemApril.Exceptions.AuthorNotFound;
import com.example.LibraryManagementSystemApril.entity.Author;
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
}
