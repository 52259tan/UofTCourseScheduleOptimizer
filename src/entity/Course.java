package entity;

import entity.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Course {
    private String courseName;
    private List<Session> lecSessions = new ArrayList<>();
    private List<Session> tutSessions = new ArrayList<>();
    private List<Session> praSessions = new ArrayList<>();


    public Course(HashMap<String, HashMap<String, ArrayList<HashMap<String,Object>>>> courseInfo){
        courseName = courseInfo.keySet().iterator().next().toString();
        for (String sessionCode: courseInfo.get(courseName).keySet()){
            Session session = new Session(courseInfo.get(courseName).get(sessionCode));
            session.setSessionCode(courseName.substring(0,6) +' '+ sessionCode);
            String type = sessionCode.substring(0,3);
            if (type.equals("LEC")){
                lecSessions.add(session);
            } else if (type.equals("TUT")) {
                tutSessions.add(session);
            }else{
                praSessions.add(session);
            }
        }
    }

    public List<Session> getLecSessions(){
        return lecSessions;
    }

    public List<Session> getTutSessions(){
        return tutSessions;
    }
    public List<Session> getPraSessions(){
        return praSessions;
    }
    public boolean hasPra(){
        /**
         * return True if there are PRActical sessions for this course
         */
        return praSessions.isEmpty();
    }
    // Percy: Added a getter
    public String getCourseName() {
        return courseName;
    }
//    Testing
//    public static void main(String[] args) {
//        entity.Course course = new entity.Course(CourseAPI.getCourse("CSC207H1 -F"));
//        System.out.println(course.courseName);
//        System.out.println(course.lecSessions);
//        System.out.println(course.tutSessions);
//        System.out.println(course.praSessions);
//        System.out.println(course.hasPra());
//    }
}
