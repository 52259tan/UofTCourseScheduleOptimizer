package InputViewDemo;
import app.Factory;
import gui.*;

import javax.swing.*;
import java.io.IOException;

import static app.Factory.timetableview;

public class InputViewExecuteTest {
    public static void InputViewExecuteTest() {
        SwingUtilities.invokeLater(() -> {
            CourseController realController = Factory.createCourseController();

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
            // output view
            JFrame frame2 = new JFrame("Timetable Test");
            frame2.add(timetableview);
            frame2.pack();
            frame2.setVisible(true);
        });
    }
}
