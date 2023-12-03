package use_case;

import MapRender.MapRenderManager;
import entity.Course;
import entity.Session;

import java.util.ArrayList;
import java.util.List;
import algorithm.Algorithm;
import entity.TimeTable;


public class CourseInteractor implements CourseInputBoundary {
    private TimetableOutputBoundary outputBoundary;
    public CourseInteractor(TimetableOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }
    @Override
    public void execute(CourseInputData inputData) {
        System.out.println("reach interactor");
        List<Course> courses = inputData.getCourse();
        TimeTable timeTable = Algorithm.getOptimalChoice(courses);
        List<Session> sessionList = timeTable.getSessions();
        System.out.println(sessionList);
        // Generate map images
        MapRenderManager.generateMapsPNG(timeTable,"mapimgs");

        TimeTableOutputData timeTableOutputData = new TimeTableOutputData(sessionList);
        outputBoundary.presentTimetableOptimizationResults(timeTableOutputData);
    }
}
