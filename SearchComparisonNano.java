import java.util.*;

public class SearchComparisonNano {
    // Generate a dataset with unique random numbers
    public static int[] generateDataset(int size) {
        Random rand = new Random();
        Set<Integer> set = new HashSet<>();

        while (set.size() < size) {
            set.add(rand.nextInt(size * 10)); // Unique numbers
        }

        int[] dataset = new int[size];
        int index = 0;
        for (int num : set) {
            dataset[index++] = num;
        }
        return dataset;
    }

    // Linear Search: O(N)
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    // Binary Search: O(log N)
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    // Measure execution time using System.nanoTime()
    public static void measurePerformance() {
        int[] datasetSizes = {1_000, 10_000, 1_000_000};

        for (int size : datasetSizes) {
            int[] dataset = generateDataset(size);
            int target = dataset[new Random().nextInt(size)];

            // Measure Linear Search Time
            long startTime = System.nanoTime();
            linearSearch(dataset, target);
            long linearTime = System.nanoTime() - startTime;

            // Sort dataset for Binary Search
            Arrays.sort(dataset); // O(N log N)

            // Measure Binary Search Time
            startTime = System.nanoTime();
            binarySearch(dataset, target);
            long binaryTime = System.nanoTime() - startTime;

            System.out.println("Dataset Size: " + size);
            System.out.printf("Linear Search Time: %.3f ms%n", linearTime / 1e6);
            System.out.printf("Binary Search Time: %.3f ms (after sorting)%n", binaryTime / 1e6);
        }
    }

    public static void main(String[] args) {
        measurePerformance();
    }
}

// Dataset Size: 1000
// Linear Search Time: 0.011 ms
// Binary Search Time: 0.003 ms (after sorting)
// Dataset Size: 10000
// Linear Search Time: 0.084 ms
// Binary Search Time: 0.002 ms (after sorting)
// Dataset Size: 1000000
// Linear Search Time: 2.009 ms
// Binary Search Time: 0.006 ms (after sorting)
