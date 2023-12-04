package entity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TimeTableTest {

    public TimeTable table;

    @Before
    public void init() {
        this.table = new TimeTable(List.of(),List.of(List.of("","","AA",""),List.of("","","AA","")),
                List.of(List.of("","","AA",""),List.of("","","AA","")),
                List.of(List.of("","","AA",""),List.of("","","AA","")),
                List.of(List.of("","","AA",""),List.of("","","AA","")),
                List.of(List.of("","","AA",""),List.of("","","AA","")),1.0);
    }


    @Test
    public void testToString() {
        System.out.println(table);
    }

    @Test
    public void getBuildingCode1() {
        System.out.println(table.getBuildingCode1());
    }

    @Test
    public void getBuildingCode2() {
        System.out.println(table.getBuildingCode2());
    }

    @Test
    public void getBuildingCode3() {
        System.out.println(table.getBuildingCode3());
    }

    @Test
    public void getBuildingCode4() {
        System.out.println(table.getBuildingCode4());
    }

    @Test
    public void getBuildingCode5() {
        System.out.println(table.getBuildingCode5());
    }

    @Test
    public void getAddress1() {
        System.out.println(table.getAddress1());
    }

    @Test
    public void getAddress2() {
        System.out.println(table.getAddress2());
    }

    @Test
    public void getAddress3() {
        System.out.println(table.getAddress3());
    }

    @Test
    public void getAddress4() {
        System.out.println(table.getAddress4());
    }

    @Test
    public void getAddress5() {
        System.out.println(table.getAddress5());
    }

    @Test
    public void getDistance() {
        System.out.println(table.getDistance());
    }

    @Test
    public void getSessions() {
        System.out.println(table.getSessions());
    }
}