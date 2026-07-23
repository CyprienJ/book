package ismin.cours.book.repository;

import ismin.cours.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findAllByIsbn(long isbn);
}
