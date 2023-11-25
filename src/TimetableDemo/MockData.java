package TimetableDemo;

import entity.Session;

import java.time.LocalTime;
import java.util.ArrayList;
import entity.Course;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MockData {
    public static List<Session> getMockSessions() {
        List<Session> sessions = new ArrayList<>();

        // Single-day session example
        sessions.add(createSession("MAT137 LEC0101",
                new LocalTime[]{LocalTime.of(9, 0)}, // 9 AM
                new LocalTime[]{LocalTime.of(11, 0)}, // 11 AM
                new int[]{1}, // Monday
                "AB"));

        // Multi-day session example
        sessions.add(createSession("ECO317 LEC0101",
                new LocalTime[]{LocalTime.of(15, 0), LocalTime.of(13, 0)}, // 3 PM Monday, 1 PM Tuesday
                new LocalTime[]{LocalTime.of(17, 0), LocalTime.of(15, 0)}, // 5 PM Monday, 3 PM Tuesday
                new int[]{1, 2}, // Monday and Tuesday
                "SS"));
        sessions.add(createSession("POL200 LEC0101",
                new LocalTime[]{LocalTime.of(11, 0), LocalTime.of(15, 0)}, // 11 AM Wednesday, 3 PM Friday
                new LocalTime[]{LocalTime.of(12, 0), LocalTime.of(16, 0)}, // 12 PM Wednesday, 4 PM Friday
                new int[]{3, 5}, // Wednesday and Friday
                "SS"));

        // ... Add more sessions as needed

        return sessions;
    }

    private static Session createSession(String sessionCode, LocalTime[] startTimes, LocalTime[] endTimes, int[] days, String buildingCode) {
        ArrayList<HashMap<String, Object>> sessionList = new ArrayList<>();

        for (int i = 0; i < startTimes.length; i++) {
            HashMap<String, Object> sessionData = new HashMap<>();
            sessionData.put("Start", startTimes[i].toSecondOfDay() * 1000); // Convert to milliseconds
            sessionData.put("endtime", endTimes[i].toSecondOfDay() * 1000);
            sessionData.put("Day", days[i]);
            sessionData.put("building", buildingCode);
            sessionList.add(sessionData);
        }

        Session session = new Session(sessionList);
        session.setSessionCode(sessionCode);
        return session;}
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
