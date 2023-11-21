package TimetableDemo;

import entity.Session;

import java.time.LocalTime;
import java.util.ArrayList;
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
        return session;
    }

    public static double getMockTotalDistance() {
        return 5.0; // An example total distance
    }
}
