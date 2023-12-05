package algorithm;

import entity.Course;
import entity.Session;

import java.util.List;
import java.util.ArrayList;

import static algorithm.AllPossibleSessions2.generateCombinations;
import static algorithm.DayOverlapChecker.hasOverlappingDay;
import static algorithm.TimeRangeOverlapChecker.checkNoOverlap;

/**
 * Generate all possible combinations of sessions (one Lec, one Tut, one Pra) of 5 courses without time conflict
 * and with every session has a endTime before 8
 * @author pinglu
 */
public class AllPossibleTimeTables2 {

    /**
     * Generate all possible combinations of sessions (one Lec, one Tut, one Pra) of 5 courses without time conflict
     *
     * @param courses A list of courses that the user choose
     * @return all possible timetables which contains one list of sessions for each course without time conflict
     */
    public static List<List<List<Session>>> getAllTimeTable(List<Course> courses) {
        List<List<List<Session>>> possibleTimeTable = new ArrayList<>();
        generateTimeTable(courses, 0, new ArrayList<>(), possibleTimeTable);
        return possibleTimeTable;
    }

    private static void generateTimeTable(List<Course> courses, int courseIndex,
                                          List<List<Session>> currentTimetable,
                                          List<List<List<Session>>> possibleTimeTable) {
        if (courseIndex == courses.size()) {
            // All courses processed, add the current timetable to the list of possible timetables
            possibleTimeTable.add(new ArrayList<>(currentTimetable));
            return;
        }

        for (List<Session> listOfSessions : generateCombinations(courses.get(courseIndex))) {
            if (noConflict(listOfSessions, currentTimetable)) {
                currentTimetable.add(listOfSessions);
                generateTimeTable(courses, courseIndex + 1, currentTimetable, possibleTimeTable);
                currentTimetable.remove(currentTimetable.size() - 1); // Backtrack
            }
        }
    }
    /**
     * Generate all possible combinations of sessions (one Lec, one Tut, one Pra) of a course without time conflict
     *
     * @param listOfSessions A list of sessions that could potentially be added to the timetable
     * @param timeTable A list of lists of sessions that have been added to the timetable
     * @return boolean that if the listOfSessions could be added to timeTable
     */
    private static boolean noConflict(List<Session> listOfSessions, List<List<Session>> timeTable) {
        for (List<Session> listSessions : timeTable){
            for (Session session1 : listSessions){
                for (Session session2 : listOfSessions){
                    if (!hasOverlappingDay(session1.getDay(), session2.getDay()).isEmpty()){
                        List<Integer> days = hasOverlappingDay(session1.getDay(), session2.getDay());
                        List<Integer> start = new ArrayList<>();
                        List<Integer> end = new ArrayList<>();
                        List<Integer> index1 = new ArrayList<>();
                        List<Integer> index2 = new ArrayList<>();
                        for (Integer day : days){
                            index1.add(session1.getDay().indexOf(day));
                            index2.add(session2.getDay().indexOf(day));
                        }

                        for (Integer in : index1) {
                            start.add(session1.getStartTime().get(in));
                            end.add(session1.getEndTime().get(in));
                        }
                        for (Integer in : index2){
                            start.add(session2.getStartTime().get(in));
                            end.add(session2.getEndTime().get(in));
                        }

                        if (!checkNoOverlap(start, end)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
