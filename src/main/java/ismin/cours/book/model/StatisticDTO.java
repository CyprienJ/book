package ismin.cours.book.model;

import lombok.Data;

import java.util.Map;

@Data
public class StatisticDTO {

    private float mark;
    private int numberOfReviews;
    Map<Integer, Integer> markRepartition;
    float score;
}
