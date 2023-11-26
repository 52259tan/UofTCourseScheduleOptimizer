package use_case;

public interface CourseInputBoundary {
    /**
     * Make sure Interactor implements this
     * @param inputData list of Course entities
     */
    void execute(CourseInputData inputData);
}
