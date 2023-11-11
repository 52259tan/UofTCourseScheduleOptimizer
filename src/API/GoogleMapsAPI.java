package API;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class GoogleMapsAPI {

    private static String encodeLocations(ArrayList<String> locations) {
        StringBuilder formattedString = new StringBuilder();

        for (int i = 0; i < locations.size(); i++) {
            formattedString.append(locations.get(i));
            if (i < locations.size() - 1) formattedString.append(" | ");
        }

        return formattedString.toString();
    }

    public static ArrayList<ArrayList<String>> get(ArrayList<String> origins, ArrayList<String> destinations, String mode) {
        final String API_URL =
                "https://maps.googleapis.com/maps/api/distancematrix/json?origins=%s&destinations=%s&mode=%s&units=metric&key=%s";
        final String API_KEY = "AIzaSyA8W-ukR4bacqWfzy4kT0MSn52V-Y_kk0E";

        ArrayList<ArrayList<String>> results = new ArrayList<>();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format(API_URL, encodeLocations(origins), encodeLocations(destinations), mode, API_KEY))
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());
            //System.out.println(responseBody);

            JSONArray resultDestinations =
                    responseBody.getJSONArray("destination_addresses");
            JSONArray resultOrigins =
                    responseBody.getJSONArray("origin_addresses");

            JSONArray rows = responseBody.getJSONArray("rows");

            for (int i = 0; i < rows.length(); i++) {
                JSONObject elements = rows.getJSONObject(i);
                JSONArray elementsArray = elements.getJSONArray("elements");


                for (int j = 0; j < elementsArray.length(); j++) {
                    ArrayList<String> resultPair = new ArrayList<>();
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