package Distance;

/** High-level data structure for storing distance and travel time info between two buildings.
 * Buildings are represented by their unique building code.
 * @author Joshua Jang
 */
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
