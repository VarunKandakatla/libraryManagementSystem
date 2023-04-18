package com.example.LibraryManagementSystemApril.service.impl;

import com.example.LibraryManagementSystemApril.DTOs.BookResponseDTO;
import com.example.LibraryManagementSystemApril.entity.Book;
import com.example.LibraryManagementSystemApril.entity.Cards;
import com.example.LibraryManagementSystemApril.repository.CardsRepository;
import com.example.LibraryManagementSystemApril.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardImpl implements CardService {

    @Autowired
    CardsRepository cardsRepository;

    @Override
    public List<BookResponseDTO> getBooks(int id) {
        Cards cards=cardsRepository.findById(id).get();
        List<Book> bookList=cards.getBooks();
        List<BookResponseDTO> bookResponseDTOList=new ArrayList<>();
        for(Book book: bookList)
        {
            BookResponseDTO bookResponseDTO=new BookResponseDTO();
            bookResponseDTO.setAuthor(book.getAuthor().getName());
            bookResponseDTO.setTitle(book.getTitle());
            bookResponseDTO.setGenre(book.getGenre());
            bookResponseDTO.setPublications(book.getPublications());
            bookResponseDTO.setPrice(book.getPrice());
            bookResponseDTOList.add(bookResponseDTO);
        }
        return bookResponseDTOList;
    }
}
