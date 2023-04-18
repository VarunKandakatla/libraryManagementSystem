package com.example.LibraryManagementSystemApril.controller;

import com.example.LibraryManagementSystemApril.DTOs.TransactionRequestDto;
import com.example.LibraryManagementSystemApril.DTOs.TransactionResponseDto;
import com.example.LibraryManagementSystemApril.Enum.TransactionStatus;
import com.example.LibraryManagementSystemApril.Exceptions.BookNotFound;
import com.example.LibraryManagementSystemApril.Exceptions.StudentNotFound;
import com.example.LibraryManagementSystemApril.entity.Transaction;
import com.example.LibraryManagementSystemApril.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/getBook")
    public TransactionResponseDto getBookbyIDs(@RequestBody TransactionRequestDto transactionRequestDto) {
        Transaction transaction=new Transaction();
        transaction.setStatus(TransactionStatus.FAILED);

        TransactionResponseDto transactionResponseDto=new TransactionResponseDto();
        try{
           transactionResponseDto= transactionService.getBookbyIds(transactionRequestDto,transaction);
        }
        catch (Exception e)
        {
            transactionResponseDto.setMessage(e.getMessage());
            transactionResponseDto.setTransactionNo(transaction.getTransactionId());
            transactionResponseDto.setDate(transaction.getDate().toString());
            transactionResponseDto.setStatus(transaction.getStatus());
        }
        return  transactionResponseDto;

    }

    @PutMapping("/returnBook")
    public TransactionResponseDto returnBooks(@RequestBody TransactionRequestDto transactionRequestDto) throws BookNotFound, StudentNotFound {
        Transaction transaction=new Transaction();
        transaction.setStatus(TransactionStatus.FAILED);

        TransactionResponseDto transactionResponseDto=new TransactionResponseDto();

        try
        {
            transactionResponseDto=transactionService.returnBooks(transactionRequestDto,transaction);
        }
        catch (Exception e)
        {
            transactionResponseDto.setMessage(e.getMessage());
            transactionResponseDto.setTransactionNo(transaction.getTransactionId());
            transactionResponseDto.setDate(transaction.getDate().toString());
            transactionResponseDto.setStatus(transaction.getStatus());
        }
        return transactionResponseDto;
    }

    @DeleteMapping("/deleteAll")
    public String DeleteAll()
    {
        return transactionService.DeleteAll();

    }
}
