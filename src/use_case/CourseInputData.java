package use_case;

import java.io.Serializable;
import java.util.List;

public class CourseInputData implements Serializable {
    /**
     * A list of Course entities
     */
    private final List<String> courseCodes;

    public CourseInputData(List<String> courseCodes) {
        this.courseCodes = courseCodes;
    }

    public List<String> getCourse() {
        return courseCodes;
    }
}
