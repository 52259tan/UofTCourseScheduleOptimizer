package AlgorithmDemo;

import algorithm.DayOverlapChecker;

import java.util.ArrayList;
import java.util.List;

public class DayOverlapCheckerDemo {
    public static void main(String[] args) {
        // Sample lists of days
        List<Integer> days1 = List.of(1, 2, 3, 4, 5);
        List<Integer> days2 = List.of(4, 5, 6, 7, 8);

        // Check for overlapping days
        List<Integer> overlappingIndices = DayOverlapChecker.hasOverlappingDay(days1, days2);

        // Display the original lists and the result
        System.out.println("List 1: " + days1);
        System.out.println("List 2: " + days2);

        if (!overlappingIndices.isEmpty()) {
            System.out.println("Overlapping indices: " + overlappingIndices);
        } else {
            System.out.println("No overlapping days found.");
        }
    }
}
