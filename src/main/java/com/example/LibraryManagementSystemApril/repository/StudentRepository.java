package com.example.LibraryManagementSystemApril.repository;

import com.example.LibraryManagementSystemApril.Enum.Department;
import com.example.LibraryManagementSystemApril.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findByName(String name);
    List<Student> findByDepartment(Department department);
}
