package ErrorView;
import javax.swing.JOptionPane;
import java.util.List;

public class ErrorView {
    /**
    Specifically gives a popup regarding invalid input of course codes.
     **/
    public static void ErrorViewInvalidCourseCode(String coursecode){
        JOptionPane.showMessageDialog(null, String.format("%s is not a valid course code, please check your input again.", coursecode));
    }

    public static void ErrorViewInvalidCourseCode(List<String> courseCodes){
        String result = String.join(", ", courseCodes);
        JOptionPane.showMessageDialog(null, String.format("%s is not a valid course code, please check your input again.", result));

    }

    public static void ErrorViewTermCodeMismatch(){
        JOptionPane.showMessageDialog(null, "System detects both FALL(-F) and WINTER(-S) courses, please recheck your input.");
    }

    public static void ErrorViewNoInput(){
        JOptionPane.showMessageDialog(null, "System detects no input, please add a course before submitting.");
    }
}
