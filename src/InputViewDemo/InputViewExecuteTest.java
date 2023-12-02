package InputViewDemo;
import API.CourseAPI;
import TimetableDemo.MockTimetableOptimizerInteractor;
import TimetableDemo.TimetableExecute;
import entity.Session;
import gui.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import entity.Course;
import use_case.CourseInputBoundary;
import use_case.CourseInputData;
import use_case.CourseInteractor;
import use_case.TimetableOutputBoundary;


public class InputViewExecuteTest {
    public static void InputViewExecuteTest() {
        SwingUtilities.invokeLater(() -> {
            TimetableViewModel timetableViewModel = new TimetableViewModel();
            TimetableOutputBoundary timetableOutputBoundary = new TimetablePresenter(timetableViewModel);
            CourseInputBoundary courseInteractor = new CourseInteractor(timetableOutputBoundary);

            CourseController realController = new CourseControllerImpl(courseInteractor);

            JFrame frame = new JFrame("Course Input Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            AutoSuggest autoSuggest;
            try {
                autoSuggest = new AutoSuggest(realController);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            frame.getContentPane().add(autoSuggest);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
