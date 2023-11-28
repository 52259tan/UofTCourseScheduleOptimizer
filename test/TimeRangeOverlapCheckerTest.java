import algorithm.TimeRangeOverlapChecker;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Numerous tests for TimeRangeOverlapChecker.java
 * @author bayanmehr
 */
public class TimeRangeOverlapCheckerTest {

    private List<Integer> start;
    private List<Integer> end;

    /**
     * Pre-made lists for ease of use
     */
    @Before
    public void init() {
        start = Arrays.asList(1,4,7);
        end = Arrays.asList(4,7,10);
    }

    /**
     * Basic test of non-conflicting inputs
     * In accordance with the method documentation, returns true if no overlap
     */
    @Test
    public void testNoConflict() {
        assertTrue(TimeRangeOverlapChecker.checkNoOverlap(Arrays.asList(1,5),Arrays.asList(4,9)));
        assertTrue(TimeRangeOverlapChecker.checkNoOverlap(start,end));
    }

    /**
     * Basic test of conflicting inputs
     * In accordance with the method documentation, returns false if overlap
     */
    @Test
    public void testConflict() {
        //Manipulating start list to create conflict
        start.set(2,5);
        //Now comparing (1,4,5) with (4,7,10)
        assertFalse(TimeRangeOverlapChecker.checkNoOverlap(start,end));
        //Creating two identical start and end times
        start.set(2,4);
        end.set(2,7);
        //Now comparing (1,4,4) with (4,7,7)
        assertFalse(TimeRangeOverlapChecker.checkNoOverlap(start,end));
    }

    /**
     * Testing handling of empty lists
     * Current expected behaviour: return true
     */
    @Test
    public void testEmptyList() {
        assertTrue(TimeRangeOverlapChecker.checkNoOverlap(List.of(),List.of()));
    }

    /**
     * Testing handling of invalid input lists
     */
    @Test
    public void testInvalidInput() {
        //Making a list too short
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2));
        try {
            TimeRangeOverlapChecker.checkNoOverlap(list,end);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Exception thrown");
        }
    }
}
