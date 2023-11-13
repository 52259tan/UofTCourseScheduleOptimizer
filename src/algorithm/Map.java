import java.util.ArrayList;
import java.util.List;

public class Map {
    private List<String> courses;
    private List<Double> distances;
    private double totalDistance;

    public Map(List<String> courses, List<Double> distances) {
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

    public List<String> getCourses() {
        return courses;
    }

    public List<Double> getDistances() {
        return distances;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public static void main(String[] args) {
        List<String> courses = new ArrayList<>();
        courses.add("Course A");
        courses.add("Course B");
        courses.add("Course C");

        List<Double> distances = new ArrayList<>();
        distances.add(10.0);
        distances.add(15.0);

        Map myMap = new Map(courses, distances);

        System.out.println("Courses: " + myMap.getCourses());
        System.out.println("Distances: " + myMap.getDistances());
        System.out.println("Total Distance: " + myMap.getTotalDistance());
    }
}
