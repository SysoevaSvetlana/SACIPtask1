public class ArrayCompressor {
    public static int[] compressArray(int[] arr) {
        int uniqueElementCount = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i == 0 || arr[i] != arr[i - 1]) {
                arr[uniqueElementCount++] = arr[i];
            }
        }
        return java.util.Arrays.copyOf(arr, uniqueElementCount);
    }

    public static void main(String[] args) {
        int[] inputArray = {1, 1, 2, 2, 2, 3, 3, 5, 3, 4, 4, 4, 4, 4};
        System.out.println("Original array: " + java.util.Arrays.toString(inputArray));
        int[] compressedArray = compressArray(inputArray);
        System.out.println("Compressed array: " + java.util.Arrays.toString(compressedArray));
    }
}

