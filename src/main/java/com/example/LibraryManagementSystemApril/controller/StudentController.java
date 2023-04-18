package com.example.LibraryManagementSystemApril.controller;

import com.example.LibraryManagementSystemApril.DTOs.BookResponseDTO;
import com.example.LibraryManagementSystemApril.DTOs.StudentRequestDTO;
import com.example.LibraryManagementSystemApril.DTOs.StudentResponseDTO;
import com.example.LibraryManagementSystemApril.DTOs.StudentUpdatedResponseDto;
import com.example.LibraryManagementSystemApril.Enum.Department;
import com.example.LibraryManagementSystemApril.entity.Book;
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

    @GetMapping("/getById")
    public StudentResponseDTO getbyId(@RequestParam("id") int id)
    {
        return studentService.getById(id);
    }

    @GetMapping("/getByName")
    public List<StudentResponseDTO> getByName(@RequestParam("name") String name)
    {
        return studentService.getByName(name);
    }

    @GetMapping("/getByBranch/{branch}")
    public List<StudentResponseDTO> getbyBranch(@PathVariable("branch")Department department)
    {
        return studentService.getByBranch(department);
    }
    @GetMapping("/getall")
    public List<StudentResponseDTO> getStudents()
    {
        return studentService.getStudents();
    }

    @PutMapping("/getBooksbyID")
    public List<BookResponseDTO> getBooksofStudent(@RequestParam int id)
    {
        return studentService.getBooksofStudent(id);
    }

    @PutMapping("/updateName")
    public StudentUpdatedResponseDto updateName(@RequestParam int id, String name)
    {
        return studentService.updateName(id,name);
    }

    @PutMapping("/updateBranch")
    public StudentUpdatedResponseDto updateBranch(@RequestParam("id") int id, Department department)
    {
        return studentService.updateBranch(id,department);
    }

    @PutMapping("/CardStatus")
    public String BlockCard(@RequestParam("id") int id)
    {
        return studentService.BlockCard(id);
    }


    @DeleteMapping("/deleteStudentById")
    public String deleteStudentByName(@RequestParam("id") int id)
    {
        return studentService.deleteStudentByName(id);
    }

}
