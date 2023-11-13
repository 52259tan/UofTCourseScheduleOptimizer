package algorithm;
import java.util.ArrayList;
import java.util.List;

import API.CourseAPI;
import entity.Course;
import entity.Schedule;
import entity.Session;

// A map is a unique schedule, if two maps have exactly the same sessions, tuts, and pra, then they are the same map
// We use randomly create 1000 maps, and calculate the total distance for each, and then output the map that have the least total distance
public class Map {
    private final Schedule schedule;
    private final Session sessions;
    private final List<Double> distances;
    private final double totalDistance;

    public Map(List<Course> courses, Schedule schedule, Session sessions, List<Double> distances) {
        this.schedule = schedule;
        this.sessions = sessions;
        this.courses = courses;
        this.distances = distances;
        this.totalDistance = calculateTotalDistance();
    }

    private double calculateTotalDistance() {
        if (distances.size() == courses.size() - 1) {
            double total = 0.0;
            for (Double distance : distances) {
                total += distance;
            }
            return total;
        } else {
            throw new IllegalArgumentException("Number of distances should be one less than the number of courses.");
        }
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Double> getDistances() {
        return distances;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    // test case
    public static void main(String[] args) {
        List<Course> courses = new ArrayList<>();
        Course cs207 = new Course(CourseAPI.getCourse("CSC207H1 -F"));
        courses.add(cs207);

        List<Double> distances = new ArrayList<>();


        Map myMap = new Map(courses, schedule, sessions, distances);

        System.out.println("Courses: " + myMap.getCourses());
        System.out.println("Distances: " + myMap.getDistances());
        System.out.println("Total Distance: " + myMap.getTotalDistance());
    }
}
