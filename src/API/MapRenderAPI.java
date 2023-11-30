package API;

import java.awt.image.BufferedImage;
import java.util.List;

/** Lowest-level interface for communicating with a Maps Directions API and Maps Static API.
 * @author Joshua Jang
 */
public interface MapRenderAPI {
    public BufferedImage generateMapWithPolyline(String encodedPolyline, List<String> markerAddresses);
    public String getPolyline(List<String> waypoints, String mode);
}
