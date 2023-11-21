package TimetableDemo;

import entity.Course;
import use_case.TimeTableOutputData;
import use_case.TimetableOutputBoundary;

import java.util.ArrayList;
import java.util.List;

public class MockTimetableOptimizerInteractor {
    private TimetableOutputBoundary outputBoundary;

    public MockTimetableOptimizerInteractor(TimetableOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    public void execute() {
        // Example data
        List<Course> courses = MockData.getMockCourses();
        double distance = MockData.getMockTotalDistance();
        List<Object> mixedList = new ArrayList<>();
        TimeTableOutputData outputdata = new TimeTableOutputData(courses, distance);

        // Output
        outputBoundary.presentTimetableOptimizationResults(outputdata);
    }
}

