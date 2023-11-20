package algorithm;

import entity.Course;
import entity.Session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static algorithm.DayOverlapChecker.hasOverlappingDay;
import static algorithm.TimeRangeOverlapChecker.checkNoOverlap;

/**
 * Generate all possible combinations of sessions (one Lec, one Tut, one Pra) of a course without time conflict.
 *
 * @author ping
 */
public class AllPossibleSessions {
    // Get a list of all possible sessions (Lec, Tut, and Pra) for a certain course
    private static List<List<Session>> generateCombinations(Course course) {
        List<List<Session>> allCombinations = new ArrayList<>();
        List<Session> list1 = course.getLecSessions();
        List<Session> list2 = course.getTutSessions();
        List<Session> list3 = course.getPraSessions();

        for (Session element1 : list1) {
            for (Session element2 : list2) {
                for (Session element3 : list3) {
                    List<Session> combination = new ArrayList<>();
                    combination.add(element1);
                    combination.add(element2);
                    combination.add(element3);
                    allCombinations.add(combination);
                }
            }
        }

        Iterator<List<Session>> iterator = allCombinations.iterator();
        while (iterator.hasNext()) {
            List<Session> combination = iterator.next();

            if (combination.size() == 1) {
                allCombinations.add(combination);
                return allCombinations;
            } else if (combination.size() == 2 || combination.size() == 3) {
                for (int i = 0; i < combination.size(); i++) {
                    for (int j = i + 1; j < combination.size(); j++) {
                        if (!hasOverlappingDay(combination.get(i).getDay(), combination.get(j).getDay()).isEmpty()) {
                            List<Integer> days = hasOverlappingDay(combination.get(i).getDay(), combination.get(j).getDay());
                            List<Integer> start = new ArrayList<>();
                            List<Integer> end = new ArrayList<>();

                            for (Integer day : days) {
                                start.add(combination.get(i).getStartTime().get(day));
                                end.add(combination.get(i).getEndTime().get(day));
                                start.add(combination.get(j).getStartTime().get(day));
                                end.add(combination.get(j).getEndTime().get(day));
                            }

                            if (checkNoOverlap(start, end)) {
                                iterator.remove();
                            }
                        }
                    }
                }
            }
        }

        return allCombinations;
    }
}
