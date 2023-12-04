package use_case;

import API.CourseAPIContext;
import algorithm.AlgorithmContext;
import gui.TimetablePresenter;
import org.junit.Before;

/**
 * @author bayanmehr
 */

public class CourseInteractorTest {
    public void successTest() {


        TimetableOutputBoundary timetablePresenter = new TimetableOutputBoundary() {
            @Override
            public void presentTimetableOptimizationResults(TimeTableOutputData outputData) {
                System.out.println(outputData);
            }
        };
    }
}
