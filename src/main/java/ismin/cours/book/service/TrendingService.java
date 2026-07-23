package ismin.cours.book.service;

import ismin.cours.book.model.Book;
import ismin.cours.book.model.Review;
import ismin.cours.book.model.ScoreDTO;
import ismin.cours.book.model.StatisticDTO;
import ismin.cours.book.repository.BookRepository;
import ismin.cours.book.repository.ReviewRepository;
import org.hibernate.type.descriptor.java.BooleanJavaType;
import org.springframework.data.domain.Score;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TrendingService {

    private final BookRepository bookRepository;
    int THRESHOLD = 5;

    private final ReviewRepository reviewRepository;

    public TrendingService(ReviewRepository reviewRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
    }

    private float computeScore(Book book){

        List<Review> reviews = reviewRepository.findAllByBookIsbn(book.getIsbn());

        float averageBookReviewRating = reviewRepository.findAverageReviewRatingByBookIsbn(book.getIsbn());

        float averageReviewRatingGlobal = reviewRepository.findAverageReviewRating();

        return (float) reviews.size() / (reviews.size() + THRESHOLD) * averageBookReviewRating + ((float) THRESHOLD / (reviews.size() + THRESHOLD) * averageReviewRatingGlobal);
    }

    public StatisticDTO computeStatistic(Book book) {
        List<Review> reviews = reviewRepository.findAllByBookIsbn(book.getIsbn());

        StatisticDTO statisticDTO = new StatisticDTO();

        statisticDTO.setMark(reviewRepository.findAverageReviewRatingByBookIsbn(book.getIsbn()));
        statisticDTO.setNumberOfReviews(reviews.size());

        Map<Integer, Integer> markRepartition = new HashMap<>();
        for(int i = 0; i < 5; i++){
            int rating = i;
            int count = (int) reviews.stream()
                    .filter(review -> review.getReviewRating() == rating)
                    .count();

            markRepartition.put(rating, count);
        }
        statisticDTO.setMarkRepartition(markRepartition);

        statisticDTO.setScore(computeScore(book));

        return statisticDTO;
    }

    public List<ScoreDTO> computeAllRanking() {

        List<Book> allBooks = bookRepository.findAll();
        List<ScoreDTO> scores =  new ArrayList<>();

        for (Book book : allBooks) {
            ScoreDTO score = new ScoreDTO();
            score.setBook(book);
            score.setScore(computeScore(book));
            scores.add(score);
        }

        scores.sort(Comparator.comparingDouble(ScoreDTO::getScore).reversed());

        return scores;
    }



}
