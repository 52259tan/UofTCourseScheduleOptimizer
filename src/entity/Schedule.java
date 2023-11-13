package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A entity.Schedule entity that contains the name of the Courses a student select for a semester or a year
 */
public class Schedule {
    private String ScheduleName;
    private List<Course> course = new ArrayList<>();

    public Schedule(String ScheduelName, Course course1, Course course2, Course course3, Course course4, Course course5){
        this.ScheduleName = ScheduelName;
        this.course.add(course1);
        this.course.add(course2);
        this.course.add(course3);
        this.course.add(course4);
        this.course.add(course5);
    }
}
