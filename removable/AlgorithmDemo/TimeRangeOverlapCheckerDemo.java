package AlgorithmDemo;

import algorithm.TimeRangeOverlapChecker;

import java.util.List;

public class TimeRangeOverlapCheckerDemo {
    public static void main(String[] args) {
        // Sample lists of start and end times
        List<Integer> startTimes1 = List.of(1, 5);
        List<Integer> endTimes1 = List.of(4, 6);

        List<Integer> startTimes2 = List.of(5, 6, 7);
        List<Integer> endTimes2 = List.of(8, 9, 10);

        // Check if there is no overlap
        boolean noOverlap = TimeRangeOverlapChecker.checkNoOverlap(startTimes1, endTimes1);
        System.out.println("Overlap between sessions 1 and 2: " + noOverlap);

        // Check for another set of time ranges with no overlap
        boolean noOverlap2 = TimeRangeOverlapChecker.checkNoOverlap(startTimes2, endTimes2);
        System.out.println("Overlap between sessions 2 and 3: " + noOverlap2);

        // Check for overlapping time ranges
        List<Integer> startTimes3 = List.of(4, 8, 11);
        List<Integer> endTimes3 = List.of(6, 11, 12);
        boolean overlap3 = TimeRangeOverlapChecker.checkNoOverlap(startTimes3, endTimes3);
        System.out.println("Overlap between sessions 3 and 1: " + overlap3);
    }
}
