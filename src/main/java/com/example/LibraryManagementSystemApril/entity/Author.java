package com.example.LibraryManagementSystemApril.entity;

import com.example.LibraryManagementSystemApril.Enum.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(unique = true,nullable = false)
    private String contact;
    private int age;
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private List<Book> book=new ArrayList<>();

}
