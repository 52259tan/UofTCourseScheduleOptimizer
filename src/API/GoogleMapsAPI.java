package org.example;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Objects;

public class GoogleMapsAPI
    {
    public static void main(String[] args)
        {
        String origin = "Bahen Centre";
        String destination = "Bloor-Yonge Station";
        String mode = "walking";

        final String API_URL =
                "https://maps.googleapis.com/maps/api/distancematrix/json?origins=%s&destinations=%s&mode=%s&units=metric&key=%s";
        final String API_KEY = "KEY HERE";

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format(API_URL, origin, destination, mode, API_KEY))
                .build();
        try
            {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());
            System.out.println(responseBody);

            if (Objects.equals(responseBody.getString("status"), "OK"))
                {
                // The JSON has a lot of layers to unpack..!
                String resultDestination =
                        responseBody.getJSONArray("destination_addresses").getString(0);
                String resultOrigin =
                        responseBody.getJSONArray("origin_addresses").getString(0);

                JSONArray rows = responseBody.getJSONArray("rows");
                JSONObject elements = rows.getJSONObject(0);
                JSONArray elementsArray = elements.getJSONArray("elements");
                JSONObject firstElement = elementsArray.getJSONObject(0);
                JSONObject duration = firstElement.getJSONObject("duration");
                System.out.println(duration);
                String durationTime = duration.getString("text");

                System.out.printf("From %s to %s:\nTravel time (%s): %s%n",
                        resultOrigin, resultDestination, mode, durationTime);
                }
            else
                {
                throw new RuntimeException();
                }
            }
        catch (IOException | JSONException e)
            {
            throw new RuntimeException(e);
            }
        }
    }