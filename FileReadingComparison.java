import java.io.*;

public class FileReadingComparison {

    private static final String FILE_PATH = "large_file.txt"; // Change to your file path

    // Measure execution time
    public static long measureTime(Runnable method) {
        long startTime = System.nanoTime();
        method.run();
        return (System.nanoTime() - startTime) / 1_000_000; // Convert to milliseconds
    }

    // Read file using FileReader (Character Stream)
    public static void readFileUsingFileReader(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            while (reader.read() != -1) {} // Read character by character
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read file using InputStreamReader (Byte Stream)
    public static void readFileUsingInputStreamReader(String filePath) {
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(filePath))) {
            while (reader.read() != -1) {} // Read byte by byte
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read file using BufferedReader for optimal performance
    public static void readFileUsingBufferedReader(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.readLine() != null) {} // Read line by line
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void comparePerformance(String filePath) {
        System.out.println("Reading file: " + filePath);

        long fileReaderTime = measureTime(() -> readFileUsingFileReader(filePath));
        System.out.printf("FileReader Time: %d ms%n", fileReaderTime);

        long inputStreamReaderTime = measureTime(() -> readFileUsingInputStreamReader(filePath));
        System.out.printf("InputStreamReader Time: %d ms%n", inputStreamReaderTime);

        long bufferedReaderTime = measureTime(() -> readFileUsingBufferedReader(filePath));
        System.out.printf("BufferedReader Time: %d ms (Fastest for text)%n", bufferedReaderTime);
    }

    public static void main(String[] args) {
        comparePerformance(FILE_PATH);
    }
}
