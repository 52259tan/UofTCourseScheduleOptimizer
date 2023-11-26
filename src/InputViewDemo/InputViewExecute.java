package InputViewDemo;


import API.CourseAPI;
import TimetableDemo.TimetableExecute;
import algorithm.Algorithm;
import entity.Session;
import gui.CourseController;
import gui.CourseInputView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import entity.Course;
import use_case.CourseInputData;

public class InputViewExecute {
    /**
     * The output of this demo tests for 2 things:
     * Whether the program captured all the user inputs ("Submitted courses")
     * Whether user-input course names have been correctly translated to Course entities
     * If there's an error occur it is very likely related to missing building codes
     */
    public static void InputViewExecute() {
        SwingUtilities.invokeLater(() -> {
            CourseController mockController = new CourseController() {
                @Override
                public void execute(List<String> courseCodes) {
                    ArrayList<Session> sessionList = new ArrayList<Session>();
                    for (String code: courseCodes){
                         Course course = new Course(CourseAPI.getCourse(code));
                         sessionList.add(course.getLecSessions().get(0));
                         sessionList.add(course.getTutSessions().get(0));
                    }

                    TimetableExecute.TimetableExecute(sessionList);

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

                    // Verification Logic
                    boolean isCorrect = verifyInputData(inputData, courseCodes);
                    System.out.println("Verification Passed: " + isCorrect);




                }

                private boolean verifyInputData(CourseInputData inputData, List<String> expectedCodes) {
                    List<Course> inputCourses = inputData.getCourse();
                    if (inputCourses.size() != expectedCodes.size()) {
                        return false;
                    }

                    for (Course course : inputCourses) {
                        if (!expectedCodes.contains(course.getCourseName())) {
                            return false;
                        }
                    }

                    return true;
                }
            };

            JFrame frame = new JFrame("Course Input Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            CourseInputView inputView = new CourseInputView(mockController);
            frame.getContentPane().add(inputView);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
    }
