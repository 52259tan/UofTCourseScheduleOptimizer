package entity;

import java.util.ArrayList;
import java.util.List;
/**
 * An entity.TimeTable entity that contains a list of sessions of a final schedule, list of addresses based
 * on the shedule for each week day in a chronological order, total walking distance of the schedule
 * (five weekdays' distances add together)
 * @author pinglu
 */
public class TimeTable {
    private final String timeTableName;
    private List<Session> listSession = new ArrayList<>();
    private List<String> address1 = new ArrayList<>();
    private List<String> address2 = new ArrayList<>();
    private List<String> address3 = new ArrayList<>();
    private List<String> address4 = new ArrayList<>();
    private List<String> address5 = new ArrayList<>();
    private Double distance;

    public TimeTable(String timeTableName, List<List<Object>> day1, List<List<Object>> day2, List<List<Object>> day3, List<List<Object>>
            day4, List<List<Object>> day5, Double distance){
        this.distance = distance;
        this.timeTableName = timeTableName;
        this.address1 = getAddress(day1);
        this.address2 = getAddress(day2);
        this.address3 = getAddress(day3);
        this.address4 = getAddress(day4);
        this.address5 = getAddress(day5);

    }

    private List<String> getAddress(List<List<Object>> day) { //day: [[7,9,"location1"], [11, 12, "location2"], [14,16, "location3"]]
        final String postfix = ", Toronto, Ontario, Canada";
        List<String> address = new ArrayList<>();
        for (List<Object> info: day){
            address.add(info.get(2) + postfix);
        }
        return address; //["address1", "address2", "address3"]
    }
}

