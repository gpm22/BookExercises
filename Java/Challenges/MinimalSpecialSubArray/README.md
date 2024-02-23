# Minimal Special Value of a Sub Array

The complete solution is on [`MinimalSpecialSubArray.java`](https://github.com/gpm22/BookExercises/blob/master/Java/Challenges/MinimalSpecialSubArray/MinimalSpecialSubArray.java);

## Problem

Define the special value of an array $\text{arr}$ with $n$ elements as 
$$\sum_{i=2}^n=\mid\text{arr}_i-\text{arr}_{i-1}\mid$$
Where the first elements is $\text{arr}_1$ and the last $\text{arr}_n$.

Create a function that consumes an array of $n$ integers and an integer `k`, which represents the size of the sub array, and returns the minimal special value between all the possible sub arrays of the given array with size $k$. 

## Solution

Here I present two solutions, one using brute force searching, which runs in factorial time, and another that runs in polynomial time.

### Brute Force Solution

```java
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
```

#### Running time

There is $\binom nk$ sub arrays and each loop iteration in the main method takes $O(k \log k)$, as the sub array is sorted before the calculation. Therefore it runs in $O(k \binom nk \log k )$, which is really horrible.

### Polynomial Solution

```java
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
```

#### Running Time

The outer loop of the main method runs $O(n-k)$ times and each execution of `calculateValueForSubset` takes $O(k)$, therefore it runs in $O(k(n-k))$, which is much better than the brute force solution.

### Comparing Solutions

The brute force solution running time is so bad that running it with an array of 30 elements causes the jvm to halt.

To see how much superior is the polynomial version, I ran 10 times both solutions with random arrays of 25 elements and a random $k$:

| Run                      | Ratio $\text{brute time}/ \text{polynomial time}$ |
| ------------------------ | ------------------------------------------------- |
| 1                        | 1.7587918620528965E-4                             |
| 2                        | 0.06794706004438271                               |
| 3                        | 5.6865646991159675E-5                             |
| 4                        | 9.421971280268826E-6                              |
| 5                        | 1.0655449557728013E-5                             |
| 6                        | 2.85330153202887E-5                               |
| 7                        | 5.367063011594515E-4                              |
| 8                        | 1.9706962498884532E-5                             |
| 9                        | 0.06984069047761392                               |
| 10                       | 3.089020501812521E-5                              |
| Average                  | 0.013865640926002782                              |
| Average without ouliners | 8.686587380312E-4                                 |

It is possible to observe how much faster the polynomial solution is than the brute force one.
