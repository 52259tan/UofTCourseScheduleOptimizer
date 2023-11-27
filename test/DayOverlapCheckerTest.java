import algorithm.DayOverlapChecker;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Numerous tests for DayOverlapChecker.java
 * @author bayanmehr
 */
public class DayOverlapCheckerTest {

    private List<Integer> longList;
    private List<Integer> shortList;

    /**
     * Pre-made lists for ease of use
     */
    @Before
    public void init(){
        longList = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        shortList = new ArrayList<>(Arrays.asList(1,2));
    }

    /**
     * Tests whether the method properly handles null lists
     * Fail statement activates if the method returns anything
     */
    @Test
    public void testNulls() {
        try {
            DayOverlapChecker.hasOverlappingDay(null, null);
            fail("Exception failed to throw");
        }
        catch (IllegalArgumentException e) {
            System.out.println("Exception caught");
        }
    }

    /**
     * Tests if the method can handle both empty lists
     */
    @Test
    public void testBothEmptyList() {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        assertEquals(DayOverlapChecker.hasOverlappingDay(list1,list2), List.of());
    }

    /**
     * Tests if the method properly handles the second list being empty
     */
    @Test
    public void testSecondEmptyList() {
        List<Integer> list2 = new ArrayList<>();
        assertEquals(DayOverlapChecker.hasOverlappingDay(shortList,list2), List.of());
    }

    /**
     * Checks that the function terminates given list1 being shorter and gives the correct result
     * @see ""This test currently fails and needs to be debugged, run test for expected vs. actual""
     */
    @Test
    public void testShorterList() {
        assertEquals(DayOverlapChecker.hasOverlappingDay(shortList,longList),shortList);
    }

    /**
     * Checks that the function terminates given list1 being longer and gives the correct result
     * @see ""This test currently fails and needs to be debugged, run test for expected vs. actual""
     */
    @Test
    public void testLongerList() {
        assertEquals(DayOverlapChecker.hasOverlappingDay(longList,shortList),shortList);
    }
}
