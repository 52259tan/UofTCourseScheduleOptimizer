package MapRender;

import entity.TimeTable;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/** Demo of rendering the route that passes through three waypoints, and saving the result to disk.
 * @author Joshua Jang
 */
public class MapRenderDemo {
    public static void main(String[] args) {
        List<String> waypoints = new ArrayList<>();
        waypoints.add("Bloor-Yonge Station, Toronto");
        waypoints.add("Bahen Centre, Toronto");
        waypoints.add("Dundas Station, Toronto");

        TimeTable timeTable = new TimeTable();
        MapRenderManager.generateMapsPNG(timeTable);
    }



}
