package Distance;

import entity.Course;
import entity.Session;
import API.GoogleMapsAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/** Higher-level interface for retrieving distance and travel time data between buildings.
 * @author Joshua Jang
 */
public class DistanceManager {
    /**
     * Retrieve the distance and travel time between all possible building combinations that exist in the given courses.
     *
     * @param courses A list of all course entities.
     * All sessions (lec, tut, pra) in such courses must have a buildingCode and Address.
     * @return A list of DistanceData instances containing origin and destination building codes,
     * distance, and travel time strings.
     */
    private static List<DistanceData> cache = new ArrayList<>();
    private static final int BATCH_SIZE = 10;

    public static DistanceData getDistanceData(String originCode, String destinationCode) {
        if (cache.isEmpty()) { throw new RuntimeException("No distance cache generated. Run updateDistances() first."); }
        else {
            for (DistanceData dataInstance : cache) {
                if ((dataInstance.getOrigin().equals(originCode) && dataInstance.getDestination().equals(destinationCode))
                        ||
                        (dataInstance.getOrigin().equals(destinationCode) && dataInstance.getDestination().equals(originCode))) {
                    return dataInstance;
                }
            }
        }
        return null;
    }

    public static void updateDistances(List<Course> courses) {
        cache.clear();

        ArrayList<String> allBuildingCodes = new ArrayList<>();
        ArrayList<String> allAddresses = new ArrayList<>();

        for(Course course : courses) {
            ArrayList<Session> masterList = new ArrayList<>();
            // Concatenate all sessions
            Stream.of(course.getLecSessions(), course.getTutSessions(), course.getPraSessions()).forEach(masterList::addAll);

            for(Session session : masterList) {
                List<String> buildingCodes = session.getBuildingCode();
                List<String> addresses = session.getAddress();

                // We assume buildingCodes and addresses have the same size, and are in the same order
                for(int i = 0; i < buildingCodes.size(); i++) {
                    if(!allBuildingCodes.contains(buildingCodes.get(i))) {
                        allBuildingCodes.add(buildingCodes.get(i));
                        allAddresses.add(addresses.get(i));
                    }
                }
            }
        }

        ArrayList<ArrayList<String>> apiResults = new ArrayList<>();

        for (int i = 0; i < allAddresses.size(); i += BATCH_SIZE) {
            List<String> sublist1 = allAddresses.subList(i, Math.min(i + BATCH_SIZE, allAddresses.size()));
            for (int j = 0; j < allAddresses.size(); j += BATCH_SIZE) {
                List<String> sublist2 = allAddresses.subList(j, Math.min(j + BATCH_SIZE, allAddresses.size()));

                apiResults.addAll(GoogleMapsAPI.get(sublist1, sublist2, "walking"));
            }
        }

        int length = allBuildingCodes.size();

        for (int i = 0; i < length; i++) {
            for (int j = length - 1; j >= i; j--) {
                String originCode = allBuildingCodes.get(i);
                String destinationCode = allBuildingCodes.get(j);
                String distance = apiResults.get(i * length + j).get(0);
                String duration = apiResults.get(i * length + j).get(1);

                DistanceData data = new DistanceData(originCode, destinationCode, distance, duration);
                cache.add(data);
            }
        }
    }
}
