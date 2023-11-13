package TimtableDemo;
import gui.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TimetableViewModel viewModel = new TimetableViewModel();
            TimetablePresenter presenter = new TimetablePresenter(viewModel);
            TimetableView view = new TimetableView(viewModel);

            JFrame frame = new JFrame("Timetable Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(view);
            frame.pack();
            frame.setVisible(true);

            // Create and use the mock interactor to simulate optimization
            MockTimetableOptimizerInteractor interactor = new MockTimetableOptimizerInteractor(presenter);
            interactor.execute();
        });
    }
}

