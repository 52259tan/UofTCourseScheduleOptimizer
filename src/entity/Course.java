package entity;

import entity.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Course {
    private String courseName;
    private List<Session> lecSessions = new ArrayList<>();
    private List<Session> tutSessions = new ArrayList<>();
    private List<Session> praSessions = new ArrayList<>();


    public Course(HashMap<String, HashMap<String, ArrayList<HashMap<String,Object>>>> courseInfo){
        courseName = courseInfo.keySet().iterator().next().toString();
        for (String sessionCode: courseInfo.get(courseName).keySet()){
            Session session = new Session(courseInfo.get(courseName).get(sessionCode));
            session.setSessionCode(sessionCode);
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
// Ping: Get a list of all possible sessions (Lec, Tut and Pra) for a certain course
    private static List<List<Session>> generateCombinations(List<Session> list1, List<Session> list2, List<Session> list3) {
        List<List<Session>> allCombinations = new ArrayList<>();

        for (Session element1 : list1) {
            for (Session element2 : list2) {
                for (Session element3 : list3) {
                    List<Session> combination = new ArrayList<>();
                    combination.add(element1);
                    combination.add(element2);
                    combination.add(element3);
                    allCombinations.add(combination);
                }
            }
        }

        return allCombinations;
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
