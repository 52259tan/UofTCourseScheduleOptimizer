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
    private List<String> BuildingCode1 = new ArrayList<>();
    private List<String> BuildingCode2 = new ArrayList<>();
    private List<String> BuildingCode3 = new ArrayList<>();
    private List<String> BuildingCode4 = new ArrayList<>();
    private List<String> BuildingCode5 = new ArrayList<>();
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
        this.BuildingCode1 = getBuildingCode(day1);
        this.BuildingCode2 = getBuildingCode(day2);
        this.BuildingCode3 = getBuildingCode(day3);
        this.BuildingCode4 = getBuildingCode(day4);
        this.BuildingCode5 = getBuildingCode(day5);
        this.address1 = getAddress(day1);
        this.address2 = getAddress(day2);
        this.address3 = getAddress(day3);
        this.address4 = getAddress(day4);
        this.address5 = getAddress(day5);
    }

    public String toString() {
        //make new lines for each session
        StringBuilder result = new StringBuilder();

        for (Session obj : sessions) {
            result.append(obj.toString()).append(System.lineSeparator());
        }
        String session = result.toString();

        //make new lines for each address
        StringBuilder resultBuilder1 = new StringBuilder();
        for (String str : BuildingCode1) {
            resultBuilder1.append(str).append(System.lineSeparator());
        }
        // Convert StringBuilder to String
        String address1 = resultBuilder1.toString();

        StringBuilder resultBuilder2 = new StringBuilder();
        for (String str : BuildingCode2) {
            resultBuilder2.append(str).append(System.lineSeparator());
        }
        // Convert StringBuilder to String
        String address2 = resultBuilder2.toString();

        StringBuilder resultBuilder3 = new StringBuilder();
        for (String str : BuildingCode3) {
            resultBuilder3.append(str).append(System.lineSeparator());
        }
        // Convert StringBuilder to String
        String address3 = resultBuilder3.toString();

        StringBuilder resultBuilder4 = new StringBuilder();
        for (String str : BuildingCode4) {
            resultBuilder4.append(str).append(System.lineSeparator());
        }
        // Convert StringBuilder to String
        String address4 = resultBuilder4.toString();

        StringBuilder resultBuilder5 = new StringBuilder();
        for (String str : BuildingCode5) {
            resultBuilder5.append(str).append(System.lineSeparator());
        }
        // Convert StringBuilder to String
        String address5 = resultBuilder5.toString();

        return String.format("""
                optimal timetable with minimal distance %s, optimal sessions are\s
                %s,\s
                 the building code on Monday is
                %s
                 the building code on Tuesday is
                %s
                the building code on Wednesday is\s
                %s
                the building code on Thursday is\s
                %s
                the building code on Friday is\s
                %s\s""", distance, session, address1,address2, address3, address4, address5);
    }

    private List<String> getBuildingCode(List<List<Object>> day) { //day: [[7,9,"location1"], [11, 12, "location2"], [14,16, "location3"]]
        if (day.get(0).isEmpty()){
            return new ArrayList<>();
        }
        List<String> buildingCode = new ArrayList<>();
        for (List<Object> info : day) {
            buildingCode.add((String) info.get(2));
        }
        return buildingCode;
    }

    public List<String> getBuildingCode1(){
        return this.BuildingCode1;
    }
    public List<String> getBuildingCode2(){
        return this.BuildingCode2;
    }
    public List<String> getBuildingCode3(){
        return this.BuildingCode3;
    }
    public List<String> getBuildingCode4(){
        return this.BuildingCode4;
    }
    public List<String> getBuildingCode5(){
        return this.BuildingCode5;
    }

    private List<String> getAddress(List<List<Object>> day) { //day: [[7,9,"location1"], [11, 12, "location2"], [14,16, "location3"]]
        if (day.get(0).isEmpty()){
            return new ArrayList<>();
        }
        List<String> address = new ArrayList<>();
        for (List<Object> info : day) {
            address.add(info.get(3).toString());
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

