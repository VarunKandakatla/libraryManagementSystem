package com.example.LibraryManagementSystemApril.DTOs;

import com.example.LibraryManagementSystemApril.Enum.Genre;
import com.example.LibraryManagementSystemApril.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDTO {

    private String title;
    private String publications;
    private int price;
    private Genre genre;
    private String author;

}
