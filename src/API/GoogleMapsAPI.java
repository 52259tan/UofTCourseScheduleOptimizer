package org.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class GoogleMapsAPI {
    private ArrayList<String> origins;
    private ArrayList<String> destinations;
    private String mode;

    public GoogleMapsAPI(){
        origins = new ArrayList<String>();
        destinations = new ArrayList<String>();
        setMode(0);
    }

    public void addOrigin(String address) {
        origins.add(address);
    }

    public void addDestination(String address) {
        destinations.add(address);
    }

    public void clearOriginsDestinations() {
        origins.clear();
        destinations.clear();
    }

    public void setMode(int id) {
        switch (id) {
            case 0: // Walking
                mode = "walking";
                break;
            case 1: // Transit
                mode = "transit";
                break;
        }
    }

    private String encodeLocations(ArrayList<String> locations) {
        StringBuilder formattedString = new StringBuilder();

        for (int i = 0; i < locations.size(); i++) {
            formattedString.append(locations.get(i));
            if (i < locations.size() - 1) formattedString.append(" | ");
        }

        return formattedString.toString();
    }

    public void GetDistanceMatrix() {
        final String API_URL =
                "https://maps.googleapis.com/maps/api/distancematrix/json?origins=%s&destinations=%s&mode=%s&units=metric&key=%s";
        final String API_KEY = "AIzaSyA8W-ukR4bacqWfzy4kT0MSn52V-Y_kk0E";

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format(API_URL, encodeLocations(origins), encodeLocations(destinations), mode, API_KEY))
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());
            System.out.println(responseBody);

            JSONArray resultDestinations =
                    responseBody.getJSONArray("destination_addresses");
            JSONArray resultOrigins =
                    responseBody.getJSONArray("origin_addresses");

            JSONArray rows = responseBody.getJSONArray("rows");

            for (int i = 0; i < rows.length(); i++) {
                JSONObject elements = rows.getJSONObject(i);
                JSONArray elementsArray = elements.getJSONArray("elements");

                for (int j = 0; j < elementsArray.length(); j++) {
                    JSONObject element = elementsArray.getJSONObject(j);
                    JSONObject duration = element.getJSONObject("duration");
                    System.out.println(duration);
                    String durationTime = duration.getString("text");
                    System.out.printf("From %s to %s:\nTravel time (%s): %s%n",
                            resultOrigins.getString(i), resultDestinations.getString(j), mode, durationTime);
                }
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}