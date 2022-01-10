function selection_sort(list) {
  for (let current = 0; current < list.length -1 ; current++) {
    let smallest = current;
    for (let i = current; i < list.length; i++) {
      if (list[i] < list[smallest]) {
        smallest = i;
      }
    }
    [list[current], list[smallest]] = [list[smallest], list[current]];
  }
}

var exempleList = [10, 11, 12, 9, 5, 3, 0, 1, 11];

console.log("original list: " + exempleList);
selection_sort(exempleList);
console.log("sorted list: " + exempleList);
