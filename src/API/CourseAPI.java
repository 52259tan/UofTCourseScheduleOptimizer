package API;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CourseAPI {

    private static JSONObject getCourseInfoRaw(String course){
        /**
         precondition:
         String course must be a valid course code.
         Valid course code: "COURSECODE" + " -TERMCODE"
         Example usage:
         .getCourseInfoRaw("CSC207H1 -F") //COURSECODE = "CSC207H1", TERMCODE = " -F" for fall term
         returns a JSON object
         */
        //Parsing course +
        String[] courseArray = course.split("\\s*-\\s*");
        String coursecode = courseArray[0];
        String termcode = courseArray[1];

        // Parsing course -
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, String.format("{\"courseCodeAndTitleProps\":{\"courseCode\":\"%s\",\"courseTitle\":\"\",\"courseSectionCode\":\"%s\"},\"departmentProps\":[],\"campuses\":[],\"sessions\":[\"20239\",\"20241\",\"20239-20241\"],\"requirementProps\":[],\"instructor\":\"\",\"courseLevels\":[],\"deliveryModes\":[],\"dayPreferences\":[],\"timePreferences\":[],\"divisions\":[\"ARTSC\"],\"creditWeights\":[],\"page\":1,\"pageSize\":20,\"direction\":\"asc\"}\n", coursecode, termcode));
        Request request = new Request.Builder()
                .url("https://api.easi.utoronto.ca/ttb/getPageableCourses")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json, text/plain, */*")
                .addHeader("Accept-Encoding", "gzip, deflate, br")
                .addHeader("Accept-Language", "en-US,en;q=0.9,zh-TW;q=0.8,zh-CN;q=0.7,zh;q=0.6")
                .addHeader("Connection", "keep-alive")
                .addHeader("Origin", "https://ttb.utoronto.ca")
                .addHeader("Referer", "https://ttb.utoronto.ca/")
                .addHeader("Sec-Fetch-Dest", "empty")
                .addHeader("Sec-Fetch-Mode", "cors")
                .addHeader("Sec-Fetch-Site", "same-site")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36")
                .addHeader("sec-ch-ua", "^\\^Google")
                .addHeader("sec-ch-ua-mobile", "?0")
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());
            return responseBody;
        } catch (IOException|JSONException e){
            throw new RuntimeException();
        }
    }

    public static HashMap<String, HashMap<String, ArrayList<HashMap<String,Object>>>> getCourse(String course) throws IOException {
        /**
         * Returns a HashMap
         * Sample return
         * -> {CSC207H1 -F=
         *      {LEC5101=[{Start=64800000, endtime=72000000, Day=4, building=KP}],
         *      LEC0501=[{Start=57600000, endtime=61200000, Day=2, building=BA}, {Start=57600000, endtime=4, Day=4, building=BA}]}
         */

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://127.0.0.1:8000/"+course)
                .get()
                .addHeader("User-Agent", "insomnia/8.4.5")
                .build();
        Response response = client.newCall(request).execute();
        JSONObject responseBody = new JSONObject(response.body().string());
        JSONObject payload = responseBody.getJSONObject("payload");
        JSONObject pageableCourse = payload.getJSONObject("pageableCourse");
        JSONArray courses = pageableCourse.getJSONArray("courses");
        JSONObject fullCoursesInfo = (JSONObject) courses.get(0);
        JSONArray sections = fullCoursesInfo.getJSONArray("sections");


        HashMap<String, HashMap<String, ArrayList<HashMap<String, Object>>>> retCourseInfo = new HashMap<>();
        String code = fullCoursesInfo.get("code").toString();
        String term = fullCoursesInfo.get("sectionCode").toString();

        HashMap<String, ArrayList<HashMap<String, Object>>> lecTutSectionInfo = new HashMap<>();
        for (int i= 0; i< sections.length(); i++){
            JSONObject lectures = sections.getJSONObject(i);
            String lecturecode = lectures.getString("name");

            ArrayList<HashMap<String, Object>> sectionMeetingTimes = new ArrayList<>();
            JSONArray times =lectures.getJSONArray("meetingTimes");
            for (int k = 0; k< times.length(); k++){
                Integer day = (Integer) ((JSONObject) times.get(k)).getJSONObject("start").get("day");
                Integer starttime = (Integer) ((JSONObject) times.get(k)).getJSONObject("start").get("millisofday");
                Integer endtime = (Integer) ((JSONObject) times.get(k)).getJSONObject("end").get("millisofday");
                String building = ((JSONObject) times.get(k)).getJSONObject("building").getString("buildingCode");

                if (building.length() != 0){
                    HashMap<String, Object> weekday = new HashMap<>();
                    weekday.put("Day",day);
                    weekday.put("Start", starttime);
                    weekday.put("endtime", endtime);
                    weekday.put("building", building);
                    sectionMeetingTimes.add(weekday);}
            }
            if (sectionMeetingTimes.size()!=0){lecTutSectionInfo.put(lecturecode, sectionMeetingTimes);}
        }
        retCourseInfo.put(code + " -" + term, lecTutSectionInfo);
        //System.out.println(retCourseInfo);
        return retCourseInfo;

    }

    public static void main(String[] args) {
        /**
         Try it out!
         */
    }


    private static void availableCourses () {
        /**
         * This method has the sole purpose of creating a textfile containing all of the
         * courses that Arts and Science department has.
         */
        try {
            String fileName = "availableCourses.txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            // k goes up to 183 because we assume there are only 183 pages.
            for (int k = 1; k <= 183; k++) {
                JSONObject rawJson = availableCourses_helper(k);
                JSONObject payload = rawJson.getJSONObject("payload");
                JSONObject pageableCourse = payload.getJSONObject("pageableCourse");
                JSONArray courses = pageableCourse.getJSONArray("courses");
                int i = courses.length();
                for (int j=1; j <= i-1; j++) {
                    JSONObject fullCoursesInfo = (JSONObject) courses.get(j);
                    String courseCode = fullCoursesInfo.get("code").toString();
                    String term = fullCoursesInfo.get("sectionCode").toString();
                    writer.write(courseCode + " -" + term);
                    writer.newLine();
                }
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static JSONObject availableCourses_helper ( int k){
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, String.format("{\"courseCodeAndTitleProps\":{\"courseCode\":\"\",\"courseTitle\":\"\",\"courseSectionCode\":\"\"},\"departmentProps\":[],\"campuses\":[],\"sessions\":[\"20239\",\"20241\",\"20239-20241\"],\"requirementProps\":[],\"instructor\":\"\",\"courseLevels\":[],\"deliveryModes\":[],\"dayPreferences\":[],\"timePreferences\":[],\"divisions\":[\"ARTSC\"],\"creditWeights\":[],\"page\":%s,\"pageSize\":20,\"direction\":\"asc\"}\n  ", k));
        Request request = new Request.Builder()
                .url("https://api.easi.utoronto.ca/ttb/getPageableCourses")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json, text/plain, */*")
                .addHeader("Accept-Encoding", "gzip, deflate, br")
                .addHeader("Accept-Language", "en-US,en;q=0.9,zh-TW;q=0.8,zh-CN;q=0.7,zh;q=0.6")
                .addHeader("Connection", "keep-alive")
                .addHeader("Origin", "https://ttb.utoronto.ca")
                .addHeader("Referer", "https://ttb.utoronto.ca/")
                .addHeader("Sec-Fetch-Dest", "empty")
                .addHeader("Sec-Fetch-Mode", "cors")
                .addHeader("Sec-Fetch-Site", "same-site")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36")
                .addHeader("sec-ch-ua", "^\\^Google")
                .addHeader("sec-ch-ua-mobile", "?0")
                .build();

        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());
            return responseBody;
        } catch (IOException | JSONException e) {
            throw new RuntimeException();
        }
    }

}