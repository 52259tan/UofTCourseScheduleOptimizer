package use_case;

import entity.Course;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author bayanmehr
 */
public class CourseInputDataTest {

    public CourseInputData data;

    @Before
    public void init() {
        this.data = new CourseInputData(List.of("123"), true);
    }

    @Test
    public void testCodes() {
        assertEquals(data.getCourse().get(0), "123");
    }

    @Test
    public void testAlgorithm() {
        assertTrue(data.isAlgo2());
    }
}
