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
public class StudentUpdatedResponseDto {
    private int id;
    private String name;
    private Department department;
}
