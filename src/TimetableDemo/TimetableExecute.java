package TimetableDemo;
import entity.Session;
import gui.*;

import javax.swing.*;
import java.util.List;

public class TimetableExecute {
    public static void TimetableExecute(List<Session> sessions) {
        SwingUtilities.invokeLater(() -> {
            TimetableViewModel viewModel = new TimetableViewModel();
            TimetablePresenter presenter = new TimetablePresenter(viewModel);
            TimetableView view = new TimetableView(viewModel);

            JFrame frame = new JFrame("Timetable Test");
            frame.add(view);
            frame.pack();
            frame.setVisible(true);

            // Create and use the mock interactor to simulate optimization
            MockTimetableOptimizerInteractor interactor = new MockTimetableOptimizerInteractor(presenter);
            interactor.execute(sessions);
        });
    }
}

