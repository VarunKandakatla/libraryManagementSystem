package com.example.LibraryManagementSystemApril.repository;

import com.example.LibraryManagementSystemApril.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardsRepository extends JpaRepository<Cards,Integer> {
}
