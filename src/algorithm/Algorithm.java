package algorithm;

import entity.Timetable;
import entity.Schedule;

import java.util.List;

// This class randomly generate at most 1000 different schedules (Timetable class), and get the shortest distance one
// assume "walking" for now
public class Algorithm {
    //TODO: the schedule should be user input
    private static Schedule schedule;
    private List<Double> distances;
    private List<Timetable> instances;


    public Timetable[] main(String[] args) {
        // Create 100 instances of Timetable
        Timetable[] instances = new Timetable[100];
        this.distances = this.getListOfDistance();

        for (int i = 0; i < instances.length; i++) {
            instances[i] = new Timetable(schedule);
            this.instances = List.of(instances);
        }
        return instances;
    }

    // create a list of total distance of each Timetable
    private List<Double> getListOfDistance() {
        for (Timetable Timetable : this.instances) {
            this.distances.add(Timetable.getTotalDistance());
        }
        return this.distances;
    }

    private Timetable getLeastDistanceTimetable() {
        int minValue = Integer.MAX_VALUE;

        for (Double number : this.distances) {
            minValue = (int) Math.min(minValue, number);
        }
        int index = this.distances.indexOf(minValue);

        return instances.get(index);

        }
    }
