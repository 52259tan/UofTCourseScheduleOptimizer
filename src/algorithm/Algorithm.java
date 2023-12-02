package algorithm;

import Distance.DistanceManager;
import entity.Course;
import entity.Session;
import entity.TimeTable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static algorithm.AllPossibleTimeTables.getAllTimeTable;
import static algorithm.getDistances.getDistance;

/** This class return a TimeTable object that contains the global optimal choice based on the courses that the user input
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
     * @return A TimeTable Object which contains list of sessions, the final optimal choice Wow!!!
     */
    public static TimeTable getOptimalChoice(List<Course> courses) {
        // allTImeTable structure below
        // [
        // [[sessions for course1], [sessions for course2],[sessions for course3],[sessions for course4], [sessions for course5]]
        // [[same structure as above]]
        // [[5 lists of sessions for each courses]]
        // ...
        // ]
        List<Course> validCourse = new ArrayList<>();
        for (Course course: courses){
            if (course.isValidCourse()){
                validCourse.add(course);
            }
        }

        //update courses
        DistanceManager.updateDistances(validCourse);

        List<List<List<Session>>> allTimeTable = getAllTimeTable(validCourse);
        List<List<Session>> flattenTimeTable = new ArrayList<>();
        for (List<List<Session>> timeTable : allTimeTable) { //sample timeTable: [[sessions for course1], [sessions for course2],[sessions for course3],[sessions for course4], [sessions for course5]]//
            flattenTimeTable.add(flattenList(timeTable)); //sample element in flattenTimeTable: [sessions for course1, sessions for course2, etc...], [],[]
        }

        List<Double> allDistances = new ArrayList<>();
        List<List<Object>> data1 = null;
        List<List<Object>> data2 = null;
        List<List<Object>> data3 = null;
        List<List<Object>> data4 = null;
        List<List<Object>> data5 = null;
        for (List<Session> timeTable : flattenTimeTable) { //sample timeTable: [sessions for course1, sessions for course2, etc...]
            data1 = new ArrayList<>();
            data2 = new ArrayList<>();
            data3 = new ArrayList<>();
            data4 = new ArrayList<>();
            data5 = new ArrayList<>();
            for (Session ses : timeTable) { //sample info in [starTime,endTime,Address] :  [[7,9],[8,10],["location1","location2"]]
                for (Integer day : ses.getDay()) { //sample days:[1,3] which 1 corresponds to [7,8,"location1"] and 3 corresponds to [9,10,"location2"]
                    Integer index = ses.getDay().indexOf(day);
                    if (day.equals(1)) {
                        data1.add(List.of(ses.getStartTime().get(index), ses.getEndTime().get(index), ses.getBuildingCode().get(index), ses.getAddress().get(index))); //sample data in data1 [[7,8,"location1"]]
                    } else if (day.equals(2)) {
                        data2.add(List.of(ses.getStartTime().get(index), ses.getEndTime().get(index), ses.getBuildingCode().get(index), ses.getAddress().get(index)));
                    } else if (day.equals(3)) {
                        data3.add(List.of(ses.getStartTime().get(index), ses.getEndTime().get(index), ses.getBuildingCode().get(index), ses.getAddress().get(index)));
                    } else if (day.equals(4)) {
                        data4.add(List.of(ses.getStartTime().get(index), ses.getEndTime().get(index), ses.getBuildingCode().get(index), ses.getAddress().get(index)));
                    } else if (day.equals(5)) {
                        data5.add(List.of(ses.getStartTime().get(index), ses.getEndTime().get(index), ses.getBuildingCode().get(index), ses.getAddress().get(index)));
                    }
                }
            }
            // Sort list data based on the startTime of each class, so the classes are in chronological order in each day
            if (data1.isEmpty()){
                data1.add(new ArrayList<>());
            }else {
                data1.sort(Comparator.comparingInt(sublist -> (int) sublist.get(0)));
            }//sample data1 [[7,8,"location1"], [9,10,"location2"]]
            if (data2.isEmpty()){
                data2.add(new ArrayList<>());
            }else {
                data2.sort(Comparator.comparingInt(sublist -> (int) sublist.get(0)));
            }
            if (data3.isEmpty()){
                data3.add(new ArrayList<>());
            }else {
                data3.sort(Comparator.comparingInt(sublist -> (int) sublist.get(0)));
            }
            if (data4.isEmpty()){
                data4.add(new ArrayList<>());
            }else {
                data4.sort(Comparator.comparingInt(sublist -> (int) sublist.get(0)));
            }
            if (data5.isEmpty()){
                data5.add(new ArrayList<>());
            }else {
                data5.sort(Comparator.comparingInt(sublist -> (int) sublist.get(0)));
            }

            // add the total distances of one timeTable, the index of allDistance and flattenTimeTable should represent the same timeTable
            allDistances.add(getDistance(data1) + getDistance(data2) + getDistance(data3) + getDistance(data4) + getDistance(data5));
        }
        // Find the index of the minimum distance
        int minIndex = findMinIndex(allDistances);
        List<Session> optimalSessions = flattenTimeTable.get(minIndex);
        Double optimalDistance = allDistances.get(minIndex);
        return new TimeTable(optimalSessions, data1, data2, data3, data4, data5, optimalDistance);
    }


    public static Integer findMinIndex(List<Double> allDistances) {
        double minValue = allDistances.get(0);
        int minIndex = 0;

        if (allDistances.isEmpty()){
            System.out.println("TimeTable Not Available");
            return -1;
        }

        for (int i = 1; i < allDistances.size(); i++) {
            if (allDistances.get(i) < minValue) {
                minValue = allDistances.get(i);
                minIndex = i;
            }
        }
        return minIndex;
    }
}
