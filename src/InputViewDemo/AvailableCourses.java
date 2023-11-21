package InputViewDemo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class AvailableCourses {
    /**
     * This class exist solely for the purpose of returning a String[] of all available classes
     * will probably make into a method of some class in the future.
     */
    public static String[] getAvailableCourses() throws IOException{
        Path path = Paths.get("availableCourses.txt");

        // Read all lines from the file into a List of strings
        List<String> lines = Files.readAllLines(path);

        // Convert the List to a String array
        String[] linesArray = lines.toArray(new String[0]);

        return linesArray;
    }
    }


