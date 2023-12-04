package data_access;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SessionDTOTest {

    public SessionDTO sessionDTO;

    @Before
    public void init() {
        this.sessionDTO = new SessionDTO("A", List.of(0),List.of(0),List.of(0),List.of("A"),
                List.of("A"));
    }

    @Test
    public void getSessionCode() {
        System.out.println(sessionDTO.getSessionCode());
    }

    @Test
    public void setSessionCode() {
        sessionDTO.setSessionCode("B");
    }

    @Test
    public void getStartTime() {
        System.out.println(sessionDTO.getStartTime());
    }

    @Test
    public void setStartTime() {
        sessionDTO.setStartTime(List.of(1));
    }

    @Test
    public void getEndTime() {
        System.out.println(sessionDTO.getEndTime());
    }

    @Test
    public void setEndTime() {
        sessionDTO.setEndTime(List.of(1));
    }

    @Test
    public void getDay() {
        System.out.println(sessionDTO.getDay());
    }

    @Test
    public void setDay() {
        sessionDTO.setDay(List.of(1));
    }

    @Test
    public void getAddress() {
        System.out.println(sessionDTO.getAddress());
    }

    @Test
    public void setAddress() {
        sessionDTO.setAddress(List.of("B"));
    }

    @Test
    public void getBuildingCode() {
        System.out.println(sessionDTO.getBuildingCode());;
    }

    @Test
    public void setBuildingCode() {
        sessionDTO.setBuildingCode(List.of("B"));
    }
}