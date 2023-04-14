package com.example.LibraryManagementSystemApril.repository;

import com.example.LibraryManagementSystemApril.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
}
