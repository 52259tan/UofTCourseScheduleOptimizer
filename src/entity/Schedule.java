package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * An entity.Schedule entity that contains the name of the Courses a student select for a semester or a year
@author ping
 */
public class Schedule {
    private String ScheduleName;
    private List<Course> courses = new ArrayList<>();

    // Assume user input have 5 courses
    //TODO: change the input parameter such that user can choose arbitrary number of courses
    public Schedule(String ScheduelName, Course course1, Course course2, Course course3, Course course4, Course course5) {
        this.ScheduleName = ScheduelName;
        this.courses.add(course1);
        this.courses.add(course2);
        this.courses.add(course3);
        this.courses.add(course4);
        this.courses.add(course5);
    }

    public int size() {
        return courses.size();
    }
}
