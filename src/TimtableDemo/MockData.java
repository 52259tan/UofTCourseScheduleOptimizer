package TimtableDemo;

import entity.Course;
import entity.Session;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MockData {
    public static List<Session> getMockSessions() {
        List<Session> sessions = new ArrayList<>();

        // Add sessions for Math101
        sessions.add(createSession("MAT137 LEC0101", LocalTime.of(9, 0), LocalTime.of(11, 0), 1, "AB"));
        sessions.add(createSession("MAT137 TUT0101", LocalTime.of(10, 0), LocalTime.of(11, 0), 3, "AB"));
        sessions.add(createSession("ECO317 LEC0101", LocalTime.of(11, 0), LocalTime.of(13, 0), 3, "SS"));
        sessions.add(createSession("ECO317 TUT0101", LocalTime.of(11, 0), LocalTime.of(13, 0), 5, "MS"));
        // ... Add more sessions as needed

        return sessions;
    }

    private static Session createSession(String sessionCode, LocalTime startTime, LocalTime endTime, int day, String buildingCode) {
        HashMap<String, Object> sessionData = new HashMap<>();
        sessionData.put("Start", startTime.toSecondOfDay() * 1000); // Convert to milliseconds
        sessionData.put("endtime", endTime.toSecondOfDay() * 1000);
        sessionData.put("Day", day);
        sessionData.put("building", buildingCode);

        ArrayList<HashMap<String, Object>> sessionList = new ArrayList<>();
        sessionList.add(sessionData);

        Session session = new Session(sessionList);
        session.setSessionCode(sessionCode);
        return session;
    }

    public static double getMockTotalDistance() {
        return 5.0; // An example total distance
    }
}
