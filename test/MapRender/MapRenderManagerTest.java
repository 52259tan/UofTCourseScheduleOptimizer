package MapRender;

import API.CourseAPI;
import entity.Course;
import entity.TimeTable;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static algorithm.Algorithm.getOptimalChoice;

/** Demo of rendering 5 maps, with waypoint markers and route line(s)
 * @author Joshua Jang
 * refactored by bayanmehr
 */
public class MapRenderManagerTest {
    @Test
    public void testDemo() throws IOException {
        List<Course> courses = new ArrayList<>();

        Course csc207 = new Course(CourseAPI.getCourse("CSC207H1 -F"));
        Course csc236 = new Course(CourseAPI.getCourse("CSC236H1 -F"));
        Course mat223 = new Course(CourseAPI.getCourse("MAT223H1 -F"));
        Course chm299 = new Course(CourseAPI.getCourse("CHM299H1 -F"));
        Course cdn307 = new Course(CourseAPI.getCourse("CDN307H1 -F"));

        courses.add(csc207);
        courses.add(csc236);
        courses.add(mat223);
        courses.add(chm299);
        courses.add(cdn307);
        TimeTable timeTable = getOptimalChoice(courses);
        System.out.println(timeTable);

        MapRenderManager.generateMapsPNG(timeTable, "mapimgs");
    }
}
