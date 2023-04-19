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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TransactionImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardsRepository cardsRepository;

    @Autowired
    JavaMailSender emailSender;

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

        //Sending Mail
        IssueBooksendMail(transaction,cards,book);

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
       //Adding transaction double here
//       cards.getTransactions().add(transaction);
       cardsRepository.save(cards);

       TransactionResponseDto transactionResponseDto=TransactionToDTOs(transaction);
       transactionResponseDto.setMessage("Successfully returned book: "+book.getTitle());

       //sending mail
        returnBookSendMail(transaction,cards,book);
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
    //Mail Function
    public void IssueBooksendMail(Transaction transaction, Cards cards,Book book)
    {
        String text="Hi "+cards.getStudent().getName()+"!\nCongratulations! You have been issued a book: "+book.getTitle()+
                ", which has been written by, "+book.getAuthor().getName()+". Hope you will enjoy it! \nHappy Learning! \n \n" +
                "Here is your transaction Details: \ntransaction no: "+transaction.getTransactionId()+"\ntransaction status: " +
                transaction.getStatus()+"\nDate: "+transaction.getDate().toString();


        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("librarymanagementsystemapril.com");
        message.setTo(cards.getStudent().getContact());
        message.setSubject("Congrats! Book Issued! ");
        message.setText(text);
        emailSender.send(message);
    }

    public void returnBookSendMail(Transaction transaction, Cards cards, Book book)
    {
        String text="Hi "+cards.getStudent().getName()+"!\nYou are returning a book: "+book.getTitle()+
                ", which has been written by, "+book.getAuthor().getName()+". Hope you have enjoyed it! \nTake new book & Gain more Knowledge! \nThank You! \n \n" +
                "Here is your transaction Details: \ntransaction no: "+transaction.getTransactionId()+"\ntransaction status: " +
                transaction.getStatus()+"\nDate: "+transaction.getDate().toString();


        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("librarymanagementsystemapril.com");
        message.setTo(cards.getStudent().getContact());
        message.setSubject("Congrats! Book returned Successfull! ");
        message.setText(text);
        emailSender.send(message);
    }
}