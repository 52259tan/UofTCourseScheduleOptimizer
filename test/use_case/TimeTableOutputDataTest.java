package use_case;

import data_access.SessionDTO;
import entity.TimeTable;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * @author bayanmehr
 */
public class TimeTableOutputDataTest {

    public TimeTableOutputData table;

    public SessionDTO data;

    @Before
    public void init() {
        this.data = new SessionDTO("123",List.of(),List.of(),List.of(),List.of(),List.of());
        this.table = new TimeTableOutputData(List.of(data));
    }

    @Test
    public void testGetter() {
        assertEquals(table.getSessions().get(0), data);
    }

}
