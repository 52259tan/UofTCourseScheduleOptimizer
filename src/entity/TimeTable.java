package entity;

import java.util.ArrayList;
import java.util.List;
/**
 * An entity.TimeTable entity that contains a list of sessions of a final schedule, list of addresses based
 * on the schedule for each week day in a chronological order, total walking distance of the schedule
 * (five weekdays' distances add together)
 * @author pinglu
 */
public class TimeTable {
    private final List<Session> sessions;
    private List<String> address1 = new ArrayList<>();
    private List<String> address2 = new ArrayList<>();
    private List<String> address3 = new ArrayList<>();
    private List<String> address4 = new ArrayList<>();
    private List<String> address5 = new ArrayList<>();
    private final Double distance;

    public TimeTable(List<Session> listSession, List<List<Object>> day1, List<List<Object>> day2, List<List<Object>> day3, List<List<Object>>
            day4, List<List<Object>> day5, Double distance) {
        this.sessions = listSession;
        this.distance = distance;
        this.address1 = getAddress(day1);
        this.address2 = getAddress(day2);
        this.address3 = getAddress(day3);
        this.address4 = getAddress(day4);
        this.address5 = getAddress(day5);

    }

    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Session obj : sessions) {
            result.append(obj.toString()).append(System.lineSeparator());
        }
        String session = result.toString();
        return String.format("optimal timetable with minimal distance %s, optimal sessions are \n%s ", distance, session);
    }

    private List<String> getAddress(List<List<Object>> day) { //day: [[7,9,"location1"], [11, 12, "location2"], [14,16, "location3"]]
        final String postfix = ", Toronto, Ontario, Canada";
        if (day.get(0).isEmpty()){
            return new ArrayList<>();
        }
        List<String> address = new ArrayList<>();
        for (List<Object> info : day) {
            address.add(info.get(2) + postfix);
        }
        return address; //["address1", "address2", "address3"]
    }

    public List<String> getAddress1(){
        return this.address1;
    }
    public List<String> getAddress2(){
        return this.address2;
    }
    public List<String> getAddress3(){
        return this.address3;
    }
    public List<String> getAddress4(){
        return this.address4;
    }
    public List<String> getAddress5(){
        return this.address5;
    }
    public Double getDistance(){
        return this.distance;
    }
    public List<Session> getSessions(){
        return this.sessions;
    }
}

