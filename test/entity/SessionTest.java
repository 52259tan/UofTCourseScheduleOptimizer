package entity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class SessionTest {
    public Session session;

    @Before
    public void init() {
        HashMap<String,Object> map = new HashMap<>();
        map.put("Start",0);
        map.put("endtime",0);
        map.put("Day",0);
        map.put("building","BA");
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        list.add(map);
        this.session = new Session(list);
    }

    @Test
    public void getBuildingCode() {
        System.out.println(session.getBuildingCode());
    }

    @Test
    public void getAddress() {
        System.out.println(session.getAddress());
    }

    @Test
    public void setSessionCode() {
        session.setSessionCode("abc");;
    }

    @Test
    public void testToString() {
        System.out.println(session);
    }

    @Test
    public void getStartTime() {
        System.out.println(session.getStartTime());
    }

    @Test
    public void getEndTime() {
        System.out.println(session.getEndTime());
    }

    @Test
    public void getDay() {
        System.out.println(session.getDay());
    }

    @Test
    public void getSessionCode() {
        System.out.println(session.getSessionCode());
    }
}