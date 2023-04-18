package com.example.LibraryManagementSystemApril.service;

import com.example.LibraryManagementSystemApril.DTOs.BookRequestDTO;
import com.example.LibraryManagementSystemApril.DTOs.BookResponseDTO;
import com.example.LibraryManagementSystemApril.Exceptions.AuthorNotFound;

import java.util.List;

public interface BookService {
    BookResponseDTO addBook(BookRequestDTO bookRequestDTO) throws AuthorNotFound;

    List<BookResponseDTO> getAllBooksById(int id);

    List<BookResponseDTO> getAll();

    List<BookResponseDTO> getByname(String name);

    BookResponseDTO getBookbyId(int id);

    BookResponseDTO updateName(int id, String newName);

    BookResponseDTO updatePrice(int id, int price);

    String DeleteBook(int id);

    String deleteAll();

    List<BookResponseDTO> getBooksofStudent(int id);
    String resetAllBooks();
}
