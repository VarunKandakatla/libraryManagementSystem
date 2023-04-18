package com.example.LibraryManagementSystemApril.service;

import com.example.LibraryManagementSystemApril.DTOs.AuthorRequestDTO;
import com.example.LibraryManagementSystemApril.DTOs.AuthorResponseDTO;
import com.example.LibraryManagementSystemApril.DTOs.BookResponseDTO;
import com.example.LibraryManagementSystemApril.Enum.Department;
import com.example.LibraryManagementSystemApril.Exceptions.AuthorNotFound;
import com.example.LibraryManagementSystemApril.entity.Author;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AuthorService {
    public AuthorResponseDTO addAuthor(AuthorRequestDTO authorRequestDTO);

    AuthorResponseDTO getAuthorById(int id) throws AuthorNotFound;

    List<AuthorResponseDTO> getAuthorByName(String string);

    List<AuthorResponseDTO> getall();
    List<String> getCountofBooks();

    List<BookResponseDTO> getTotalBooksbyAuthor(int id);

    AuthorResponseDTO updateNamebyId(int id,String name);

    String DeleteAuthor(int id);

    String DeleteAll();
}
