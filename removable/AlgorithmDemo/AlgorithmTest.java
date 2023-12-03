package AlgorithmDemo;

import entity.Course;
import entity.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * This test class is to check if the recursive algorithm work
 */
public class AlgorithmTest {
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

    private static boolean noConflict(List<Session> sessions, List<List<Session>> timetable) {
        // Implement your conflict resolution logic here
        // Return true if there is no conflict, false otherwise
        return true;
    }

    private static List<List<Session>> generateCombinations(Course course) {
        // Implement the logic to generate combinations of sessions for a given course
        // Return a list of session combinations
        return new ArrayList<>();
    }

}
