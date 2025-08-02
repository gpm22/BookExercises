# Array Equalization with Minimum Swap Cost

You are given two arrays `A` and `B`, each of length `n`. You can perform the following operation any number of times:

- Choose indices `i` (from `A`) and `j` (from `B`), then swap `A[i]` with `B[j]`.
- The cost of this operation is `min(A[i], B[j])`.

Your goal is to make the two arrays the same, which means that when sorted their elements are in the same order.

Return the minimum total cost required to achieve this, or `-1` if it's impossible.

## Examples

### Example 1

**Input**: A = [5, 7, 7, 7], B = [1, 5, 1, 7]
**Output**: 1

#### Explanation

Swap `A[1]` (value = 7) with `B[0]` (value = 1).
The cost is `min(7, 1) = 1`.
After swapping, the arrays become:
`A = [5, 1, 7, 7], B = [7, 5, 1, 7].`
When sorted, both arrays become `[1, 5, 7, 7]`, so they are multiset equivalent.
The total cost is 1.

### Example 2

**Input**: `A = [7, 8, 6, 2], B = [8, 7, 9, 2]`
**Output**: `-1`

#### Explanation

After checking all possible swaps, it's impossible to make the two arrays multiset equivalent.

## Constraints

- `A.length == B.length`
- `1 <= n <= 10^5`
- `1 <= A[i], B[i] <= 10^9`