package Distance;

public class DistanceData {
    private String originCode;
    private String destinationCode;

    private String distance; // in meters
    private String duration; // in minutes

    public DistanceData(String originCode, String destinationCode, String distance, String duration) {
        this.originCode = originCode;
        this.destinationCode = destinationCode;
        this.distance = distance;
        this.duration = duration;
    }

    public String toString() {
        return String.format("%s to %s across %s in %s", originCode, destinationCode, distance, duration);
    }
}
