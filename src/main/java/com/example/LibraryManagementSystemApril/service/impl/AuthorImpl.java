package com.example.LibraryManagementSystemApril.service.impl;

import com.example.LibraryManagementSystemApril.DTOs.AuthorRequestDTO;
import com.example.LibraryManagementSystemApril.DTOs.AuthorResponseDTO;
import com.example.LibraryManagementSystemApril.DTOs.BookResponseDTO;
import com.example.LibraryManagementSystemApril.Exceptions.AuthorNotFound;
import com.example.LibraryManagementSystemApril.entity.Author;
import com.example.LibraryManagementSystemApril.entity.Book;
import com.example.LibraryManagementSystemApril.repository.AuthorRepository;
import com.example.LibraryManagementSystemApril.service.AuthorService;
import com.example.LibraryManagementSystemApril.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookServiceImpl bookServiceImpl;

    @Override
    public AuthorResponseDTO addAuthor(AuthorRequestDTO authorRequestDTO) {

        Author author=new Author();
        author.setName(authorRequestDTO.getName());
        author.setAge(authorRequestDTO.getAge());
        author.setContact(authorRequestDTO.getContact());

        Author updatedAuthor=authorRepository.save(author);
        //ResponseDTO

        AuthorResponseDTO authorResponseDTO=new AuthorResponseDTO();
        authorResponseDTO.setName(updatedAuthor.getName());
        authorResponseDTO.setContact(updatedAuthor.getContact());

        return authorResponseDTO;

    }

    @Override
    public AuthorResponseDTO getAuthorById(int id) throws AuthorNotFound {

        try {
            Author author = authorRepository.findById(id).get();
            AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO();
            authorResponseDTO.setName(author.getName());
            authorResponseDTO.setContact(author.getContact());

            return authorResponseDTO;
        }
        catch (Exception e)
        {
            throw new AuthorNotFound("id is Not Valid");
        }
    }

    @Override
    public List<AuthorResponseDTO> getAuthorByName(String string) {
       List< Author> authors= authorRepository.findByName(string);

       return AuthortoAuthorDTOS(authors);
    }

    @Override
    public List<AuthorResponseDTO> getall() {
        List<Author> authors=authorRepository.findAll();
        return AuthortoAuthorDTOS(authors);
    }

    @Override
    public List<String> getCountofBooks() {
        List<Author> authors=authorRepository.findAll();
        List<String> list=new ArrayList<>();

        for(Author author: authors)
        {
            list.add(author.getName()+", total books written -> "+author.getBook().size());
        }
        return list;
    }

    @Override
    public List<BookResponseDTO> getTotalBooksbyAuthor(int id) {
        Author author=authorRepository.findById(id).get();
        List<Book> books=author.getBook();

        return bookServiceImpl.BookstoBooksDTO(books);

    }

    @Override
    public AuthorResponseDTO updateNamebyId(int id,String name) {
        Author author=authorRepository.findById(id).get();
        author.setName(name);
        Author updatedAuthor=authorRepository.save(author);

        AuthorResponseDTO authorResponseDTO=new AuthorResponseDTO();
        authorResponseDTO.setName(updatedAuthor.getName());
        authorResponseDTO.setContact(updatedAuthor.getContact());
        return authorResponseDTO;
    }

    @Override
    public String DeleteAuthor(int id) {
        Author author=authorRepository.findById(id).get();
        authorRepository.delete(author);
        return author.getName()+" Deleted";
    }

    @Override
    public String DeleteAll() {
        authorRepository.deleteAll();
        return "Deleted All Authors";
    }

    public List<AuthorResponseDTO> AuthortoAuthorDTOS(List<Author> authors)
    {
        List<AuthorResponseDTO> authorResponseDTOList=new ArrayList<>();

        for(Author author: authors)
        {
            AuthorResponseDTO authorResponseDTO=new AuthorResponseDTO();
            authorResponseDTO.setContact(author.getContact());
            authorResponseDTO.setName(author.getName());
            authorResponseDTOList.add(authorResponseDTO);
        }

        return authorResponseDTOList;
    }
}
