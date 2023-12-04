package API;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
public class UofTCoursesAPI implements CourseAPIInterface{
    @Override
    public HashMap<String, HashMap<String, ArrayList<HashMap<String,Object>>>> getCourse(String course) throws IOException {
        /**
         * Returns a HashMap
         * Sample return
         * -> {CSC207H1 -F=
         *      {LEC5101=[{Start=64800000, endtime=72000000, Day=4, building=KP}],
         *      LEC0501=[{Start=57600000, endtime=61200000, Day=2, building=BA}, {Start=57600000, endtime=4, Day=4, building=BA}]}
         */
        System.out.println("UofT Courses API Used");
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
}
