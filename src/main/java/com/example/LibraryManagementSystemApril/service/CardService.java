package com.example.LibraryManagementSystemApril.service;

import com.example.LibraryManagementSystemApril.DTOs.BookResponseDTO;

import java.util.List;

public interface CardService {
    List<BookResponseDTO> getBooks(int id);
}
