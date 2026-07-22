package ismin.cours.book.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "BOOK")
public class Book {
    @Id
    int id;
    @Column(columnDefinition = "INTEGER")
    long isbn;
    String writer;
    String title;
    @Column(name = "PUBLISHING_DATE")
    LocalDate publishingDate;
}
