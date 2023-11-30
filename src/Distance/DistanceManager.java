package Distance;

import API.DistanceMatrixAPI;
import entity.Course;
import entity.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/** Higher-level interface for retrieving distance and travel time data between buildings.
 * @author Joshua Jang
 */
public class DistanceManager {
    private static List<DistanceData> cache = new ArrayList<>();
    private static final int BATCH_SIZE = 10;

    /**
     * Assuming updateDistances() has been called, return the distance/duration info between two buildings
     * as a DistanceData object.
     *
     * @param originCode Origin building code. Interchangable with destinationCode.
     * @param destinationCode Destination building code. Interchangable with originCode.
     * @return A corresponding DistanceData object with getDistanceFloat() and getDuration() getter methods.
     */
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

    /**
     * Call the DistanceMatrix API and generate a cache consisting of all possible distance combinations.
     * Every time a new list of courses are to be worked with, this method must be called for getDistanceData() to work.
     *
     * @param courses A list of all course entities.
     * All sessions (lec, tut, pra) in such courses must have a buildingCode and Address.
     * A list of DistanceData instances containing origin and destination building codes,
     * distance, and travel time strings will be genrated and stored.
     */
    public static void updateDistances(List<Course> courses) {
        cache.clear();

        List<String> allBuildingCodes = new ArrayList<>();
        List<String> allAddresses = new ArrayList<>();

        for(Course course : courses) {
            List<Session> masterList = new ArrayList<>();
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

        List<List<String>> apiResults = new ArrayList<>();

        for (int i = 0; i < allAddresses.size(); i += BATCH_SIZE) {
            List<String> sublist1 = allAddresses.subList(i, Math.min(i + BATCH_SIZE, allAddresses.size()));
            for (int j = 0; j < allAddresses.size(); j += BATCH_SIZE) {
                List<String> sublist2 = allAddresses.subList(j, Math.min(j + BATCH_SIZE, allAddresses.size()));

                apiResults.addAll(DistanceMatrixAPI.get(sublist1, sublist2, "walking"));
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
