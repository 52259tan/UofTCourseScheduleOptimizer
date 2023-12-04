package API;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
public class CourseAPIContext {
    private CourseAPIInterface courseAPI;
    public void setCourseAPI(CourseAPIInterface courseAPI){
        this.courseAPI = courseAPI;
    }
    public HashMap<String, HashMap<String, ArrayList<HashMap<String, Object>>>> courseAPIExecute(String code) throws IOException {
        return courseAPI.getCourse(code);
    }
}
