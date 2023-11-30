package MapRender;

import API.GoogleMapRenderAPI;
import API.MapRenderAPI;
import entity.TimeTable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/** Higher-level interface for rendering a list of classes into a viewable map image with routes.
 * @author Joshua Jang
 */
public class MapRenderManager {
    private static final List<String> days = new ArrayList<>(Arrays.asList("MON", "TUE", "WED", "THU", "FRI"));
    private static final MapRenderAPI mapRenderAPI = new GoogleMapRenderAPI();

    /**
     * Generate 5 Map images with waypoint markers and path/route line(s), as a list of BufferedImages.
     *
     * @param timeTable A timetable entity generated from the optimisation algorithm.
     * The timetable must be able to return a list of addresses by calling getAddressX().
     * @return A list of BufferedImage objects, which can be used to directly display images on screen,
     * without having to save to disk first. If saving to disk is the goal, use generateMapsPNG().
     */
    public static List<BufferedImage> generateMapsBuffer(TimeTable timeTable) {
        BufferedImage monImg = generateMapImage(timeTable.getAddress1());
        BufferedImage tueImg = generateMapImage(timeTable.getAddress2());
        BufferedImage wedImg = generateMapImage(timeTable.getAddress3());
        BufferedImage thuImg = generateMapImage(timeTable.getAddress4());
        BufferedImage friImg = generateMapImage(timeTable.getAddress5());

        return new ArrayList<>(Arrays.asList(monImg, tueImg, wedImg, thuImg, friImg));
    }

    /**
     * Generate 5 Map images with waypoint markers and path/route line(s), and save them to disk.
     *
     * @param timeTable A timetable entity generated from the optimisation algorithm.
     * The timetable must be able to return a list of addresses by calling getAddressX().
     * @param subdir The directory/folder inside the project root in which the images are to be saved.
     */
    public static void generateMapsPNG(TimeTable timeTable, String subdir) {
        List<BufferedImage> bufferedImages = generateMapsBuffer(timeTable);
        for(int i = 0; i < 5; i++) {
            saveImageToFile(bufferedImages.get(i),  subdir + "/map_" + days.get(i) + ".png");
        }
    }

    private static BufferedImage generateMapImage(List<String> addresses) {
        if (!addresses.isEmpty()) {
            String polyline = mapRenderAPI.getPolyline(addresses, "walking");
            BufferedImage image = mapRenderAPI.generateMapWithPolyline(polyline, addresses);

            return image;
        }
        return new BufferedImage(800, 640, BufferedImage.TYPE_INT_ARGB);
    }

    private static void saveImageToFile(BufferedImage image, String fileName) {
        if (image != null) {
            try {
                String projectPath = System.getProperty("user.dir");
                File outputFile = new File(projectPath + File.separator + fileName);
                ImageIO.write(image, "png", outputFile);
                System.out.println("Image saved to: " + outputFile.getAbsolutePath());
            } catch (IOException e) {
                throw new RuntimeException("Image cannot be saved");
            }
        } else {
            System.out.println("No image to save.");
        }
    }
}
