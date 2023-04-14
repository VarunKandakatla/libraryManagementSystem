package com.example.LibraryManagementSystemApril.repository;

import com.example.LibraryManagementSystemApril.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
}
