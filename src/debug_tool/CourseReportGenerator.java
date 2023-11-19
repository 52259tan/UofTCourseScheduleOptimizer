package debug_tool;

import java.io.*;
import java.util.*;
import entity.Course;
import API.CourseAPI;

public class CourseReportGenerator {

    public static void main(String[] args) {
        String coursesFile = "availableCourses.txt";

        List<String> courses = readCourses(coursesFile);

        Set<String> failedCourses = new HashSet<>();

        for (String courseCode : courses) {
            try {
                Course course = new Course(CourseAPI.getCourse(courseCode));
                // Process the course normally if no exception is thrown
            } catch (RuntimeException e) {
                failedCourses.add(courseCode);
                System.out.println(failedCourses);
                // Add to failed courses if exception occurs
            }
        }

        // Output the failed courses
        System.out.println("Failed Courses:");
        failedCourses.forEach(System.out::println);
    }

    private static List<String> readCourses(String filePath) {
        List<String> courses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                courses.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courses;
    }
}