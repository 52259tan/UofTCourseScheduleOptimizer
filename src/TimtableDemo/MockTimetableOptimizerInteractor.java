package TimtableDemo;

import entity.Session;
import use_case.TimeTableOutputData;
import use_case.TimetableOutputBoundary;

import java.util.ArrayList;
import java.util.List;

public class MockTimetableOptimizerInteractor {
    private TimetableOutputBoundary outputBoundary;

    public MockTimetableOptimizerInteractor(TimetableOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    public void execute() {
        // Example data
        List<Session> sessions = MockData.getMockSessions();
        double distance = MockData.getMockTotalDistance();
        List<Object> mixedList = new ArrayList<>();
        TimeTableOutputData outputdata = new TimeTableOutputData(sessions, distance);

        // Output
        outputBoundary.presentTimetableOptimizationResults(outputdata);
    }
}

