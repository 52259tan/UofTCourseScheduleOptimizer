package use_case;

import java.io.Serializable;
import java.util.List;

public class CourseInputData implements Serializable {
    /**
     * A list of Course entities
     */
    private final List<String> courseCodes;
    private boolean algo2;

    public CourseInputData(List<String> courseCodes, boolean algo2) {
        this.courseCodes = courseCodes;
        this.algo2 = algo2;
    }

    public List<String> getCourse() {
        return courseCodes;
    }
    public boolean isAlgo2(){return algo2;}
}
