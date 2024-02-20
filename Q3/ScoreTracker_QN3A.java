package Q3;

import java.util.ArrayList;
import java.util.List;

public class ScoreTracker_QN3A {

    List<Double> scores = new ArrayList<>(); // List to store the scores

    // Method to add a new score to the list
    void addScore(Double score) {
        scores.add(score);
    }

    // Method to calculate and return the median score
    double getMedianScore() {
        scores.sort(null); // Sorting the list of scores in ascending order

        switch (scores.size() % 2) {
            case 0:
                // If the size of the list is even, return the average of the two middle scores
                return (scores.get(scores.size() / 2) + scores.get(scores.size() / 2 - 1)) / 2;
            default:
                // If the size of the list is odd, return the middle score
                return scores.get(scores.size() / 2);
        }
    }

    // Main method for testing the ScoreTrackerQN3A class
    public static void main(String[] args) {
        ScoreTracker_QN3A sc = new ScoreTracker_QN3A();

        // Adding scores to the ScoreTracker
        sc.addScore(85.5);
        sc.addScore(92.3);
        sc.addScore(77.8);
        sc.addScore(90.1);

        // Calculating and printing the median after the initial scores
        double median1 = sc.getMedianScore();
        System.out.println("Median after initial scores: " + median1);

        // Adding more scores
        sc.addScore(81.2);
        sc.addScore(88.7);

        // Calculating and printing the median after additional scores
        double median2 = sc.getMedianScore();
        System.out.println("Median after additional scores: " + median2);
    }
}
