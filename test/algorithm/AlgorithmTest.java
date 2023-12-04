package algorithm;

import API.CourseAPI;
import entity.Course;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static algorithm.Algorithm.*;
import static org.junit.Assert.*;

public class AlgorithmTest {

    @Test
    public void testDemo() throws IOException {
        List<Course> courses = new ArrayList<>();

        Course csc207 = new Course(CourseAPI.getCourse("CSC207H1 -F"));
        Course CHM222 = new Course(CourseAPI.getCourse("CHM222H1 -F"));
        Course act391 = new Course(CourseAPI.getCourse("ACT391H1 -F"));
        Course chm299 = new Course(CourseAPI.getCourse("CHM299H1 -F"));
        Course cdn307 = new Course(CourseAPI.getCourse("CDN307H1 -F"));



        courses.add(csc207);
        courses.add(CHM222);
        courses.add(act391);
        courses.add(chm299);
        courses.add(cdn307);
        System.out.println(getOptimalChoice(courses));
    }

    /**
     * Basic test for findMinIndex
     * @author bayanmehr
     */
    @Test
    public void testMin(){
        List<Double> list = new ArrayList<>(Arrays.asList(100.0,50.0,0.2,1.0,2.0));
        assertEquals((Integer) 2, Algorithm.findMinIndex(list));
        list.set(3, 1.0);
        assertEquals((Integer) 2, Algorithm.findMinIndex(list));
    }

    /**
     * Test for findMinIndex with empty list
     * @author bayanmehr
     */
    @Test
    public void testMinEmpty(){
        //empty list
        assertEquals((Integer) (-1), Algorithm.findMinIndex(new ArrayList<>()));
    }
}
