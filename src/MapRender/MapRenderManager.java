package MapRender;

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
    public static List<BufferedImage> generateMapsBuffer(TimeTable timeTable) {
        BufferedImage monImg = generateMapImage(timeTable.getAddress1());
        BufferedImage tueImg = generateMapImage(timeTable.getAddress2());
        BufferedImage wedImg = generateMapImage(timeTable.getAddress3());
        BufferedImage thuImg = generateMapImage(timeTable.getAddress4());
        BufferedImage friImg = generateMapImage(timeTable.getAddress5());

        return new ArrayList<>(Arrays.asList(monImg, tueImg, wedImg, thuImg, friImg));
    }
    public static void generateMapsPNG(TimeTable timeTable) {
        List<BufferedImage> bufferedImages = generateMapsBuffer(timeTable);
        for(int i = 0; i < 5; i++) {
            saveImageToFile(bufferedImages.get(i), "/mapimgs/map_" + days.get(i) + ".png");
        }
    }

    private static BufferedImage generateMapImage(List<String> addresses) {
        if (!addresses.isEmpty()) {
            String polyline = MapRenderAPI.getPolyline(addresses, "walking");
            BufferedImage image = MapRenderAPI.generateMapWithPolyline(polyline, addresses);

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
