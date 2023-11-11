package Distance;

import Course.Course;
import Course.Session;
import API.GoogleMapsAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DistanceManager {
    public static ArrayList<DistanceData> getDistances(ArrayList<Course> courses) {
        ArrayList<DistanceData> results = new ArrayList<>();

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

        ArrayList<ArrayList<String>> apiResults = GoogleMapsAPI.get(allAddresses, allAddresses, "walking");

        int length = allBuildingCodes.size();

        for (int i = 0; i < length; i++) {
            for (int j = length - 1; j >= i; j--) {
                String originCode = allBuildingCodes.get(i);
                String destinationCode = allBuildingCodes.get(j);
                String distance = apiResults.get(i * length + j).get(0);
                String duration = apiResults.get(i * length + j).get(1);

                DistanceData data = new DistanceData(originCode, destinationCode, distance, duration);
                results.add(data);
            }
        }
        return results;
    }
}
