function is_arrays_equals(array1, array2) {
  return (
    array1.length === array2.length &&
    array1.every((value, index) => value === array2[index])
  );
}

function remove_nonuniques(lista) {
  for (let i = 0; i < lista.length - 1; ) {
    if (lista[i] === lista[i + 1]) {
      lista.splice(i + 1, 1);
    } else {
      i++;
    }
  }
}

let array1 = [1, 1, 1, 2, 3, 3, 3];
remove_nonuniques(array1);
let final_array1 = [1, 2, 3];
console.log(
  "remove_nonuniques([1, 1, 1, 2, 3, 3, 3]) == [1, 2, 3] ? " +
    (is_arrays_equals(array1, final_array1) ? "Yes" : "No")
);

let array2 = [10, 10, 10, 1, 1, 1, 2, 4, 5];
remove_nonuniques(array2);
let final_array2 = [10, 1, 2, 4, 5];
console.log(
  "remove_nonuniques([10, 10, 10, 1, 1, 1, 2, 4, 5]) == [10, 1, 2, 4, 5] ? " +
    (is_arrays_equals(array2, final_array2) ? "Yes" : "No")
);

let array3 = [1, 2, 3];
remove_nonuniques(array2);
let final_array3 = [1, 2, 3];
console.log(
  "remove_nonuniques([1, 2, 3]) == [1, 2, 3] ? " +
    (is_arrays_equals(array3, final_array3) ? "Yes" : "No")
);

