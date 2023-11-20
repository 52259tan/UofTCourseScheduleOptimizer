package algorithm;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeRangeOverlapChecker {
    public static void main(String[] args) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

            // Define the first time range
            Date range1Start = sdf.parse("5:00");
            Date range1End = sdf.parse("7:00");

            // Define the second time range
            Date range2Start = sdf.parse("6:00");
            Date range2End = sdf.parse("8:00");

            // Check if the time ranges overlap
            boolean overlap = doTimeRangesOverlap(range1Start, range1End, range2Start, range2End);

            if (overlap) {
                System.out.println("The time ranges overlap.");
            } else {
                System.out.println("The time ranges do not overlap.");
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static boolean doTimeRangesOverlap(Date start1, Date end1, Date start2, Date end2) {
        return end1.after(start2) && start1.before(end2);
    }
}