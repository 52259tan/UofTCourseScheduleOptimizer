package TimetableDemo;

import entity.Course;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MockData {
    public static List<Course> getMockCourses() {
        // Create mock session data for Math101
        Course mathCourse = createCourse(
                "MAT137",
                new String[]{"LEC0101", "TUT0101", "PRA0101"}, // session codes
                new LocalTime[]{LocalTime.of(9, 0), LocalTime.of(10, 0), LocalTime.of(10, 0)}, // start times
                new LocalTime[]{LocalTime.of(11, 0), LocalTime.of(11, 0), LocalTime.of(12, 0)}, // end times
                new int[]{1, 3, 0}, // days
                "AB" // building code
        );

        // Create mock session data for Physics201
        Course physicsCourse = createCourse(
                "PHY201",
                new String[]{"LEC0201", "TUT0201", "PRA0201"},
                new LocalTime[]{LocalTime.of(11, 0), LocalTime.of(12, 0), LocalTime.of(15, 0)},
                new LocalTime[]{LocalTime.of(13, 0), LocalTime.of(13, 0), LocalTime.of(16, 0)},
                new int[]{2, 4, 1},
                "AB"
        );

        // Create mock session data for Chemistry301
        Course chemistryCourse = createCourse(
                "CHEM301",
                new String[]{"TUT0301", "LEC0301", "PRA0301"},
                new LocalTime[]{LocalTime.of(14, 0), LocalTime.of(8, 0), LocalTime.of(17, 0)},
                new LocalTime[]{LocalTime.of(16, 0), LocalTime.of(9, 0), LocalTime.of(19, 0)},
                new int[]{3, 0, 4},
                "AB"
        );

        return Arrays.asList(mathCourse, physicsCourse, chemistryCourse);
    }

    private static Course createCourse(String courseName, String[] sessionCodes, LocalTime[] startTimes, LocalTime[] endTimes, int[] days, String building) {
        HashMap<String, HashMap<String, ArrayList<HashMap<String, Object>>>> courseInfo = new HashMap<>();
        HashMap<String, ArrayList<HashMap<String, Object>>> courseSessions = new HashMap<>();

        for (int i = 0; i < sessionCodes.length; i++) {
            HashMap<String, Object> sessionData = new HashMap<>();
            sessionData.put("Start", startTimes[i].toSecondOfDay() * 1000); // Converting to milliseconds
            sessionData.put("endtime", endTimes[i].toSecondOfDay() * 1000);
            sessionData.put("Day", days[i]);
            sessionData.put("building", building);

            ArrayList<HashMap<String, Object>> sessionList = new ArrayList<>();
            sessionList.add(sessionData);

            courseSessions.put(sessionCodes[i], sessionList);
        }

        courseInfo.put(courseName, courseSessions);
        return new Course(courseInfo);
    }

    public static double getMockTotalDistance() {
        return 5.0; // An example total distance
    }
}
