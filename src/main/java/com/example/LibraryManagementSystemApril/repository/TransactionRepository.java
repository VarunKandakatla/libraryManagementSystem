package com.example.LibraryManagementSystemApril.repository;

import com.example.LibraryManagementSystemApril.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,String> {
}
