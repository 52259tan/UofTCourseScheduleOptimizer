package InputViewDemo;
import API.CourseAPI;
import TimetableDemo.TimetableExecute;
import algorithm.Algorithm;
import entity.Session;
import gui.CourseController;
import gui.CourseControllerImpl;
import gui.CourseInputView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import entity.Course;
import use_case.CourseInputBoundary;
import use_case.CourseInputData;
import use_case.CourseInteractor;


public class InputViewExecuteTest {
    SwingUtilities.invokeLater(() -> {
        CourseInputBoundary courseInputBoundary = new CourseInteractor();
        CourseInputBoundary courseInteractor = // ... create or obtain an instance

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
