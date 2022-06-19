import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Helper {
    /**
     * This method is static to stay independently from instances. The main task of this method is to
     * read file from chosen fileName from user.
     * The method will be called in main for loadEnrollments function to execute further action
     * @param fileName- in string format
     * @return List of string data from the specific file names fileLines.
     * @throws Exception if there is error in execution.
     */
    public static List<String> readFile(String fileName) throws Exception {
        // ref: https://mkyong.com/java8/java-8-stream-read-a-file-line-by-line/
        List<String> fileLines = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(fileLines::add);
        } catch (IOException e) {
            throw new Exception("Unable to read file.");
        }
        return fileLines;
    }


    /**
     * Method to write file used for export csv method if any command is requested by user option "y"
     * @param fileName - fileName is the user input from scanner to name the to-be-exported file
     * @param buffer - number of elements it contains
     * @throws Exception if there is error in execution
     */
    public static void writeFile(String fileName, String buffer) throws Exception {
        // ref: https://www.w3schools.com/java/java_files_create.asp
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(buffer);
            myWriter.close();
        } catch (IOException e) {
            throw new Exception("Unable to write file.");
        }
    }

}
