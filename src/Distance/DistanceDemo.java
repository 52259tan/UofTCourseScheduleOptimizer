package Distance;

import API.CourseAPI;
import Course.Course;

import java.util.ArrayList;

public class DistanceDemo {
    public static void main(String[] args) {
        ArrayList<Course> courses = new ArrayList<>();

        Course csc207 = new Course(CourseAPI.getCourse("CSC207H1 -F"));
        Course cog250 = new Course(CourseAPI.getCourse("COG250Y1 -Y"));

        courses.add(csc207);
        courses.add(cog250);

        ArrayList<DistanceData> distanceData = DistanceManager.getDistances(courses);
        for(DistanceData data : distanceData) {
            System.out.println(data.toString());
        }
    }
}
