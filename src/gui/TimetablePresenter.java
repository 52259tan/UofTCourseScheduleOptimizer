package gui;

import entity.Session;
import use_case.TimeTableOutputData;
import use_case.TimetableOutputBoundary;

import java.util.List;

public class TimetablePresenter implements TimetableOutputBoundary {
    /**
     * Upon being called by the Interactor, the Presenter updates the state of the output view model
     */
    private TimetableViewModel timetableViewModel;

    public TimetablePresenter(TimetableViewModel timetableViewModel) {
        this.timetableViewModel = timetableViewModel;
    }

    @Override
    public void presentTimetableOptimizationResults(TimeTableOutputData outputData) {
        List<Session> sessions = outputData.getSessions();

        timetableViewModel.setSessions(sessions);
    }
}
