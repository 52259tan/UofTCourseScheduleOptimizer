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

    public String getOrigin() { return originCode; }
    public String getDestination() { return destinationCode; }
    public double getDistanceFloat()
        {
        return convertToMeters(distance);
        }
    public String getDuration() { return duration; }

    public String toString() {
        return String.format("%s to %s across %s in %s", originCode, destinationCode, distance, duration);
    }

    private static double convertToMeters(String distance) {
        double result = 0.0;
        String[] parts = distance.split(" ");

        if (parts.length == 2) {
            double value = Double.parseDouble(parts[0]);
            String unit = parts[1].trim();

            if (unit.equalsIgnoreCase("m")) {
                result = value;
            } else if (unit.equalsIgnoreCase("km")) {
                result = value * 1000;
            }
        }
        return result;
    }
}
