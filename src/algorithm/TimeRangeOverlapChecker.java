package algorithm;
import java.util.List;

/** Check if two sessions have time conflict
 * @author pinglu
 */
public class TimeRangeOverlapChecker {
    /** Check if two sessions have time conflict
     * @param startTimes the lists of all start times in a session
     * @param endTimes the lists of all end times in a session
     * @return A boolean if the two sessions have time conflict
     */
    public static boolean checkNoOverlap(List<Integer> startTimes, List<Integer> endTimes) {
        if (startTimes.size() != endTimes.size()) {
            throw new IllegalArgumentException("Lists must have the same size.");
        }

        int size = startTimes.size();

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                // Check if the current pair does not overlap
                if (!(endTimes.get(i) < startTimes.get(j)) && !(startTimes.get(i) > endTimes.get(j))) {
                    return false;
                }
            }
        }

        // No overlap found
        return true;
    }
}
