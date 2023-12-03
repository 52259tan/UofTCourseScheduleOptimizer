package use_case;

import API.CourseAPI;
import MapRender.MapRenderManager;
import java.util.stream.Collectors;
import data_access.SessionDTO;
import entity.Course;
import entity.Session;

import java.util.ArrayList;
import java.util.List;
import algorithm.Algorithm;
import entity.TimeTable;


public class CourseInteractor implements CourseInputBoundary {
    /**
     * This class creates Session and Class entities, then invoke the Algorithm to find optimal output for the sessions
     */
    private TimetableOutputBoundary outputBoundary;
    public CourseInteractor(TimetableOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }
    @Override
    public void execute(CourseInputData inputData) {
        List<String> courseCodes  = inputData.getCourse();
        List<Course> courses = new ArrayList<>();
        System.out.println("reach interactor");
        for (String code : courseCodes) {
            // Replace with actual Course creation logic or a mock version
            Course course = new Course(CourseAPI.getCourse(code));
            System.out.println("Processing: " + course.getCourseName());
            courses.add(course);
        }
        TimeTable timeTable = Algorithm.getOptimalChoice(courses);
        List<Session> sessionList = timeTable.getSessions();
        System.out.println(sessionList);
        // Generate map images
        MapRenderManager.generateMapsPNG(timeTable,"mapimgs");
        // Mapping Session to SessionDTO
        List<SessionDTO> sessionDTOs = sessionList.stream()
                .map(session -> new SessionDTO(
                        session.getSessionCode(),
                        session.getStartTime(),
                        session.getEndTime(),
                        session.getDay(),
                        session.getAddress(),
                        session.getBuildingCode()
                ))
                .collect(Collectors.toList());

        TimeTableOutputData timeTableOutputData = new TimeTableOutputData(sessionDTOs);
        outputBoundary.presentTimetableOptimizationResults(timeTableOutputData);
    }
}
