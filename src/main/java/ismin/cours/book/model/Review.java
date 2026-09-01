package ismin.cours.book.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Review {
    int id;
    int bookIsbn;
    LocalDate reviewDate;
    String reviewText;
    int reviewRating;
}
