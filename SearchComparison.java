import java.util.*;

public class SearchComparison {

    // Linear Search in Array (O(N))
    public static boolean linearSearch(int[] arr, int target) {
        for (int num : arr) {
            if (num == target) return true;
        }
        return false;
    }

    // Measure execution time in milliseconds
    public static long measureTime(Runnable method) {
        long startTime = System.nanoTime();
        method.run();
        return (System.nanoTime() - startTime) / 1_000_000; // Convert to ms
    }

    public static void comparePerformance(int N) {
        Random rand = new Random();
        int[] array = new int[N];
        HashSet<Integer> hashSet = new HashSet<>();
        TreeSet<Integer> treeSet = new TreeSet<>();

        // Fill data structures with random numbers
        for (int i = 0; i < N; i++) {
            int num = rand.nextInt(N * 10); // Ensure large range to reduce duplicate numbers
            array[i] = num;
            hashSet.add(num);
            treeSet.add(num);
        }

        // Pick a random target number to search
        int target = array[N / 2];

        System.out.println("Searching in dataset size: " + N);

        long arrayTime = measureTime(() -> linearSearch(array, target));
        System.out.printf("Array Search (O(N)): %d ms%n", arrayTime);

        long hashSetTime = measureTime(() -> hashSet.contains(target));
        System.out.printf("HashSet Search (O(1)): %d ms%n", hashSetTime);

        long treeSetTime = measureTime(() -> treeSet.contains(target));
        System.out.printf("TreeSet Search (O(log N)): %d ms%n", treeSetTime);

    }

    public static void main(String[] args) {
        comparePerformance(1_000);
        comparePerformance(100_000);
        comparePerformance(1_000_000);
    }
}


// Searching in dataset size: 1000
// Array Search (O(N)): 0 ms
// HashSet Search (O(1)): 0 ms
// TreeSet Search (O(log N)): 0 ms
// Searching in dataset size: 100000
// Array Search (O(N)): 0 ms
// HashSet Search (O(1)): 0 ms
// TreeSet Search (O(log N)): 0 ms
// Searching in dataset size: 1000000
// Array Search (O(N)): 1 ms
// HashSet Search (O(1)): 0 ms
// TreeSet Search (O(log N)): 0 ms