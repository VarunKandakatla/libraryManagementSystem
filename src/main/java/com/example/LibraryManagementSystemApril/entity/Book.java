package com.example.LibraryManagementSystemApril.entity;

import com.example.LibraryManagementSystemApril.Enum.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private boolean bookIssued;

    @Enumerated(EnumType.STRING)
    private Genre genre;
    @ManyToOne
    @JoinColumn
    private Author author;
    @ManyToOne
    @JoinColumn
    private Cards cards;
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<Transaction> transactions=new ArrayList<>();

}
