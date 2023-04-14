package com.example.LibraryManagementSystemApril.DTOs;

import com.example.LibraryManagementSystemApril.Enum.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentRequestDTO {
    private String name;
    private String contact;
    private Department department;
}