package com.example.LibraryManagementSystemApril.controller;

import com.example.LibraryManagementSystemApril.DTOs.StudentRequestDTO;
import com.example.LibraryManagementSystemApril.DTOs.StudentResponseDTO;
import com.example.LibraryManagementSystemApril.entity.Student;
import com.example.LibraryManagementSystemApril.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public StudentResponseDTO addStudent(@RequestBody StudentRequestDTO studentRequestDTO)
    {
        return studentService.addStudent(studentRequestDTO);
    }

    @GetMapping("/getall")
    public List<StudentResponseDTO> getStudents()
    {
        return studentService.getStudents();
    }

    @DeleteMapping("/deleteStudentById")
    public String deleteStudentByName(@RequestParam("id") int id)
    {
        return studentService.deleteStudentByName(id);
    }

}
