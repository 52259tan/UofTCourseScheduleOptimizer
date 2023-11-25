package use_case;

import entity.Course;

import java.io.Serializable;
import java.util.List;

public class CourseInputData implements Serializable {
    /**
     * A list of Course entities
     */
    private final List<Course> courseCodes;

    public CourseInputData(List<Course> courseCodes) {
        this.courseCodes = courseCodes;
    }

    public List<Course> getCourse() {
        return courseCodes;
    }
}
