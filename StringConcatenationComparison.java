public class StringConcatenationComparison {

    private static final int SMALL_N = 1_000;    // Small dataset
    private static final int MEDIUM_N = 10_000;  // Medium dataset
    private static final int LARGE_N = 1_000_000; // Large dataset

    // Measure execution time
    public static long measureTime(Runnable method) {
        long startTime = System.nanoTime();
        method.run();
        return (System.nanoTime() - startTime) / 1_000_000; // Convert to milliseconds
    }

    // String Concatenation (O(N²))
    public static void stringConcatenation(int n) {
        String s = "";
        for (int i = 0; i < n; i++) {
            s += "a"; // Inefficient: New object created in each iteration
        }
    }

    // StringBuilder Concatenation (O(N))
    public static void stringBuilderConcatenation(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("a");
        }
    }

    // StringBuffer Concatenation (O(N))
    public static void stringBufferConcatenation(int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb.append("a");
        }
    }

    // Execute and compare performance
    public static void comparePerformance(int n) {
        System.out.println("Testing with N = " + n);

        long stringTime = measureTime(() -> stringConcatenation(n));
        System.out.printf("String Concatenation Time: %d ms%n", stringTime);

        long stringBuilderTime = measureTime(() -> stringBuilderConcatenation(n));
        System.out.printf("StringBuilder Concatenation Time: %d ms%n", stringBuilderTime);

        long stringBufferTime = measureTime(() -> stringBufferConcatenation(n));
        System.out.printf("StringBuffer Concatenation Time: %d ms%n", stringBufferTime);
    }

    public static void main(String[] args) {
        comparePerformance(SMALL_N);
        comparePerformance(MEDIUM_N);
        comparePerformance(LARGE_N);
    }
}

// Testing with N = 1000
// String Concatenation Time: 1 ms
// StringBuilder Concatenation Time: 0 ms
// StringBuffer Concatenation Time: 0 ms
// Testing with N = 10000
// String Concatenation Time: 19 ms
// StringBuilder Concatenation Time: 0 ms
// StringBuffer Concatenation Time: 0 ms
// Testing with N = 1000000