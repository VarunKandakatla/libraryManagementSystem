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
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;


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

    @Override
    public List< BookResponseDTO> getByname(String name) {
        List<Book> books=bookRepository.findByTitle(name);

        return BookstoBooksDTO(books);
    }

    @Override
    public BookResponseDTO getBookbyId(int id) {
        Book book=bookRepository.findById(id).get();

        BookResponseDTO bookResponseDTO=new BookResponseDTO();
        bookResponseDTO.setTitle(book.getTitle());
        bookResponseDTO.setGenre(book.getGenre());
        bookResponseDTO.setPublications(book.getPublications());
        bookResponseDTO.setPrice(book.getPrice());
        bookResponseDTO.setAuthor(book.getAuthor().getName());
         return bookResponseDTO;
    }

    @Override
    public BookResponseDTO updateName(int id, String newName) {
        Book book=bookRepository.findById(id).get();
        book.setTitle(newName);
        bookRepository.save(book);

        BookResponseDTO bookResponseDTO=new BookResponseDTO();
        bookResponseDTO.setTitle(book.getTitle());
        bookResponseDTO.setGenre(book.getGenre());
        bookResponseDTO.setPublications(book.getPublications());
        bookResponseDTO.setPrice(book.getPrice());
        bookResponseDTO.setAuthor(book.getAuthor().getName());
        return bookResponseDTO;
    }

    @Override
    public BookResponseDTO updatePrice(int id, int price) {
        Book book=bookRepository.findById(id).get();
        book.setPrice(price);
        bookRepository.save(book);

        BookResponseDTO bookResponseDTO=new BookResponseDTO();
        bookResponseDTO.setTitle(book.getTitle());
        bookResponseDTO.setGenre(book.getGenre());
        bookResponseDTO.setPublications(book.getPublications());
        bookResponseDTO.setPrice(book.getPrice());
        bookResponseDTO.setAuthor(book.getAuthor().getName());
        return bookResponseDTO;
    }

    @Override
    public String DeleteBook(int id) {
        Book book=bookRepository.findById(id).get();
        bookRepository.delete(book);
        return book.getTitle()+" Deleted";
    }

    @Override
    public String deleteAll() {
        bookRepository.deleteAll();
        return "Deleted All Books";
    }

    @Override
    public List<BookResponseDTO> getBooksofStudent(int id) {
        List<Book> bookList=bookRepository.findByCards(id);
        return BookstoBooksDTO(bookList);
    }

    @Override
    public String resetAllBooks() {
        List<Book> bookList=bookRepository.findAll();

        for(Book book: bookList)
        {
            book.setBookIssued(false);
//            book.getCards().getBooks().clear();
            book.setCards(null);
            bookRepository.save(book);
        }

        return "Successfully Resetted";
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
