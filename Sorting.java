import java.util.Arrays;

public class Sorting {
    public static void main(String[] args) {
        int[] arr = generateRandomArray(10);
        System.out.println("Array - " + Arrays.toString(arr));
        selectionSort(arr.clone());
        insertionSort(arr.clone());
        baboolSort(arr.clone());
        quickSortDriver(arr.clone());
        mergeSortDriver(arr.clone());
        librarySort(arr.clone());
        heapSort(arr.clone());
    }

    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * size * size);
        }
        return array;
    }

    public static void selectionSort(int[] array) {
        long t = System.nanoTime();
        for (int i = 0; i < array.length; i++) {
            int index = i;
            int min = array[i];
            for (int j = i; j < array.length; j++) {
                if (array[j] < min) {
                    index = j;
                    min = array[j];
                }
            }
            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }

        System.out.println("Selection sort - " + Arrays.toString(array) + ", took - " + (System.nanoTime() - t) + "ns");
    }

    public static void insertionSort(int[] array) {
        long t = System.nanoTime();
        for (int i = 1; i < array.length; i++) {
            int auxI = i;
            for (int j = i-1; j >= 0; j--) {
                if (array[auxI] < array[j]) {
                    int temp = array[auxI];
                    array[auxI] = array[j];
                    array[j] = temp;
                    auxI = j;
                }
            }
        }
        System.out.println("Insertion sort - " + Arrays.toString(array) + ", took - " + (System.nanoTime() - t) + "ns");
    }

    public static void baboolSort(int[] array) {
        long t = System.nanoTime();
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length - i; j++) {
                if (array[j] < array[j - 1]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
        System.out.println("Babool sort - " + Arrays.toString(array) + ", took - " + (System.nanoTime() - t) + "ns");
    }

    public static int partition(int arr[], int low, int high) {
        int pivot = arr[low];
        int i = low - 1, j = high + 1;
        while (i < j) {
            do {
                i++;
            } while (arr[i] < pivot);

            do {
                j--;
            } while (arr[j] > pivot);

            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
            
        }

        return j;
    }

    public static void quickSort(int arr[], int low, int high) {
        if (low < high) {
            int j = partition(arr, low, high);
            quickSort(arr, low, j);
            quickSort(arr, j + 1, high);
        }
    }

    public static void quickSortDriver(int array[]) {
        long t = System.nanoTime();
        quickSort(array, 0, array.length - 1);
        System.out.println("Quick sort - " + Arrays.toString(array) + ", took - " + (System.nanoTime() - t) + "ns");
    }

    public static void merge(int[] array, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int i, j, k;
        int[] L = new int[n1], R = new int[n2]; 

        for (i = 0; i < n1; i++) L[i] = array[l + i];
        for (i = 0; i < n2; i++) R[i] = array[m + 1 + i];

        for (i = j = 0, k = l; i < n1 && j < n2;) {
            if (L[i] <= R[j])
                array[k++] = L[i++];
            else
                array[k++] = R[j++];
        }

        while (i < n1) {
            array[k++] = L[i++];
        }

        while (j < n2) {
            array[k++] = R[j++];
        }
    }

    public static void mergeSort(int[] array, int l, int r) {
        if (l < r) {
            int m = (l + r)/2;
            mergeSort(array, l, m);
            mergeSort(array, m + 1, r);
            merge(array, l, m, r);
        }
    }

    public static void mergeSortDriver(int[] array) {
        long t = System.nanoTime();
        mergeSort(array, 0, array.length - 1);
        System.out.println("Merge sort - " + Arrays.toString(array) + ", took - " + (System.nanoTime() - t) + "ns");
    }

    public static void librarySort(int[] array) {
        long t = System.nanoTime();
        Arrays.sort(array);
        System.out.println("Library sort - " + Arrays.toString(array) + ", took - " + (System.nanoTime() - t) + "ns");
    }

    public static void heapify(int[] arr, int i, int n) {
        int largest = i;
        int  l = 2 * i + 1;
        int r = l + 1;

        if (l < n && arr[l] > arr[largest])
            largest = l;

        if (r < n && arr[r] > arr[largest])
            largest = r;

        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            heapify(arr, largest, n);
        }
    }

    public static void heapSort(int[] arr) {
        long t = System.nanoTime();
        int n = arr.length;

        for (int i = n/2 - 1; i >= 0; i--)
            heapify(arr, i, n);

        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, 0, i);
        }

        System.out.println("Heap sort - " + Arrays.toString(arr) + ", took - " + (System.nanoTime() - t) + "ns");
    }
}