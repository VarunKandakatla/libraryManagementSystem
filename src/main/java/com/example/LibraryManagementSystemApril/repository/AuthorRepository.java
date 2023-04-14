package com.example.LibraryManagementSystemApril.repository;

import com.example.LibraryManagementSystemApril.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {

     List<Author> findByName(String string) ;

}
