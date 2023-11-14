package entity;
import java.util.ArrayList;
import java.util.List;

import API.CourseAPI;
import entity.Course;
import entity.Schedule;
import entity.Session;
import kotlin.NotImplementedError;

/** A map is a unique schedule, if two maps have exactly the same sessions, tuts, and pra, then they are the same map
 */
 // We use randomly create 1000 maps, and calculate the total distance for each, and then output the map that have the least total distance
public class Map {
    private final Schedule schedule;
    private final List<Double> distances;
    private final List<Session> sessions;
    private final double totalDistance;

    public Map(Schedule schedule) {
        this.schedule = schedule;
        this.sessions = (List<Session>) this.schedule.getRandomSessionsForSchedule();
        this.distances = this.getDistances();
        this.totalDistance = calculateTotalDistance();
    }


    private double calculateTotalDistance() {
        if (distances.size() == this.schedule.size() - 1) {
            double total = 0.0;
            for (Double distance : distances) {
                total += distance;
            }
            return total;
        } else {
            throw new IllegalArgumentException("Number of distances should be one less than the number of courses.");
        }
    }

    //TODO: implement this method using DistanceData class
    private List<Double> getdistance(){
        return this.distances;
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

    }
}
