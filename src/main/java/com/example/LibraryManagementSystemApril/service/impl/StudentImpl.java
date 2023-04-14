package com.example.LibraryManagementSystemApril.service.impl;

import com.example.LibraryManagementSystemApril.DTOs.StudentRequestDTO;
import com.example.LibraryManagementSystemApril.DTOs.StudentResponseDTO;
import com.example.LibraryManagementSystemApril.Enum.Status;
import com.example.LibraryManagementSystemApril.entity.Cards;
import com.example.LibraryManagementSystemApril.entity.Student;
import com.example.LibraryManagementSystemApril.repository.StudentRepository;
import com.example.LibraryManagementSystemApril.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class StudentImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public StudentResponseDTO addStudent(StudentRequestDTO studentRequestDTO) {

       Student student=new Student();
       student.setName(studentRequestDTO.getName());
       student.setContact(studentRequestDTO.getContact());
       student.setDepartment(studentRequestDTO.getDepartment());

       Cards cards=new Cards();
       cards.setStatus(Status.ACTIVATED);
       cards.setValidTill(new Date(Calendar.getInstance().get(Calendar.YEAR)-1900+3,Calendar.MONTH,Calendar.DAY_OF_MONTH));
       cards.setStudent(student);

       student.setCards(cards);

       Student updatedStudent=studentRepository.save(student);
       StudentResponseDTO studentResponseDTO=new StudentResponseDTO();
       studentResponseDTO.setContact(updatedStudent.getContact());
       studentResponseDTO.setName(updatedStudent.getName());
       studentResponseDTO.setId(updatedStudent.getId());

       return studentResponseDTO;
    }

    @Override
    public Student getStudentbyName(String name) {
        return null;
    }

    @Override
    public List<StudentResponseDTO> getStudents() {
        List<Student> studentList=studentRepository.findAll();
        List<StudentResponseDTO> studentResponseDTOSList=new ArrayList<>();

        for(Student student: studentList)
        {
            StudentResponseDTO studentResponseDTO=new StudentResponseDTO();
            studentResponseDTO.setId(student.getId());
            studentResponseDTO.setName(student.getName());
            studentResponseDTO.setContact(student.getContact());

            studentResponseDTOSList.add(studentResponseDTO);
        }

        return studentResponseDTOSList;
    }

    @Override
    public String deleteStudentByName(int id) {
        Student student=studentRepository.findById(id).get();
        studentRepository.deleteById(id);

        return student.getName()+" is Deleted";
    }
}
