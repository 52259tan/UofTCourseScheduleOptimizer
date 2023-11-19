package Distance;

import API.CourseAPI;
import entity.Course;

import java.util.ArrayList;

/** Demo of creating two courses through the CourseAPI and displaying all distance info between their buildings.
 * @author Joshua Jang
 */
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
