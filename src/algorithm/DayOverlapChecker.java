package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Given 2 lists of days, return a list of overlapping days.
 * @author pinglu
 */

public class DayOverlapChecker {
    /**
     * Given 2 lists of days, return a list of overlapping days.
     *
     * @param list1 First list of days
     * @param list2 Second list of days
     * @return A list of indices of overlapping days
     */
    public static List<Integer> hasOverlappingDay(List<Integer> list1, List<Integer> list2) {
        List<Integer> overlappingDays = new ArrayList<>();

        // Validate parameters
        if (list1 == null || list2 == null) {
            throw new IllegalArgumentException("Input lists cannot be null");
        }

        for (int day : list1) {
            if (list2.contains(day)) {
                overlappingDays.add(day);
            }
        }
        return overlappingDays;
    }
}

