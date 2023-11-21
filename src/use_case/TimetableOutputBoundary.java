package use_case;

public interface TimetableOutputBoundary {
    /**
     * In the Interactor, make sure to call the Output Boundary method with an Output Data object
     * @param outputData requires the Interactor to place input into an Output Data object
     */
    void presentTimetableOptimizationResults(TimeTableOutputData outputData);
}
