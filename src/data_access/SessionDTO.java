package data_access;

import java.util.List;

public class SessionDTO {
    /**
     * This is a simple data type representation of Session entities to be passed through Output Data object
     */
    private String sessionCode;
    private List<Integer> startTime;
    private List<Integer> endTime;
    private List<Integer> day;
    private List<String> address;
    private List<String> buildingCode;

    public SessionDTO(String sessionCode, List<Integer> startTime, List<Integer> endTime,
                      List<Integer> day, List<String> address, List<String> buildingCode) {
        this.sessionCode = sessionCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
        this.address = address;
        this.buildingCode = buildingCode;
    }

    // Getters and setters for all fields
    public String getSessionCode() {
        return sessionCode;
    }

    public void setSessionCode(String sessionCode) {
        this.sessionCode = sessionCode;
    }

    public List<Integer> getStartTime() {
        return startTime;
    }

    public void setStartTime(List<Integer> startTime) {
        this.startTime = startTime;
    }

    public List<Integer> getEndTime() {
        return endTime;
    }

    public void setEndTime(List<Integer> endTime) {
        this.endTime = endTime;
    }

    public List<Integer> getDay() {
        return day;
    }

    public void setDay(List<Integer> day) {
        this.day = day;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    public List<String> getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(List<String> buildingCode) {
        this.buildingCode = buildingCode;
    }
}

