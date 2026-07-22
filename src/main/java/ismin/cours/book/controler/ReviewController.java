package ismin.cours.book.controler;

import ismin.cours.book.model.Review;
import ismin.cours.book.repository.ReviewRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewRepository reviewRepository;

    public ReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @GetMapping
    public List<Review> getReviews(){
        return reviewRepository.findAll();
    }

    @GetMapping("/{id}")
    public Review getReview(@PathVariable Long id){
        if(reviewRepository.findById(id).isPresent()) {
            return reviewRepository.findById(id).get();
        }
        return null;
    }

    @PostMapping
    public Review createReview(@RequestBody Review review) {
        return reviewRepository.save(review);
    }

    @PutMapping
    public Review updateReview(@RequestBody Review review) {
        return reviewRepository.save(review);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewRepository.deleteById(id);
    }
}
