public class FibonacciComparison {

    // Recursive Fibonacci (O(2^n))
    public static int fibonacciRecursive(int n) {
        if (n <= 1) return n;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    // Iterative Fibonacci (O(N))
    public static int fibonacciIterative(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1, sum;
        for (int i = 2; i <= n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }

    // Measure execution time in milliseconds
    public static long measureTime(Runnable method) {
        long startTime = System.nanoTime();
        method.run();
        return (System.nanoTime() - startTime) / 1_000_000; // Convert to ms
    }

    public static void comparePerformance(int n) {
        System.out.println("Fibonacci for N = " + n);

        if (n <= 40) { // Recursive is impractical for larger values
            long recursiveTime = measureTime(() -> fibonacciRecursive(n));
            System.out.printf("Recursive Fibonacci Time: %d ms%n", recursiveTime);
        } else {
            System.out.println("Recursive approach skipped (too slow for large N)");
        }

        long iterativeTime = measureTime(() -> fibonacciIterative(n));
        System.out.printf("Iterative Fibonacci Time: %d ms%n", iterativeTime);
    }

    public static void main(String[] args) {
        comparePerformance(10);
        comparePerformance(30);
        comparePerformance(50); // Skipping recursion for N > 40
    }
}


// Fibonacci for N = 10
// Recursive Fibonacci Time: 0 ms
// Iterative Fibonacci Time: 0 ms
// Fibonacci for N = 30
// Recursive Fibonacci Time: 5 ms
// Iterative Fibonacci Time: 0 ms
// Fibonacci for N = 50
// Recursive approach skipped (too slow for large N)
// Iterative Fibonacci Time: 0 ms
