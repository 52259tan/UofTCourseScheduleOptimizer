package entity;

import java.io.*;
import java.util.*;

/**
 * A entity.Session entity that contains the name of the session (LEC0101) and Time and Location.
 */
public class Session {
    private String sessionCode = new String();
    private List<Integer> startTime = new ArrayList<>();
    private List<Integer> endTime = new ArrayList<>();
    private List<Integer> day = new ArrayList<>();
    private List<String> address = new ArrayList<>();
    private List<String> buildingCode = new ArrayList<>();

    /**
     *
     */
    public Session(ArrayList<HashMap<String,Object>> session ) {
        for (HashMap m: session){
            this.startTime.add((Integer) m.get("Start")/3600000);
            this.endTime.add((Integer)m.get("endtime")/3600000);
            this.day.add((Integer) m.get("Day"));
            this.buildingCode.add((String) m.get("building"));
            this.address.add(buildingCodeToAddress((String) m.get("building")));
        }

    }
    public List<String> getBuildingCode(){
        return this.buildingCode;
    }
    public List<String> getAddress(){
        return this.address;
    }
    public void setSessionCode(String sessionCode){
        this.sessionCode = sessionCode;
    }
    private String buildingCodeToAddress(String buildingCode){
        String fileName = "./src/codetoaddress.txt"; // Replace with your file path
        try {
            File myObj = new File(fileName);
            Scanner reader = new Scanner(myObj);
            String[] parts = new String[0];
            while (reader.hasNextLine()) {
                parts = reader.nextLine().split(":");
                if (parts[0].equals(buildingCode)) {
                    reader.close();
                    return parts[1];
                }
            }
            reader.close();
            throw new RuntimeException();
        } catch (FileNotFoundException z) {
            throw new RuntimeException(z);
        }
    }

    @Override
    public String toString() {
        return (sessionCode);
    }

    // Percy: add more setters and getters
    public List<Integer> getStartTime() { return startTime;
    }

    public List<Integer> getEndTime() { return endTime;
    }

    public List<Integer> getDay() { return day;
    }

    public String getSessionCode() { return sessionCode;
    }
}
