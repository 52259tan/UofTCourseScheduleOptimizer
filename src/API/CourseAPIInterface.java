package API;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
public interface CourseAPIInterface {
    abstract HashMap<String, HashMap<String, ArrayList<HashMap<String,Object>>>> getCourse(String course) throws IOException;
}