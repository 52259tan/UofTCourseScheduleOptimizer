package gui;

import entity.Course;
import use_case.TimeTableOutputData;
import use_case.TimetableOutputBoundary;

import java.util.List;

public class TimetablePresenter implements TimetableOutputBoundary {
    private TimetableViewModel timetableViewModel;

    public TimetablePresenter(TimetableViewModel timetableViewModel) {
        this.timetableViewModel = timetableViewModel;
    }

    @Override
    public void presentTimetableOptimizationResults(TimeTableOutputData outputData) {
        // The interactor would have performed the logic and given us the output data
        List<Course> courses = outputData.getCourses();
        double totalDistance = outputData.getTotalDistance();

        // Update the ViewModel with the new data, which will fire property changes
        timetableViewModel.setCourses(courses);
        timetableViewModel.setTotalDistance(totalDistance);
    }
}