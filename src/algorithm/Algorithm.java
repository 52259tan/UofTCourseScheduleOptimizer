package algorithm;

import entity.Course;
import entity.Session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static algorithm.AllPossibleTimeTables.getAllTimeTable;
import static algorithm.getDistances.getDistance;

/** This class return the global optimal choice based on the courses that the user input
 * @author ping
 */
public class Algorithm {
    /**
     * This method return a list of the sessions of one timetable
     *
     * @param timeTable the timetable that is a list of lists of sessions
     * @return A list of Session objects
     */
    private static List<Session> flattenList(List<List<Session>> timeTable) {
        List<Session> result = new ArrayList<>();

        for (List<Session> element : timeTable) {
            result.addAll(element);
        }
        return result;
    }

    /**
     * This method return a list of the distance of each timeTable
     *
     * @param courses the list of Courses that the user selected
     * @return A list of sessions, the final optimal choice Wow!!!
     */
    private static List<Session> getOptimalChoice(List<Course> courses) {
        List<List<List<Session>>> allTimeTable = getAllTimeTable(courses);
        List<List<Session>> flattenTimeTable = new ArrayList<>();
        for (List<List<Session>> timeTable : allTimeTable) {
            flattenTimeTable.add(flattenList(timeTable));
        }

        List<Double> allDistances = new ArrayList<>();
        for (List<Session> timeTable : flattenTimeTable) {
            List<List<Object>> data1 = new ArrayList<>();
            List<List<Object>> data2 = new ArrayList<>();
            List<List<Object>> data3 = new ArrayList<>();
            List<List<Object>> data4 = new ArrayList<>();
            List<List<Object>> data5 = new ArrayList<>();
            for (Session ses : timeTable) {
                if (ses.getDay().equals(1)) {
                    data1.add(List.of(ses.getStartTime(), ses.getEndTime(), ses.getAddress()));
                } else if (ses.getDay().equals(2)) {
                    data2.add(List.of(ses.getStartTime(), ses.getEndTime(), ses.getAddress()));
                } else if (ses.getDay().equals(23)) {
                    data3.add(List.of(ses.getStartTime(), ses.getEndTime(), ses.getAddress()));
                } else if (ses.getDay().equals(4)) {
                    data4.add(List.of(ses.getStartTime(), ses.getEndTime(), ses.getAddress()));
                } else if (ses.getDay().equals(5)) {
                    data5.add(List.of(ses.getStartTime(), ses.getEndTime(), ses.getAddress()));
                }
                // Sort the list based on the start time (index 0)
                Collections.sort(data1, Comparator.comparingInt(list -> (Integer) list.get(0)));
                Collections.sort(data2, Comparator.comparingInt(list -> (Integer) list.get(0)));
                Collections.sort(data3, Comparator.comparingInt(list -> (Integer) list.get(0)));
                Collections.sort(data4, Comparator.comparingInt(list -> (Integer) list.get(0)));
                Collections.sort(data5, Comparator.comparingInt(list -> (Integer) list.get(0)));
                allDistances.add(getDistance(data1) + getDistance(data2) + getDistance(data3) + getDistance(data4) + getDistance(data5));
            }
        }


        // Find the index of the minimum distance
        int minIndex = findMinIndex(allDistances);
        return flattenTimeTable.get(minIndex);
    }

    public static Integer findMinIndex(List<Double> allDistances) {

        double minValue = allDistances.get(0);
        int minIndex = 0;

        for (int i = 1; i < allDistances.size(); i++) {
            if (allDistances.get(i) < minValue) {
                minValue = allDistances.get(i);
                minIndex = i;
            }
        }
        return minIndex;
    }
}
