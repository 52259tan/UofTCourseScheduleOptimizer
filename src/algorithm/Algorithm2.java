package algorithm;

import entity.Course;
import entity.TimeTable;

import java.util.List;

public class Algorithm2 implements AlgorithmInterface{
    @Override
    public TimeTable getOptimalChoice(List<Course> courseList) {
        System.out.println("Algo 2 is used");
        return Algorithm.getOptimalChoice(courseList);
    }
}
