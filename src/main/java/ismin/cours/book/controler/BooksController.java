package ismin.cours.book.controler;

import ismin.cours.book.model.Book;
import ismin.cours.book.model.ScoreDTO;
import ismin.cours.book.model.StatisticDTO;
import ismin.cours.book.repository.BookRepository;
import ismin.cours.book.service.TrendingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    private final TrendingService trendingService;
    private final BookRepository bookRepository;

    public BooksController(TrendingService trendingService, BookRepository bookRepository) {
        this.trendingService = trendingService;
        this.bookRepository = bookRepository;
    }

    @GetMapping("{isbn}/statistics")
    private StatisticDTO getStatistics(@PathVariable("isbn") long isbn) {
        Book book = bookRepository.findAllByIsbn(isbn);
        if (book == null) {
            return null;
        }
        return trendingService.computeStatistic(book);
    }

    @GetMapping("ranking")
    private List<ScoreDTO> getRanking(){
        return trendingService.computeAllRanking();
    }
}
