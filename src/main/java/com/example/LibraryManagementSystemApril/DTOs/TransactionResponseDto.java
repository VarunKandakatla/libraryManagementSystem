package com.example.LibraryManagementSystemApril.DTOs;

import com.example.LibraryManagementSystemApril.Enum.TransactionStatus;
import com.example.LibraryManagementSystemApril.entity.Cards;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TransactionResponseDto {

    private String date;
    private String transactionNo;
    private TransactionStatus status;
    private String message;
}
