package API;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.List;

/** Lowest-level interface for communicating with the Google Maps Distance Matrix API.
 * @author Joshua Jang
 */
public class DistanceMatrixAPI {
    private static String encodeLocations(List<String> locations) {
        StringBuilder formattedString = new StringBuilder();

        for (int i = 0; i < locations.size(); i++) {
            formattedString.append(locations.get(i));
            if (i < locations.size() - 1) formattedString.append(" | ");
        }

        return formattedString.toString();
    }

    /** Call the API with multiple origins and destinations, at once.
     * @param origins A list of all origin addresses to query the API.
     *                Must be specific enough for Google to accurately search.
     * @param destinations Same as origins, but for destinations.
     * @param mode One of three strings: "walking", "transit", or "biking", which determines the mode of transport for all.
     *             Note that all modes may include a portion of walking.
     * @return A list containing all possible route combinations between each origin and destination.
     *         The sublist contains the distance string (in m or km) at index 0 and travel time (in mins or hours) at index 1.
     */
    public static List<List<String>> get(List<String> origins, List<String> destinations, String mode) {
        final String API_URL =
                "https://maps.googleapis.com/maps/api/distancematrix/json?origins=%s&destinations=%s&mode=%s&units=metric&key=%s";

        List<List<String>> results = new ArrayList<>();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format(API_URL, encodeLocations(origins), encodeLocations(destinations), mode, APIKeys.GOOGLE_MAPS_KEY))
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());
            System.out.println(responseBody);

            JSONArray rows = responseBody.getJSONArray("rows");

            for (int i = 0; i < rows.length(); i++) {
                JSONObject elements = rows.getJSONObject(i);
                JSONArray elementsArray = elements.getJSONArray("elements");

                for (int j = 0; j < elementsArray.length(); j++) {
                    List<String> resultPair = new ArrayList<>();
                    JSONObject element = elementsArray.getJSONObject(j);
                    JSONObject distance = element.getJSONObject("distance");
                    String distanceValue = distance.getString("text");
                    JSONObject duration = element.getJSONObject("duration");
                    String durationValue = duration.getString("text");

                    resultPair.add(distanceValue);
                    resultPair.add(durationValue);

                    results.add(resultPair);
                }
            }
            return results;
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}