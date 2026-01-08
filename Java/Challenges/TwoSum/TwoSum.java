import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement))
                return new int[]{map.get(complement), i};
            
            map.put(nums[i], i);
        }
        
        return new int[]{};
    }
    
    // Tests created with deepseek
    public static void main(String[] args) {
        TwoSum solution = new TwoSum();
        
        // Basic test cases
        int[][][] basicTests = {
            {{2, 7, 11, 15}, {9}},    // Expected: [0, 1]
            {{3, 2, 4}, {6}},         // Expected: [1, 2]
            {{3, 3}, {6}},            // Expected: [0, 1]
            {{1, 5, 3, 7}, {10}}      // Expected: [2, 3]
        };
        
        int[][] basicExpected = {
            {0, 1},
            {1, 2},
            {0, 1},
            {2, 3}
        };
        
        System.out.println("=== BASIC TESTS ===\n");
        int passed = runTestSuite(solution, "Basic", basicTests, basicExpected);
        
        // Complex test cases
        int[][][] complexTests = {
            // Large array with solution at the end
            {{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20}, {39}},
            
            // Negative numbers with positive target
            {{-1, -2, -3, -4, 5, 6, 7}, {2}},
            
            // Positive numbers with negative target
            {{1, 2, 3, 4, -5, -6}, {-1}},
            
            // All negative numbers
            {{-10, -20, -30, -40, -50}, {-50}},
            
            // Zero target with mixed numbers
            {{5, -5, 10, -10, 0, 3}, {0}},
            
            // Large numbers
            {{1000000, 2000000, 3000000, 4000000}, {5000000}},
            
            // Duplicate values that could confuse
            {{1, 1, 1, 1, 1, 1, 1, 2}, {3}},
            
            // Solution is first and last element
            {{10, 1, 2, 3, 4, 5, 6, 7, 8, 9}, {19}},
            
            // Array with zeros
            {{0, 0, 0, 0, 5, 0, 0, 0}, {5}},
            
            // Multiple possible solutions (should return the first found)
            {{1, 2, 3, 4, 5}, {6}},
            
            // Large difference between numbers
            {{-1000, 1000, 0, 999, 1}, {0}},
            
            // Ascending and descending mixed
            {{10, 1, 9, 2, 8, 3, 7, 4, 6, 5}, {11}},
            
            // Maximum and minimum integer values
            {{Integer.MAX_VALUE, Integer.MIN_VALUE, 0, 1}, {-1}},
            
            // Exactly half target (special case for some implementations)
            {{4, 4, 2, 8}, {8}},
            
            // Empty array edge case (shouldn't happen per problem but testing robustness)
            {{}, {0}}
        };
        
        int[][] complexExpected = {
            {18, 19},
            {3, 6},
            {4, 5},
            {0, 2},
            {1, 4},
            {1, 2},
            {6, 7},
            {0, 9},
            {4, 0},
            {1, 3},  // Could be [0,4] or [1,3] depending on implementation
            {1, 2},
            {1, 8},
            {1, 3},
            {0, 1},
            {} // No solution for empty array
        };
        
        System.out.println("\n=== COMPLEX TESTS ===\n");
        passed += runTestSuite(solution, "Complex", complexTests, complexExpected);
        
        // Performance test with large array
        System.out.println("\n=== PERFORMANCE TEST ===\n");
        int size = 100000;
        int[] largeArray = new int[size];
        for (int i = 0; i < size; i++) {
            largeArray[i] = i * 2; // Even numbers only
        }
        int largeTarget = (size - 2) * 2 + 2; // Sum of last two elements
        long startTime = System.currentTimeMillis();
        int[] result = solution.twoSum(largeArray, largeTarget);
        long endTime = System.currentTimeMillis();
        
        if (result.length == 2) {
            int sum = largeArray[result[0]] + largeArray[result[1]];
            boolean correct = sum == largeTarget;
            System.out.println("Large array (size = " + size + "):");
            System.out.println("  Target: " + largeTarget);
            System.out.println("  Result indices: [" + result[0] + ", " + result[1] + "]");
            System.out.println("  Values: " + largeArray[result[0]] + " + " + largeArray[result[1]]);
            System.out.println("  Sum: " + sum + " (target: " + largeTarget + ")");
            System.out.println("  Correct: " + (correct ? "✓" : "✗"));
            System.out.println("  Time: " + (endTime - startTime) + "ms");
        }
        
        // Additional tricky test cases
        System.out.println("\n=== TRICKY EDGE CASES ===\n");
        int[][][] trickyTests = {
            // Three elements where first two sum to target
            {{3, 2, 3}, {6}},
            
            // Numbers in descending order
            {{10, 8, 6, 4, 2}, {10}},
            
            // Same number repeated, target is double that number
            {{5, 5, 5, 5}, {10}},
            
            // Very sparse array
            {{0, 100, 0, 0, 0, 0, 0, 0, 0, 100}, {200}},
            
            // Single element array (should fail)
            {{5}, {10}}
        };
        
        int[][] trickyExpected = {
            {0, 2},
            {1, 3},
            {0, 1},
            {1, 9},
            {}
        };
        
        passed += runTestSuite(solution, "Tricky", trickyTests, trickyExpected);
        
        System.out.println("\n=== FINAL SUMMARY ===");
        System.out.println("Total tests passed: " + passed + " out of " + 
                          (basicTests.length + complexTests.length + trickyTests.length));
    }
    
    private static int runTestSuite(
        TwoSum solution, String suiteName, 
        int[][][] tests, int[][] expected
    ) {
        int passed = 0;
        for (int i = 0; i < tests.length; i++) {
            int[] nums = tests[i][0];
            int target = tests[i][1][0];
            int[] result = solution.twoSum(nums, target);
            
            boolean success = false;
            if (result.length == 2) {
                int sum = nums[result[0]] + nums[result[1]];
                success = sum == target && result[0] != result[1];
            } else if (result.length == 0) {
                // No solution case
                success = true;
            }

            System.out.printf("%s Test %d:\n", suiteName, (i + 1));
            System.out.printf("  Input: nums = %s, target = %d\n", Arrays.toString(nums), target);
            System.out.printf("  Result: %s\n", Arrays.toString(result));
            
            if (success) {
                if (result.length == 2) {
                    System.out.printf("  ✓ PASSED - Values: %d + %d = %d\n", nums[result[0]], nums[result[1]], target);
                } else {
                    System.out.println("  ✓ PASSED - No solution (as expected)");
                }
                passed++;
            } else {
                System.out.println("  ✗ FAILED");
                if (result.length == 2) {
                    System.out.printf("  Got sum: %d (expected: %d)\n", (nums[result[0]] + nums[result[1]]), target);
                }
            }
            System.out.println();
        }

        System.out.printf("%s suite: %d/%d passed\n", suiteName, passed, tests.length);
        return passed;
    }
}
