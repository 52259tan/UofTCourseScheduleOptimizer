package app;

import gui.CourseController;
import gui.CourseInputView;

import javax.swing.*;
import java.io.IOException;

import static app.Factory.timetableview;

public class Main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            CourseController realController = Factory.createCourseController();

            JFrame frame = new JFrame("Course Input");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            CourseInputView courseInputView;
            try {
                courseInputView = new CourseInputView(realController);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            frame.getContentPane().add(courseInputView);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            // output view
            JFrame frame2 = new JFrame("Timetable");
            frame2.add(timetableview);
            frame2.pack();
            frame2.setVisible(true);
        });
    }
}
