package use_case;

import TimetableDemo.TimetableExecute;
import entity.Course;
import entity.Session;

import java.util.ArrayList;
import java.util.List;
import algorithm.Algorithm;


public class CourseInteractor implements CourseInputBoundary {
    private TimetableOutputBoundary outputBoundary;
    public CourseInteractor(TimetableOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }
    @Override
    public void execute(CourseInputData inputData) {
        System.out.println("reach interactor");
        List<Course> courses = inputData.getCourse();
//        List<Session> sessionList = new ArrayList<>();
        List<Session> sessionList = Algorithm.getOptimalChoice(courses).getSessions();
        TimetableExecute.TimetableExecute(sessionList);
    }
}
