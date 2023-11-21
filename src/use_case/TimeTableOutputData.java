package use_case;

import entity.Session;

import java.util.List;
/**
 * Represents the output data for a timetable, including a list of courses and the total distance.
 */
public class TimeTableOutputData {

    private List<Session> sessions;
    private double distance;
    /**
     * Constructs a new TimeTableOutputData object with the given list of courses and total distance.
     *
     * @param sessions   The list of Course entity in the timetable.
     * @param distance  The total distance of the timetable.
     */
    public TimeTableOutputData(List<Session> sessions, double distance) {
        this.sessions = sessions;
        this.distance = distance;
    }
    public List<Session> getSessions() {
        return sessions;
    }
    public double getTotalDistance() {
        return distance;
    }
}
