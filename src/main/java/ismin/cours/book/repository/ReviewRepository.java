package ismin.cours.book.repository;

import ismin.cours.book.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByBookIsbn(long isbn);

    @Query("""
           SELECT AVG(reviewRating)
           FROM Review
           WHERE bookIsbn = :bookIsbn
          """)
    float findAverageReviewRatingByBookIsbn(@Param("bookIsbn") long isbn);

    @Query("""
            SELECT AVG(reviewRating)
            FROM Review
            """)
    float findAverageReviewRating();


}
