import java.util.*;
import java.util.stream.*;

public class MinimalSpecialSubArray {

    private static Random generator = new Random();

    public static int bruteForceSolution(int[] arr, int k) {
        int result = Integer.MAX_VALUE;

        List<List<Integer>> subsets = generateSubsets(arr, k);

        for (List<Integer> subset : subsets) {
            Collections.sort(subset);
            int newResult = calculateValueForSubset(subset, k);

            if (newResult < result)
                result = newResult;
        }

        return result;
    }

    private static List<List<Integer>> generateSubsets(int[] arr, int k) {
        int idx = 0;
        List<Integer> curArr = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        subsetsHelper(arr, k, idx, curArr, result);
        return result;
    }

    private static void subsetsHelper(int[] arr, int left, int idx,
            List<Integer> curArr, List<List<Integer>> result) {
        if (left <= 0) {
            ArrayList<Integer> tmp = new ArrayList<>(curArr);
            result.add(tmp);
            return;
        }
        for (int i = idx; i < arr.length; i++) {
            curArr.add(arr[i]);
            subsetsHelper(arr, left - 1, i + 1, curArr, result);
            curArr.remove(curArr.size() - 1);
        }
    }

    private static int calculateValueForSubset(List<Integer> subset, int k) {
        int result = 0;
        for (int i = 1; i < k; i++) {
            result += Math.abs(subset.get(i) - subset.get(i - 1));
        }
        return result;
    }

    public static int polynomialSolution(int[] arr, int k) {

        int[] sorted = arr.clone();

        Arrays.sort(sorted);
        int result = Integer.MAX_VALUE;

        for (int i = 0; i < (arr.length - k + 1); i++) {
            int newResult = calculateValueForSubset(sorted, i, k);

            if (newResult < result)
                result = newResult;
        }
        return result;
    }

    private static int calculateValueForSubset(int[] arr, int index, int k) {
        int result = 0;
        for (int i = index + 1; i < (k + index); i++) {
            result += Math.abs(arr[i] - arr[i - 1]);
        }
        return result;
    }

    public static void testSuite() {
        int[] arr = { 9, 1, 2, 5, 4, 9 };
        testSet(arr, "0");

        int[] arr1 = { 0, 1, 2, 5, 4, 9 };
        testSet(arr1, "1");

        int[] arr2 = { 0, 1, -2, 5, 4, -9 };
        testSet(arr2, "2");

        int[] arr3 = { 0, -1, -100, 18, 34 - 2, 5, 4, -9 };
        testSet(arr3, "3");
    }

    public static void testSet(int[] arr, String name) {
        System.out.println("Test: " + name);
        for (int i = 1; i <= arr.length; i++) {
            testUnit(arr, i);
        }
    }

    public static double testUnit(int[] arr, int i) {
        long startTime = System.nanoTime();
        int bruteResult = bruteForceSolution(arr, i);
        long bruteTime = System.nanoTime();

        int polynomialResult = polynomialSolution(arr, i);
        long polynomialTime = System.nanoTime() - bruteTime;
        bruteTime = bruteTime - startTime;

        System.out.println(
                "Brute Force - k = " + i + " -  Execution time: " + bruteTime + " nanoseconds - Result: "
                        + bruteResult);
        System.out.println("Polynomial - k = " + i + " -  Execution time: " + polynomialTime + " nanoseconds - Result: "
                + polynomialResult);
        System.out.println("Same result? " + (polynomialResult == bruteResult));
        double ratio = (polynomialTime * 1.0) / bruteTime;
        System.out.println("polynomial time / brute time = " + ratio);
        System.out.println("-----------");
        return ratio;
    }

    public static double testWithRandom(int n, int k) {
        int[] randomIntsArray = IntStream.generate(() -> generator.nextInt(n)).limit(n).toArray();
        return testUnit(randomIntsArray, k);
    }

    public static void main(String[] args) {
        List<Double> results = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            results.add(testWithRandom(25, generator.nextInt(25)));

        OptionalDouble rationMean = results.stream().mapToDouble(a -> a).average();
        System.out.println("The average of the ration is " + rationMean.getAsDouble());
    }
}