package com.example.LibraryManagementSystemApril.entity;

import com.example.LibraryManagementSystemApril.Enum.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "libraryCard")
public class Cards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @CreationTimestamp
    private Date issuedOn;

    private Date validTill;

    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToOne
    @JoinColumn
    private Student student;

}
