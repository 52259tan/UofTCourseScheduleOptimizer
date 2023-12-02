package algorithm;

import entity.Course;
import entity.TimeTable;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmContext {

    private AlgorithmInterface algorithm;

    private List<Course> courseList = new ArrayList<>();

    public AlgorithmContext(){}

    public void setAlgorithm(AlgorithmInterface algorithm){
        this.algorithm = algorithm;
    }

    public TimeTable executeAlgorithm(List<Course> courseList){
       return algorithm.getOptimalChoice(courseList);
    }
}
