package use_case;

import entity.Course;

import java.util.List;
/**
 * Represents the output data for a timetable, including a list of courses and the total distance.
 */
public class TimeTableOutputData {

    private List<Course> courses;
    private double distance;
    /**
     * Constructs a new TimeTableOutputData object with the given list of courses and total distance.
     *
     * @param courses   The list of Course entity in the timetable.
     * @param distance  The total distance of the timetable.
     */
    public TimeTableOutputData(List<Course> courses, double distance) {
        this.courses = courses;
        this.distance = distance;
    }
    public List<Course> getCourses() {
        return courses;
    }
    public double getTotalDistance() {
        return distance;
    }
}
