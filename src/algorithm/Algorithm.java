package algorithm;

import entity.Map;
import entity.Schedule;

import java.util.List;

// This class randomly generate at most 1000 different schedules (Map class), and get the shortest distance one
// assume "walking" for now
public class Algorithm {
    //TODO: the schedule should be user input
    private static Schedule schedule;
    private List<Double> distances;
    private List<Map> instances;


    public Map[] main(String[] args) {
        // Create 100 instances of Map
        Map[] instances = new Map[100];
        this.distances = this.getListOfDistance();

        for (int i = 0; i < instances.length; i++) {
            instances[i] = new Map(schedule);
            this.instances = List.of(instances);
        }
        return instances;
    }

    // create a list of total distance of each map
    private List<Double> getListOfDistance() {
        for (Map map : this.instances) {
            this.distances.add(map.getTotalDistance());
        }
        return this.distances;
    }

    private Map getLeastDistanceMap() {
        int minValue = Integer.MAX_VALUE;

        for (Double number : this.distances) {
            minValue = (int) Math.min(minValue, number);
        }
        int index = this.distances.indexOf(minValue);

        return instances.get(index);

        }
    }
