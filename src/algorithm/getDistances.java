package algorithm;


import Distance.DistanceManager;

import java.util.ArrayList;
import java.util.List;
/**
 * This class input a list of classes a student attend in one day in chronological order, and help them find the
 * total distances walking from one class to another based on the one-day-schedule
 * @author pinglu
 */
public class getDistances {
    /**
     * This method input a list of classes a student attend in one day in chronological order, and help them find the
     * total distances walking from one class to another based on the one-day-schedule
     *
     * @param data the list of class information in chronological order.
     *             [[startTime（Integer）, endTime（Integer）, location（String）],[]]
     *             sample data： [[7,8,"location1"], [9,10,"location2"]]
     * @return A list of Session objects
     */
    public static Double getDistance(List<List<Object>> data) {
        Double distance = 0.0;
        if (data.get(0).isEmpty()){
            return distance;
        }

        //sample data1 [[7,8,"buildingCode1"], [9,10,"buildingCode2"]]
        List<String> buildingCode = new ArrayList<>();
        for (List<Object> classData : data) { //sample classData : [7,8,"buildingCode1"]
            buildingCode.add((String) classData.get(2));
        } //sample buildingCode : ["buildingCode1", "buildingCode2", "buildingCode3"]

        //get distance between each two address
        List<List<String>> buildingCodePair = getConsecutivePairs(buildingCode); // [["buildingCode1","buildingCode2"],["buildingCode2","buildingCode3"]]

        for (List<String> pair : buildingCodePair) { //["buildingCode1","buildingCode2"]
            distance += DistanceManager.getDistanceData(pair.get(0), pair.get(1)).getDistanceFloat();
        }
        return distance;
    }


    public static List<List<String>> getConsecutivePairs(List<String> inputList) {
        List<List<String>> pairs = new ArrayList<>();

        for (int i = 0; i < inputList.size() - 1; i++) {
            List<String> pair = List.of(inputList.get(i), inputList.get(i + 1));
            pairs.add(pair);
        }

        return pairs;
    }
}