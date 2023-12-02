package use_case;

import static algorithm.Algorithm.getOptimalChoice;

public class CourseInteractor implements CourseInputBoundary {
    @Override
    public void execute(CourseInputData inputData) {
        getOptimalChoice(inputData.getCourse());
    }
}
