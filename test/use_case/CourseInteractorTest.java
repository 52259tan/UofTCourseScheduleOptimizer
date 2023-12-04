package use_case;

import API.CourseAPIContext;
import algorithm.AlgorithmContext;
import gui.TimetablePresenter;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import static org.junit.Assert.*;

/**
 * @author bayanmehr
 */

public class CourseInteractorTest {
    @Test
    public void successTest() {

        CourseInputData courseInputData = new CourseInputData(Arrays.asList("CSC207H1 -F", "CSC236H1 -F"), false);

        TimetableOutputBoundary timetablePresenter = new TimetableOutputBoundary() {
            @Override
            public void presentTimetableOptimizationResults(TimeTableOutputData outputData) {
                assertNotNull(outputData);
            }
        };

        CourseInteractor courseInteractor = new CourseInteractor(timetablePresenter);
        courseInteractor.execute(courseInputData);
    }
}
