package algorithm;

import entity.Course;
import entity.TimeTable;

import java.util.List;

public class Algorithm1 implements AlgorithmInterface{
    @Override
    public TimeTable getOptimalChoice(List<Course> courseList) {
        System.out.println("Algo 1 is used");
        return Algorithm.getOptimalChoice(courseList);
    }

}
