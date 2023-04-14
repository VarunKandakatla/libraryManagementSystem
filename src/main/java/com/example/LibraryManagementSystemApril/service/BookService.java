package com.example.LibraryManagementSystemApril.service;

import com.example.LibraryManagementSystemApril.DTOs.BookRequestDTO;
import com.example.LibraryManagementSystemApril.DTOs.BookResponseDTO;
import com.example.LibraryManagementSystemApril.Exceptions.AuthorNotFound;

import java.util.List;

public interface BookService {
    BookResponseDTO addBook(BookRequestDTO bookRequestDTO) throws AuthorNotFound;

    List<BookResponseDTO> getAllBooksById(int id);

    List<BookResponseDTO> getAll();
}
