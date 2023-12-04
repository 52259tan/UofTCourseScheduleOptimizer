package algorithm;

import API.CourseAPI;
import entity.Course;
import org.junit.Test;

import java.io.IOException;

import static algorithm.AllPossibleSessions.generateCombinations;

public class AllPossibleSessionsTest {
    @Test
    public void testDemo() throws IOException {
        Course csc207 = new Course(CourseAPI.getCourse("CSC207H1 -F"));
        System.out.println(generateCombinations(csc207));
    }
}
