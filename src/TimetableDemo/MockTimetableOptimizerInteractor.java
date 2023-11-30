package TimetableDemo;

import entity.Session;
import use_case.TimeTableOutputData;
import use_case.TimetableOutputBoundary;

import java.util.ArrayList;
import java.util.List;

public class MockTimetableOptimizerInteractor {
    private TimetableOutputBoundary outputBoundary;

    private List<Session> listOfSessions = new ArrayList<>();

    public MockTimetableOptimizerInteractor(TimetableOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    public void execute(List<Session> sessions) {
        // Example data
        //List<Session> sessions = MockListSession.mockSessions();
        double distance = MockData.getMockTotalDistance();
        List<Object> mixedList = new ArrayList<>();
        TimeTableOutputData outputdata = new TimeTableOutputData(sessions, distance);

        // Output
        outputBoundary.presentTimetableOptimizationResults(outputdata);
    }
}

