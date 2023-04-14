package com.example.LibraryManagementSystemApril.DTOs;

import com.example.LibraryManagementSystemApril.Enum.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BookRequestDTO {
    private String title;
    private String publications;
    private int price;
    private Genre genre;
    private int authorId;
}
