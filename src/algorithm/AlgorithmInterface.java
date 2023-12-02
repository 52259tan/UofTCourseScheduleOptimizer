package algorithm;

import entity.Course;
import entity.TimeTable;

import java.util.List;

public interface AlgorithmInterface {

    abstract TimeTable getOptimalChoice(List<Course> courseList);
}
