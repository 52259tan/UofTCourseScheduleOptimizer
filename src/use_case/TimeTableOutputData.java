package use_case;

import data_access.SessionDTO;

import java.util.List;
/**
 * Represents the output data for a timetable, including a list of courses and the total distance.
 */
public class TimeTableOutputData {

    private List<SessionDTO> sessions;

    /**
     * Constructs a new TimeTableOutputData object with the given list of courses and total distance.
     *
     * @param sessions The list of Course entity in the timetable.
     */
    public TimeTableOutputData(List<SessionDTO> sessions) {
        this.sessions = sessions;
    }
    public List<SessionDTO> getSessions() {
        return sessions;
    }

}
