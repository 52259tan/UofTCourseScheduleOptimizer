package gui;

import java.util.List;
import use_case.CourseInputData;
import use_case.CourseInputBoundary;

public class CourseControllerImpl implements CourseController {

    final CourseInputBoundary courseInteractor;

    public CourseControllerImpl(CourseInputBoundary courseInteractor) {
        this.courseInteractor = courseInteractor;
    }

    @Override
    public void execute(List<String> courseCodes, boolean algo2) {
        /**
         * This method (invoked by the View) invokes the Interactor with an Input Data object
         * @param courseCodes list of course codes inputted by the user
         * @return a list of Course entities
         */
        // Print the submitted course codes
        System.out.println("Submitted courses: " + courseCodes);

        CourseInputData inputData = new CourseInputData(courseCodes, algo2);
        courseInteractor.execute(inputData);
    }
    }
