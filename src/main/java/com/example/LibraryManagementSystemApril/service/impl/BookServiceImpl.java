package com.example.LibraryManagementSystemApril.service.impl;

import com.example.LibraryManagementSystemApril.DTOs.BookRequestDTO;
import com.example.LibraryManagementSystemApril.DTOs.BookResponseDTO;
import com.example.LibraryManagementSystemApril.Exceptions.AuthorNotFound;
import com.example.LibraryManagementSystemApril.entity.Author;
import com.example.LibraryManagementSystemApril.entity.Book;
import com.example.LibraryManagementSystemApril.repository.AuthorRepository;
import com.example.LibraryManagementSystemApril.repository.BookRepository;
import com.example.LibraryManagementSystemApril.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public BookResponseDTO addBook(BookRequestDTO bookRequestDTO) throws AuthorNotFound {
        Book book=new Book();
        book.setTitle(bookRequestDTO.getTitle());
        book.setGenre(bookRequestDTO.getGenre());
        book.setPrice(bookRequestDTO.getPrice());
        book.setPublications(bookRequestDTO.getPublications());

        try
        {
            Author author=authorRepository.findById(bookRequestDTO.getAuthorId()).get();
            book.setAuthor(author);
            author.getBook().add(book);
            Author updatedAuthor=authorRepository.save(author);

            BookResponseDTO bookResponseDTO=new BookResponseDTO();
            bookResponseDTO.setGenre(book.getGenre());
            bookResponseDTO.setPublications(book.getPublications());
            bookResponseDTO.setTitle(book.getTitle());
            bookResponseDTO.setPrice(book.getPrice());
            bookResponseDTO.setGenre(book.getGenre());
            bookResponseDTO.setAuthor(updatedAuthor.getName());
            return bookResponseDTO;
        }
        catch ( Exception e)
        {
            throw new AuthorNotFound("No Author Found");
        }
    }

    @Override
    public List<BookResponseDTO> getAllBooksById(int id) {
        Author author=authorRepository.findById(id).get();
        List<Book> books=author.getBook();
        return BookstoBooksDTO(books);
    }

    @Override
    public List<BookResponseDTO> getAll() {
        List<Book> books=bookRepository.findAll();
        return BookstoBooksDTO(books);
    }

    public List<BookResponseDTO> BookstoBooksDTO(List<Book>books)
    {
        List<BookResponseDTO> bookResponseDTOList=new ArrayList<>();

        for(Book book : books)
        {
            BookResponseDTO bookResponseDTO=new BookResponseDTO();
            bookResponseDTO.setTitle(book.getTitle());
            bookResponseDTO.setPublications(book.getPublications());
            bookResponseDTO.setPrice(book.getPrice());
            bookResponseDTO.setAuthor(book.getAuthor().getName());
            bookResponseDTO.setGenre(book.getGenre());
            bookResponseDTOList.add(bookResponseDTO);
        }

        return bookResponseDTOList;
    }
}
