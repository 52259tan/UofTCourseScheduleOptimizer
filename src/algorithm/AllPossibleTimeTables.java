package algorithm;

import entity.Course;
import entity.Session;

import java.util.List;
import java.util.ArrayList;

import static algorithm.AllPossibleSessions.generateCombinations;
import static algorithm.DayOverlapChecker.hasOverlappingDay;
import static algorithm.TimeRangeOverlapChecker.checkNoOverlap;

/**
 * Generate all possible combinations of sessions (one Lec, one Tut, one Pra) of 5 courses without time conflict
 * @author pinglu
 */
public class AllPossibleTimeTables {

    /**
     * Generate all possible combinations of sessions (one Lec, one Tut, one Pra) of 5 courses without time conflict
     *
     * @param courses A list of courses that the user choose
     * @return all possible timetables which contains one list of sessions for each course without time conflict
     */
    public static List<List<List<Session>>> getAllTimeTable(List<Course> courses) {
        List<List<List<Session>>> possibleTimeTable = new ArrayList<>();
        for (List<Session> listOfSessions1 : generateCombinations(courses.get(0))) { // [[tut, lec],[tut,lec]]
            List<List<Session>> timeTable = new ArrayList<>();
            timeTable.add(listOfSessions1);
            for (List<Session> listOfSessions2 : generateCombinations(courses.get(1))) {
                if (noConflict(listOfSessions2, timeTable)){
                    timeTable.add(listOfSessions2);
                    for (List<Session> listOfSessions3 : generateCombinations(courses.get(2))) {
                        if (noConflict(listOfSessions3, timeTable)){
                            timeTable.add(listOfSessions3);
                            for (List<Session> listOfSessions4 : generateCombinations(courses.get(3))) {
                                if (noConflict(listOfSessions4, timeTable)){
                                    timeTable.add(listOfSessions4);
                                    for (List<Session> listOfSessions5 : generateCombinations(courses.get(4))) {
                                        if (noConflict(listOfSessions5, timeTable)){
                                            timeTable.add(listOfSessions5);
                                            possibleTimeTable.add(timeTable);
                                    }// [[[lec1,tu1],[lec2, tut2].....]]]
                                    }
                            }
                        }
                    }
                }
            }
            }
        }
        return possibleTimeTable;
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

