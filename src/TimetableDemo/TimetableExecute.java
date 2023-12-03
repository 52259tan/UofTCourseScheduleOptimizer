package TimetableDemo;
import entity.Session;
import gui.*;
import use_case.TimeTableOutputData;

import javax.swing.*;
import java.util.List;

public class TimetableExecute {
    public static void TimetableExecute(TimeTableOutputData outputData) {
        List<Session> sessions = outputData.getSessions();
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

