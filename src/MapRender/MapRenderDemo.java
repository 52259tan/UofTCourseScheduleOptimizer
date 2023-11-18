package MapRender;

import API.MapRenderAPI;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** Demo of rendering the route that passes through three waypoints, and saving the result to disk.
 * @author Joshua Jang
 */
public class MapRenderDemo {
    public static void main(String[] args) {
        List<String> waypoints = new ArrayList<String>();
        waypoints.add("Bloor-Yonge Station, Toronto");
        waypoints.add("Bahen Centre, Toronto");
        waypoints.add("Dundas Station, Toronto");

        String polyline = MapRenderAPI.getPolyline(waypoints, "walking");
        BufferedImage image = MapRenderAPI.generateMapWithPolyline(polyline);

        saveImageToFile(image, "map_with_polyline.png");
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
