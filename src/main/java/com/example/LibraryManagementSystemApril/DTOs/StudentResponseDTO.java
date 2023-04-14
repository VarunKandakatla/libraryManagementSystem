package com.example.LibraryManagementSystemApril.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentResponseDTO {
    private int id;
    private String name;
    private String contact;
}
