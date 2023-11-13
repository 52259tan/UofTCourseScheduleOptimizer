package use_case;

import entity.Course;

import java.util.List;

public class TimeTableOutputData {
    private List<Course> courses;
    private double distance;
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
