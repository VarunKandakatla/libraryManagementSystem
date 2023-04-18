package com.example.LibraryManagementSystemApril.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionRequestDto {
    private int bookId;
    private int cardId;
}
