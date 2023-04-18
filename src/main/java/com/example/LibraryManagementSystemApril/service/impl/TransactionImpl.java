package com.example.LibraryManagementSystemApril.service.impl;

import com.example.LibraryManagementSystemApril.DTOs.TransactionRequestDto;
import com.example.LibraryManagementSystemApril.DTOs.TransactionResponseDto;
import com.example.LibraryManagementSystemApril.Enum.Status;
import com.example.LibraryManagementSystemApril.Enum.TransactionStatus;
import com.example.LibraryManagementSystemApril.Exceptions.BookNotFound;
import com.example.LibraryManagementSystemApril.Exceptions.StudentNotFound;
import com.example.LibraryManagementSystemApril.entity.Book;
import com.example.LibraryManagementSystemApril.entity.Cards;
import com.example.LibraryManagementSystemApril.entity.Transaction;
import com.example.LibraryManagementSystemApril.repository.BookRepository;
import com.example.LibraryManagementSystemApril.repository.CardsRepository;
import com.example.LibraryManagementSystemApril.repository.TransactionRepository;
import com.example.LibraryManagementSystemApril.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service


public class TransactionImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardsRepository cardsRepository;

    @Override
    public TransactionResponseDto getBookbyIds(TransactionRequestDto transactionRequestDto, Transaction transaction) throws StudentNotFound, BookNotFound {
        Cards cards;
        try
        {
            cards=cardsRepository.findById(transactionRequestDto.getCardId()).get();
        }
        catch (Exception e)
        {
            transactionRepository.save(transaction);
            throw new StudentNotFound("Invalid Card Details");
        }

        if(cards.getStatus()!=Status.ACTIVATED)
        {
            transactionRepository.save(transaction);
            throw new StudentNotFound("No Access for the card");
        }

        Book book;
        try
        {
            book=bookRepository.findById(transactionRequestDto.getBookId()).get();
        }
        catch ( Exception e)
        {
            transactionRepository.save(transaction);
            throw new BookNotFound("Invalid Book Id");
        }

        if(book.isBookIssued()==true)
        {
            transactionRepository.save(transaction);
            throw new BookNotFound("Book already issued");
        }

        transaction.setStatus(TransactionStatus.SUCCESSFULL);
        transaction.setBook(book);
        transaction.setCards(cards);

        book.getTransactions().add(transaction);
        book.setCards(cards);
        book.setBookIssued(true);

        cards.getTransactions().add(transaction);
        cards.getBooks().add(book);
        cardsRepository.save(cards);

        TransactionResponseDto transactionResponseDto=TransactionToDTOs(transaction);
        transactionResponseDto.setMessage("Successfully issued the book: "+book.getTitle());
        return transactionResponseDto;
    }

    @Override
    public String DeleteAll() {
        transactionRepository.deleteAll();
        return "Deleted All Transactions";
    }

    @Override
    public TransactionResponseDto returnBooks(TransactionRequestDto transactionRequestDto, Transaction transaction) throws StudentNotFound, BookNotFound {

       Cards cards;
       try
       {
           cards=cardsRepository.findById(transactionRequestDto.getCardId()).get();
       }
       catch (Exception e)
       {
           transactionRepository.save(transaction);
           throw new StudentNotFound("Invalid Card Details");
       }

       Book book;
       try
       {
           book=bookRepository.findById(transactionRequestDto.getBookId()).get();
       }
       catch (Exception e)
       {
           transactionRepository.save(transaction);
           throw new BookNotFound("Invalid Book Details");
       }

       //matching student book with library book
        boolean isBookFound=false;
        for(Book book1: cards.getBooks())
        {
            if(book1.getId()==book.getId())
            {
                isBookFound=true;
                break;
            }
        }

       if(book.isBookIssued()==false  || isBookFound==false)
       {
           transactionRepository.save(transaction);
           throw new BookNotFound("No Book is issued to the Student");
       }

       transaction.setStatus(TransactionStatus.SUCCESSFULL);
       transaction.setBook(book);
       transaction.setCards(cards);

       book.getTransactions().add(transaction);
       book.setCards(null);
       book.setBookIssued(false);

       cards.getBooks().remove(book);
       cards.getTransactions().add(transaction);
       cardsRepository.save(cards);

       TransactionResponseDto transactionResponseDto=TransactionToDTOs(transaction);
       transactionResponseDto.setMessage("Successfully returned book: "+book.getTitle());
       return transactionResponseDto;
    }

    public TransactionResponseDto TransactionToDTOs(Transaction transaction)
    {
        TransactionResponseDto transactionResponseDto=new TransactionResponseDto();
        transactionResponseDto.setTransactionNo(transaction.getTransactionId());
        transactionResponseDto.setDate(new Date().toString());
        transactionResponseDto.setStatus(transaction.getStatus());
        return transactionResponseDto;
    }
}