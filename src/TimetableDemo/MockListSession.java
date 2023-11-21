package TimetableDemo;

import API.CourseAPI;
import entity.Course;
import entity.Session;

import java.util.ArrayList;
import java.util.List;

public class MockListSession {

    // Hi percy this returns a list of Sessions
    // Put MockListSession.mockSessions() in your code to get a list of sessions.
    // Each course has list lec sessions and list tut sessions, for each of the course, I take
    // the first session of the lec session and tut session in the lists.
    public static List<Session> mockSessions(){
        Course csc207 = new Course(CourseAPI.getCourse("CSC207H1 -F"));
        Course csc209 = new Course(CourseAPI.getCourse("CSC209H1 -F"));
        Course mat337 = new Course(CourseAPI.getCourse("MAT337H1 -F"));
        List<Session> ret_list = new ArrayList<>();
        ret_list.add(csc207.getLecSessions().get(0));
        ret_list.add(csc207.getTutSessions().get(0));
        ret_list.add(csc209.getLecSessions().get(0));
        ret_list.add(csc209.getTutSessions().get(0));
        ret_list.add(mat337.getLecSessions().get(0));
        ret_list.add(mat337.getTutSessions().get(0));
        return ret_list;
    }


    // This returns you a list of courses if you need
    public static List<Course> mockList(){
        Course csc207 = new Course(CourseAPI.getCourse("CSC207H1 -F"));
        Course csc209 = new Course(CourseAPI.getCourse("CSC209H1 -F"));
        Course mat337 = new Course(CourseAPI.getCourse("MAT337H1 -F"));
        List<Course> ret_list = new ArrayList<>();
        ret_list.add(csc207);
        ret_list.add(csc209);
        ret_list.add(mat337);
        return ret_list;

    }

    public static void main(String[] args) {
        System.out.println(mockSessions());
    }}