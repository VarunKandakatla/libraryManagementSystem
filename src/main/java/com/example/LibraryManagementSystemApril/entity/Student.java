package com.example.LibraryManagementSystemApril.entity;

import com.example.LibraryManagementSystemApril.Enum.Department;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String contact;
    
    @Enumerated(EnumType.STRING)
    private Department department;
    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    private Cards cards;

}
