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
        // Print the submitted course codes
        System.out.println("Submitted courses: " + courseCodes);

        List<Course> courses = new ArrayList<>();
        for (String code : courseCodes) {
            // Replace with actual Course creation logic or a mock version
            Course course = new Course(CourseAPI.getCourse(code));
            System.out.println("Processing: " + course.getCourseName());
            courses.add(course);
        }

        CourseInputData inputData = new CourseInputData(courses);
        courseInteractor.execute(inputData);
    }
    }
