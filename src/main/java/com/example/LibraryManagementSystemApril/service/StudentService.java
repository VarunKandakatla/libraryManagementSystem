package com.example.LibraryManagementSystemApril.service;

import com.example.LibraryManagementSystemApril.DTOs.BookResponseDTO;
import com.example.LibraryManagementSystemApril.DTOs.StudentRequestDTO;
import com.example.LibraryManagementSystemApril.DTOs.StudentResponseDTO;
import com.example.LibraryManagementSystemApril.DTOs.StudentUpdatedResponseDto;
import com.example.LibraryManagementSystemApril.Enum.Department;
import com.example.LibraryManagementSystemApril.entity.Book;
import com.example.LibraryManagementSystemApril.entity.Student;

import java.util.List;

public interface StudentService {

    public StudentResponseDTO addStudent(StudentRequestDTO studentRequestDTO);
    public Student getStudentbyName(String name);

   public List<StudentResponseDTO> getStudents();

    String deleteStudentByName(int id);

    StudentResponseDTO getById(int id);

    List<StudentResponseDTO> getByName(String name);

    List<StudentResponseDTO> getByBranch(Department department);

    StudentUpdatedResponseDto updateName(int id, String name);

    StudentUpdatedResponseDto updateBranch(int id, Department department);

    String DeleteById(int id);

    String BlockCard(int id);

    List<BookResponseDTO> getBooksofStudent(int id);
}
