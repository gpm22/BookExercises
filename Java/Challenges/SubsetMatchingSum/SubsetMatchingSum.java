import java.util.List;

/**
 * SubsetMatchingSum - Find a contiguous subarray that sums to a target value.
 * 
 * Given a list of integers and a target sum, this class finds the start and end indices 
 * (inclusive) of the first contiguous subarray that sums to exactly the target value.
 * If no such subarray exists, returns an empty list.
 * 
 * The solution uses a sliding window approach with O(n) time complexity and O(1) space complexity.
 * 
 * Example 1:
 * Input: list = [1, 3, 20, 3, 10, 5], sum = 33
 * Output: [2, 4]
 * Explanation: The subarray from index 2 to 4 sums to 20 + 3 + 10 = 33
 * 
 * Example 2:
 * Input: list = [11, 4, 1, 1, 3, 10, 5], sum = 13
 * Output: [4, 5]
 * Explanation: The subarray from index 4 to 5 sums to 3 + 10 = 13
 * 
 * Example 3:
 * Input: list = [1, 4], sum = 0
 * Output: []
 * Explanation: No contiguous subarray sums to 0
 * 
 * Example 4:
 * Input: list = [2, 4, 6, 8], sum = 10
 * Output: [1, 2]
 * Explanation: The subarray from index 1 to 2 sums to 4 + 6 = 10
 * 
 * Example 5:
 * Input: list = [1, 2, 3, 4, 5], sum = 9
 * Output: [3, 4]
 * Explanation: The subarray from index 1 to 3 sums to 2 + 3 + 4 = 9
 * 
 * Example 6:
 * Input: list = [5, 10, 15, 20], sum = 35
 * Output: [2, 3]
 * Explanation: The subarray from index 2 to 3 sums to 15 + 20 = 35
 * 
 * Example 7:
 * Input: list = [1, 1, 1, 1, 1], sum = 3
 * Output: [0, 2]
 * Explanation: The subarray from index 0 to 2 sums to 1 + 1 + 1 = 3
 *
 * Example 8:
 * Input: list = [2, 2, 2, 2, 2], sum = 10
 * Output: [0, 4]
 * Explanation: The subarray from index 0 to 4 sums to 2 + 2 + 2 + 2 + 2 = 10
 */
public class SubsetMatchingSum {
 public static void main(String[] args) {
    runTest(List.of(1, 3, 20, 3, 10, 5), 33, List.of(2, 4));
    runTest(List.of(11, 4, 1, 1, 3, 10, 5), 13, List.of(4, 5));  
    runTest(List.of(1, 4), 0, List.of());
    runTest(List.of(2, 4, 6, 8), 10, List.of(1, 2));
    runTest(List.of(1, 2, 3, 4, 5), 9, List.of(1, 3));
    runTest(List.of(5, 10, 15, 20), 35, List.of(2, 3));
    runTest(List.of(1, 1, 1, 1, 1), 3, List.of(0, 2));
    runTest(List.of(10, 20, 30), 60, List.of(0, 2));
    runTest(List.of(5, 5, 5, 5), 20, List.of(0, 3));
    runTest(List.of(3, 7, 1, 2, 5, 6), 8, List.of(1, 2));
    runTest(List.of(2, 2, 2, 2, 2), 10, List.of(0, 4));
  }
  
  private static void runTest(
    List<Integer> list,
    int sum,
    List<Integer> expectedResult
  ){
    List<Integer> realResult = solution(list, sum);
    System.out.printf(
      "Correct result for list %s and sum %d? '%s'%nexpected result: %s - current result: %s%n",
      list,
      sum,
      realResult.equals(expectedResult) ? "yes" : "no",
      expectedResult,
      realResult
    );
  }
  
  private static List<Integer> solution(List<Integer> list, int sum){
    int start = 0;
    int end = -1;
    int currentSum = 0;
    for(Integer value: list){
      end++;
      currentSum += value;
      
      if(currentSum < sum) 
        continue;

      while(currentSum > sum && start < end)
        currentSum -= list.get(start++); 
      
      if(currentSum == sum)
        return List.of(start, end);
    }

    return List.of();
  }

}
