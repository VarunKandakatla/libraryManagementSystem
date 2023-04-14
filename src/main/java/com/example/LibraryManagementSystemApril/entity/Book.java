package com.example.LibraryManagementSystemApril.entity;

import com.example.LibraryManagementSystemApril.Enum.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String publications;
    private int price;

    @CreationTimestamp
    private Date launchedDate;

    @Enumerated(EnumType.STRING)
    private Genre genre;
    @ManyToOne
    @JoinColumn
    private Author author;

}
