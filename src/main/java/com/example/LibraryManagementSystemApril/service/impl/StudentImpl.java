package com.example.LibraryManagementSystemApril.service.impl;

import com.example.LibraryManagementSystemApril.DTOs.BookResponseDTO;
import com.example.LibraryManagementSystemApril.DTOs.StudentRequestDTO;
import com.example.LibraryManagementSystemApril.DTOs.StudentResponseDTO;
import com.example.LibraryManagementSystemApril.DTOs.StudentUpdatedResponseDto;
import com.example.LibraryManagementSystemApril.Enum.Department;
import com.example.LibraryManagementSystemApril.Enum.Status;
import com.example.LibraryManagementSystemApril.entity.Book;
import com.example.LibraryManagementSystemApril.entity.Cards;
import com.example.LibraryManagementSystemApril.entity.Student;
import com.example.LibraryManagementSystemApril.repository.CardsRepository;
import com.example.LibraryManagementSystemApril.repository.StudentRepository;
import com.example.LibraryManagementSystemApril.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class StudentImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CardsRepository cardsRepository;

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
        return studentToStudentDTOs(studentList);
    }

    @Override
    public String deleteStudentByName(int id) {
        Student student=studentRepository.findById(id).get();
        studentRepository.deleteById(id);

        return student.getName()+" is Deleted";
    }

    @Override
    public StudentResponseDTO getById(int id) {
        Student student = studentRepository.findById(id).get();
        StudentResponseDTO studentResponseDTO=new StudentResponseDTO();
        studentResponseDTO.setName(student.getName());
        studentResponseDTO.setId(student.getId());
        studentResponseDTO.setContact(student.getContact());

        return studentResponseDTO;
    }

    @Override
    public List<StudentResponseDTO> getByName(String name) {
        List<Student> studentList=studentRepository.findByName(name);
        return studentToStudentDTOs(studentList);
    }

    @Override
    public List<StudentResponseDTO> getByBranch(Department department) {
        List<Student> studentList=studentRepository.findByDepartment(department);
        return studentToStudentDTOs(studentList);
    }

    @Override
    public StudentUpdatedResponseDto updateName(int id, String name) {
        Student student = studentRepository.findById(id).get();
        student.setName(name);
         Student updatedStudent=studentRepository.save(student);

        StudentUpdatedResponseDto studentUpdateNameDto=new StudentUpdatedResponseDto();
        studentUpdateNameDto.setId(updatedStudent.getId());
        studentUpdateNameDto.setName(updatedStudent.getName());
        studentUpdateNameDto.setDepartment(updatedStudent.getDepartment());

        return  studentUpdateNameDto;
    }

    @Override
    public StudentUpdatedResponseDto updateBranch(int id, Department department) {
        Student student= studentRepository.findById(id).get();
        student.setDepartment(department);
       Student updatedStudent= studentRepository.save(student);

        StudentUpdatedResponseDto studentUpdateNameDto=new StudentUpdatedResponseDto();
        studentUpdateNameDto.setId(updatedStudent.getId());
        studentUpdateNameDto.setName(updatedStudent.getName());
        studentUpdateNameDto.setDepartment(updatedStudent.getDepartment());

        return studentUpdateNameDto;
    }

    @Override
    public String DeleteById(int id) {
        studentRepository.deleteById(id);
        return "Successfully Deleted";
    }

    @Override
    public String BlockCard(int id) {
        Student student=studentRepository.findById(id).get();
        student.getCards().setStatus(Status.BLOCKED);
        studentRepository.save(student);

        return "BLOCKED ! ! !";
    }

    @Override
    public List<BookResponseDTO> getBooksofStudent(int id) {
        Cards cards=cardsRepository.findById(id).get();
        List<Book> bookList=cards.getBooks();
        List<BookResponseDTO> bookResponseDTOList=new ArrayList<>();
        for(Book book: bookList)
        {
            BookResponseDTO bookResponseDTO=new BookResponseDTO();
            bookResponseDTO.setAuthor(book.getAuthor().getName());
            bookResponseDTO.setTitle(book.getTitle());
            bookResponseDTO.setGenre(book.getGenre());
            bookResponseDTO.setPublications(book.getPublications());
            bookResponseDTO.setPrice(book.getPrice());
            bookResponseDTOList.add(bookResponseDTO);
        }
        return bookResponseDTOList;
    }


    public List<StudentResponseDTO> studentToStudentDTOs(List<Student> studentList)
    {
        List<StudentResponseDTO> studentResponseDTOList=new ArrayList<>();

        for(Student student: studentList)
        {
            StudentResponseDTO studentResponseDTO=new StudentResponseDTO();
            studentResponseDTO.setId(student.getId());
            studentResponseDTO.setName(student.getName());
            studentResponseDTO.setContact(student.getContact());

            studentResponseDTOList.add(studentResponseDTO);
        }
        return studentResponseDTOList;
    }
}
