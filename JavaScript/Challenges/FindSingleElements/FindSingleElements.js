// You are given a sorted array consisting of only
// integers where every element appears exactly twice,
// except for one element which appears exactly once.
// Find this single element that appears only once.

// Examples:
// - nums = [1,1,2,3,3,4,4,8,8] Output: 2
// - nums = [2,3,3,7,7,11,11 ] Output: 2
// - nums = [3,3,7,7,11,11,12 ] Output: 12

function findSingleElement(array) {

    if(array[0] !== array[1]){
        return array[0];
    }

    if(array[array.length -1] !== array[array.length-2]){
        return array[array.length-1];
    }

  for (let i = 1; i < array.length - 1; i++) {
    if (array[i] !== array[i + 1] && array[i] !== array[i - 1]) {
      return array[i];
    }
  }
}

console.log(
  ` ${findSingleElement([1, 1, 2, 3, 3, 4, 4, 8, 8])} == 2 ? - ${
    findSingleElement([1, 1, 2, 3, 3, 4, 4, 8, 8]) === 2
  }`
);
console.log(
  ` ${findSingleElement([2,3,3,7,7,11,11])} == 2 ? - ${
    findSingleElement([2,3,3,7,7,11,11]) === 2
  }`
);
console.log(
  ` ${findSingleElement([3,3,7,7,11,11,12])} == 12 ? - ${
    findSingleElement([3,3,7,7,11,11,12]) === 12
  }`
);
