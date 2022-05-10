
function is_arrays_equals(array1, array2) {
  return (
    array1.length === array2.length &&
    array1.every((value, index) => value === array2[index])
  );
}

function merge_sort(array) {
  if (array.length == 1) {
    return array;
  }

  let meio = Math.floor(array.length / 2);

  let left = merge_sort(array.slice(0, meio));
  let right = merge_sort(array.slice(meio));
  let final = [];

  while (left.length > 0 && right.length > 0) {
    if (left[0] > right[0]) {
      final.push(right[0]);
      right.shift();
    } else {
      final.push(left[0]);
      left.shift();
    }
  }

  if (left.length > 0) {
    final = final.concat(left);
  }

  if (right.length > 0) {
    final = final.concat(right);
  }

  return final;
}

console.log(
  "merge_sort([10, 1, 2, 4, 5]) == [1, 2, 4, 5, 10] ? " +
    (is_arrays_equals(merge_sort([10, 1, 2, 4, 5]), [1, 2, 4, 5, 10])
      ? "Yes"
      : "No")
);

console.log(
  "merge_sort([1, 3, 2,]) == [1, 2, 3] ? " +
    (is_arrays_equals(merge_sort([1, 3, 2]), [1, 2, 3]) ? "Yes" : "No")
);

console.log(
    "merge_sort([1, 2, 3]) == [1, 2, 3] ? " +
      (is_arrays_equals(merge_sort([1, 2, 3]), [1, 2, 3]) ? "Yes" : "No")
  );
