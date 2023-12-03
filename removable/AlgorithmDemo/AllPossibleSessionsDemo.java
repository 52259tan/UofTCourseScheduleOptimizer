package AlgorithmDemo;


import API.CourseAPI;
import entity.Course;


import static algorithm.AllPossibleSessions.generateCombinations;


public class AllPossibleSessionsDemo {
    public static void main(String[] args) {

        Course csc207 = new Course(CourseAPI.getCourse("CSC207H1 -F"));
        System.out.println(generateCombinations(csc207));




    }
}