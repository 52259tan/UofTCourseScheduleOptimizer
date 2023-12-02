package gui;

import entity.Course;
import java.util.ArrayList;
import java.util.List;
import API.CourseAPI; // Assume this is the package where CourseAPI is located
import use_case.CourseInputData;
import use_case.CourseInputBoundary;

public class CourseControllerImpl implements CourseController {

    final CourseInputBoundary courseInteractor;

    public CourseControllerImpl(CourseInputBoundary courseInteractor) {
        this.courseInteractor = courseInteractor;
    }

    @Override
    public void execute(List<String> courseCodes) {
        /**
         * This method (invoked by the View) invokes the Interactor with an Input Data object
         * @param courseCodes list of course codes inputted by the user
         * @return a list of Course entities
         */
        System.out.println("Controller reached");
        List<Course> courses = new ArrayList<>();
        for (String code : courseCodes) {
            // Use the CourseAPI to create a new Course entity
            Course course = new Course(CourseAPI.getCourse(code));
            courses.add(course);

        // Create an input data object with a list of Course entities
        CourseInputData inputData = new CourseInputData(courses);

        // Pass the input data to the use case/interactor
        courseInteractor.execute(inputData);
    }
} }