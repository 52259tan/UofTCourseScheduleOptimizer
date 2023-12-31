package algorithm;

import API.CourseAPI;
import entity.Course;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static algorithm.AllPossibleTimeTables.getAllTimeTable;

public class AllPossibleTimeTablesTest {
    @Test
    public void testDemo() throws IOException {
        List<Course> courses = new ArrayList<>();

        Course csc207 = new Course(CourseAPI.getCourse("CSC207H1 -F"));
        Course mat235 = new Course(CourseAPI.getCourse("CHM222H1 -F"));
        Course act391 = new Course(CourseAPI.getCourse("ACT391H1 -F"));
        Course chm299 = new Course(CourseAPI.getCourse("CHM299H1 -F"));
        Course cdn307 = new Course(CourseAPI.getCourse("CDN307H1 -F"));



        courses.add(csc207);
        courses.add(mat235);
        courses.add(act391);
        courses.add(chm299);
        courses.add(cdn307);
        System.out.println(getAllTimeTable(courses));
    }
}
