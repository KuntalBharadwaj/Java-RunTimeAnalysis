import java.util.Arrays;
import java.util.Random;

public class SortingComparison {
    // Generate an array with random numbers
    public static int[] generateDataset(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(size * 10); // Random numbers
        }
        return arr;
    }

    // Bubble Sort (O(N²))
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break; // Optimization: If no swaps, stop early
        }
    }

    // Merge Sort (O(N log N))
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        for (int i = 0; i < n1; i++) leftArr[i] = arr[left + i];
        for (int j = 0; j < n2; j++) rightArr[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
        while (i < n1) arr[k++] = leftArr[i++];
        while (j < n2) arr[k++] = rightArr[j++];
    }

    // Quick Sort (O(N log N))
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // Measure sorting performance
    public static void measureSortingPerformance() {
        int[] datasetSizes = {1_000, 10_000, 100_000}; // Avoid 1M for Bubble Sort due to time

        for (int size : datasetSizes) {
            int[] dataset1 = generateDataset(size);
            int[] dataset2 = Arrays.copyOf(dataset1, dataset1.length);
            int[] dataset3 = Arrays.copyOf(dataset1, dataset1.length);

            System.out.println("Dataset Size: " + size);

            // Bubble Sort
            long startTime = System.nanoTime();
            if (size <= 10_000) { // Bubble Sort takes too long for large N
                bubbleSort(dataset1);
                long bubbleTime = System.nanoTime() - startTime;
                System.out.printf("Bubble Sort Time: %.3f ms%n", bubbleTime / 1e6);
            } else {
                System.out.println("Bubble Sort Skipped (Too Slow)");
            }

            // Merge Sort
            startTime = System.nanoTime();
            mergeSort(dataset2, 0, dataset2.length - 1);
            long mergeTime = System.nanoTime() - startTime;
            System.out.printf("Merge Sort Time: %.3f ms%n", mergeTime / 1e6);

            // Quick Sort
            startTime = System.nanoTime();
            quickSort(dataset3, 0, dataset3.length - 1);
            long quickTime = System.nanoTime() - startTime;
            System.out.printf("Quick Sort Time: %.3f ms%n", quickTime / 1e6);
        }
    }

    public static void main(String[] args) {
        measureSortingPerformance();
    }
}


// Dataset Size: 1000
// Bubble Sort Time: 3.498 ms
// Merge Sort Time: 0.711 ms
// Quick Sort Time: 0.391 ms
// Dataset Size: 10000
// Bubble Sort Time: 103.902 ms
// Merge Sort Time: 1.879 ms
// Quick Sort Time: 1.759 ms
// Dataset Size: 100000
// Bubble Sort Skipped (Too Slow)
// Merge Sort Time: 92.039 ms
// Quick Sort Time: 11.183 ms