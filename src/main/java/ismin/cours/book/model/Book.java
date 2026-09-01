package ismin.cours.book.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Book {
    int id;
    int isbn;
    String writer;
    String title;
    LocalDate publishingDate;
}

