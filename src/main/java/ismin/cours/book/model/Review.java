package ismin.cours.book.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "REVIEW")
public class Review {
    @Id
    int id;
    @Column(name = "BOOK_ISBN", columnDefinition = "INTEGER")
    long bookIsbn;
    @Column(name = "REVIEW_DATE")
    LocalDate reviewDate;
    @Column(name = "REVIEW_TEXT")
    String reviewText;
    @Column(name = "REVIEW_RATING")
    int reviewRating;
}
