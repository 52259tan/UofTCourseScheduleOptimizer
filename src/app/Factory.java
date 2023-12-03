package app;

import gui.*;
import use_case.CourseInputBoundary;
import use_case.CourseInteractor;
import use_case.TimetableOutputBoundary;

public class Factory {
    /**
     * This factory creates a controller that is required to start the Input View in Main
     */
    public static TimetableView timetableview;
    public static CourseController createCourseController() {
        TimetableViewModel timetableViewModel = new TimetableViewModel();
        TimetableOutputBoundary timetableOutputBoundary = new TimetablePresenter(timetableViewModel);
        CourseInputBoundary courseInteractor = new CourseInteractor(timetableOutputBoundary);
        timetableview = new TimetableView(timetableViewModel);

        return new CourseControllerImpl(courseInteractor);
    }

}
