package com.example.LibraryManagementSystemApril.service;

import com.example.LibraryManagementSystemApril.DTOs.StudentRequestDTO;
import com.example.LibraryManagementSystemApril.DTOs.StudentResponseDTO;
import com.example.LibraryManagementSystemApril.entity.Student;

import java.util.List;

public interface StudentService {

    public StudentResponseDTO addStudent(StudentRequestDTO studentRequestDTO);
    public Student getStudentbyName(String name);

   public List<StudentResponseDTO> getStudents();

    String deleteStudentByName(int id);
}
