package com.example.LibraryManagementSystemApril.service;

import com.example.LibraryManagementSystemApril.DTOs.TransactionRequestDto;
import com.example.LibraryManagementSystemApril.DTOs.TransactionResponseDto;
import com.example.LibraryManagementSystemApril.Exceptions.BookNotFound;
import com.example.LibraryManagementSystemApril.Exceptions.StudentNotFound;
import com.example.LibraryManagementSystemApril.entity.Transaction;

public interface TransactionService {
    TransactionResponseDto getBookbyIds(TransactionRequestDto transactionRequestDto, Transaction transaction) throws StudentNotFound, BookNotFound;

    String DeleteAll();

    TransactionResponseDto returnBooks(TransactionRequestDto transactionRequestDto, Transaction transaction) throws StudentNotFound, BookNotFound;
}
