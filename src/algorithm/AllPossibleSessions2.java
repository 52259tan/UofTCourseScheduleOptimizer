package algorithm;

import entity.Course;
import entity.Session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static algorithm.DayOverlapChecker.hasOverlappingDay;
import static algorithm.TimeRangeOverlapChecker.checkNoOverlap;

/**
 * Generate all possible combinations of sessions (one Lec, one Tut, one Pra) of a course without time conflict
 * and with every session has an endTime before 8
 * @author ping
 */
public class AllPossibleSessions2 {
    // Get a list of all possible sessions (Lec, Tut, and Pra) for a certain course
    public static List<List<Session>> generateCombinations(Course course) {
        List<List<Session>> allCombinations = new ArrayList<>();
        List<Session> list1 = course.getLecSessions();
        List<Session> list2 = course.getTutSessions();
        List<Session> list3 = course.getPraSessions();
        for (Session session : list1) {
            if (!checkBeforeEight(session)) {
                list1.remove(session);
            }
        }
        for (Session session : list2) {
            if (!checkBeforeEight(session)) {
                list2.remove(session);
            }
        }
        for (Session session : list3) {
            if (!checkBeforeEight(session)) {
                list3.remove(session);
            }
        }
        for (Session element1 : list1) {
            if (!list2.isEmpty()) {
                if (!list3.isEmpty()) {
                    for (Session element2 : list2) {
                        for (Session element3 : list3) {
                            List<Session> combination = new ArrayList<>();
                            combination.add(element1);
                            combination.add(element2);
                            combination.add(element3);
                            allCombinations.add(combination);
                        }
                    }
                } else {
                    for (Session element2 : list2) {
                        List<Session> combination = new ArrayList<>();
                        combination.add(element1);
                        combination.add(element2);
                        allCombinations.add(combination);
                    }
                }
            } else if (!list3.isEmpty()) {
                for (Session element3 : list3) {
                    List<Session> combination = new ArrayList<>();
                    combination.add(element1);
                    combination.add(element3);
                    allCombinations.add(combination);

                }
            } else {
                List<Session> combination = new ArrayList<>();
                combination.add(element1);
                allCombinations.add(combination);
            }
        }

        Iterator<List<Session>> iterator = allCombinations.iterator();
        while (iterator.hasNext()) {
            List<Session> combination = iterator.next();

            if (combination.size() == 1) {
                return allCombinations;
            } else if (combination.size() == 2 || combination.size() == 3) {
                for (int i = 0; i < combination.size(); i++) {
                    for (int j = i + 1; j < combination.size(); j++) {
                        if (!hasOverlappingDay(combination.get(i).getDay(), combination.get(j).getDay()).isEmpty()) {
                            List<Integer> days = hasOverlappingDay(combination.get(i).getDay(), combination.get(j).getDay());
                            List<Integer> start = new ArrayList<>();
                            List<Integer> end = new ArrayList<>();
                            List<Integer> index1 = new ArrayList<>();
                            List<Integer> index2 = new ArrayList<>();
                            for (Integer day : days) {
                                index1.add(combination.get(i).getDay().indexOf(day));
                                index2.add(combination.get(i).getDay().indexOf(day));
                            }

                            for (Integer in : index1) {
                                start.add(combination.get(i).getStartTime().get(in));
                                end.add(combination.get(i).getEndTime().get(in));
                            }
                            for (Integer in : index2) {
                                start.add(combination.get(j).getStartTime().get(in));
                                end.add(combination.get(j).getEndTime().get(in));
                            }

                            if (checkNoOverlap(start, end)) {
                                iterator.remove();
                            }
                        }
                    }
                }
            }
        }

        return allCombinations;  // [[tutSession, lecSession],[]]
    }

    private static boolean checkBeforeEight(Session session) {
        for (Integer endTime: session.getEndTime()){
            if (endTime > 20){
                return false;
            }
        }
        return true;
    }
}

