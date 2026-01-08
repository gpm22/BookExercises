# `TwoSum`

## **Problem Description:**

You are given an array of integers `nums` and an integer `target`. Your task is to find the indices of the two numbers in the array that add up to the target value.

You may assume:

- Each input has exactly one solution.
- You cannot use the same element twice.
- The order of the returned indices does not matter.

## **Example Test Cases:**

1. Input: `nums = [2, 7, 11, 15]`, `target = 9`
   Output: `[0, 1]`
   Explanation: `nums[0] + nums[1] = 2 + 7 = 9`
2. Input: `nums = [3, 2, 4]`, `target = 6`
   Output: `[1, 2]`
   Explanation: `nums[1] + nums[2] = 2 + 4 = 6`
3. Input: `nums = [3, 3]`, `target = 6`
   Output: `[0, 1]`
   Explanation: The same element cannot be used twice, but two separate elements with the same value are allowed.
4. Input: `nums = [1, 5, 3, 7]`, `target = 10`
   Output: `[2, 3]`
   Explanation: `nums[2] + nums[3] = 3 + 7 = 10`
