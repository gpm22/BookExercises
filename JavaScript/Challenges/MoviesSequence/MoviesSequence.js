/*
    CHALLENGE:

    You receive a list of movies from a friend and you 
    want to watch everything as soon as possible. The 
    running time of these movies is given by a vector 
    called runningTimes[n], where n is the number of 
    movies, and each movie has a running time between 
    1.01 and 3.00 hours. You want to spend no more than 
    3 hours a day watching movies and you do not want 
    to watch only half of any movie. So, your goal is to
    find the minimum number of days needed to watch all 
    the movies.
*/

const maxSum = 3.0;

const maxMovieValue = 3.0 - 1.01;

function finMinimunNumberOfDaysNLog(runningTimes) {
  let counter = 0;
  const runningTimesFiltered = [];

  runningTimes.forEach((element) => {
    if (element > maxMovieValue) {
      counter++;
    } else {
      runningTimesFiltered.push(element);
    }
  });

  runningTimesFiltered.sort((a, b) => a - b);

  while (runningTimesFiltered.length != 0) {
    if (
      runningTimesFiltered[0] +
        runningTimesFiltered[runningTimesFiltered.length - 1] <=
      maxSum
    ) {
      counter++;
      runningTimesFiltered.pop();
      runningTimesFiltered.shift();
    } else {
      counter++;
      runningTimesFiltered.pop();
    }
  }

  return counter;
}

function finMinimunNumberOfDaysNSquare1(runningTimes) {
  let counter = 0;
  const runningTimesFiltered = [];

  runningTimes.forEach((element) => {
    if (element > maxMovieValue) {
      counter++;
    } else {
      runningTimesFiltered.push(element);
    }
  });

  runningTimesFiltered.sort((a, b) => a - b);

  while (runningTimesFiltered.length != 0) {
    let i = runningTimesFiltered.length - 1;
    let unique = true;
    for (let j = i - 1; j >= 0; j--) {
      if (runningTimesFiltered[i] + runningTimesFiltered[j] <= maxSum) {
        counter++;
        runningTimesFiltered.splice(i, 1);
        runningTimesFiltered.splice(j, 1);
        unique = false;
        break;
      }
    }

    if (unique) {
      counter++;
      runningTimesFiltered.pop();
    }
  }

  return counter;
}

function finMinimunNumberOfDaysNSquare2(runningTimes) {
  let counter = 0;
  const runningTimesFiltered = [];

  runningTimes.forEach((element) => {
    if (element > maxMovieValue) {
      counter++;
    } else {
      runningTimesFiltered.push(element);
    }
  });

  let pairs = createPairs(runningTimesFiltered);

  while (pairs.length != 0) {
    let firstPair = pairs.shift();
    counter++;
    pairs = removePairs(firstPair, pairs);
  }
  return counter;
}

function createPairs(array) {
  const pairs = [];

  for (let i = 0; i < array.length; i++) {
    for (let j = i; j < array.length; j++) {
      if (j == i) {
        pairs.push([i, i, array[i]]);
        continue;
      }

      if (array[i] + array[j] <= maxSum) {
        pairs.push([i, j, array[i] + array[j]]);
      }
    }
  }

  pairs.sort((a, b) => b[2] - a[2]);
  return pairs;
}

function removePairs(pair, array) {
  const newArray = [...array];

  array.forEach((element) => {
    let jump = false;
    for (let i = 0; i < pair.length - 1; i++) {
      for (let j = 0; j < element.length - 1; j++) {
        if (pair[i] === element[j]) {
          newArray.splice(newArray.indexOf(element), 1);
          jump = true;
          break;
        }
      }
      if (jump) {
        break;
      }
    }
  });

  return newArray;
}

function finMinimunNumberOfDaysExponential(runningTimes) {
  let positionsArray = Array.from(runningTimes.keys());
  let combinations = powerSet(positionsArray);
  let counter = 0;

  combinations.shift();
  combinations = combinations.filter(
    (element) =>
      element.reduce((partialSum, a) => partialSum + runningTimes[a], 0) <=
      maxSum
  );

  combinations.sort((a, b) => {
    const sumB = b.reduce((partialSum, c) => partialSum + runningTimes[c], 0);
    const sumA = a.reduce((partialSum, o) => partialSum + runningTimes[o], 0);

    return sumB - sumA;
  });

  while (combinations.length != 0) {
    let firstCombination = combinations.shift();
    counter++;
    combinations = removeCombinations(firstCombination, combinations);
  }

  return counter;
}

function powerSet(array) {
  return array.reduce(
    (subsets, value) => subsets.concat(subsets.map((set) => [value, ...set])),
    [[]]
  );
}

function removeCombinations(combination, array) {
  const newArray = [...array];

  array.forEach((element) => {
    let jump = false;
    for (let i = 0; i < combination.length; i++) {
      for (let j = 0; j < element.length; j++) {
        if (combination[i] === element[j]) {
          newArray.splice(newArray.indexOf(element), 1);
          jump = true;
          break;
        }
      }
      if (jump) {
        break;
      }
    }
  });

  return newArray;
}

const test = (func, array, expectedAnswer) => {
  const startTime = new Date().getTime();
  let answer = func(array);
  const endTime = new Date().getTime();
  console.log(
    func.name +
      ": " +
      answer +
      " - expected answer: " +
      expectedAnswer +
      " - is the right answer? " +
      (answer == expectedAnswer ? "Yes" : "No") +
      " - execution time: " +
      (endTime - startTime) * 1000 +
      " milliseconds"
  );
};

const functions = [
  finMinimunNumberOfDaysNLog,
  finMinimunNumberOfDaysNSquare1,
  finMinimunNumberOfDaysNSquare2,
  finMinimunNumberOfDaysExponential,
];

const exemples = [
  [[1.9, 1.04, 1.25, 2.5, 1.75], 3],
  [new Array(20).fill(3), 20],
  [
    [
      1.42, 1.51, 2.53, 1.76, 2.47, 1.75, 1.27, 2.89, 1.51, 2.87, 1.57, 1.73,
      2.99, 2.24, 2.12, 2.9, 2.01, 1.17, 2.2, 2.34,
    ],
    17,
  ],
  [
    [
      2.15, 1.15, 2.62, 2.39, 1.26, 1.55, 1.24, 2.71, 1.68, 2.87, 1.25, 2.88,
      2.64, 2.07, 1.95, 1.61, 2.83, 1.28, 2.74, 2.7,
    ],
    16,
  ],
  [
    [
      1.51, 2.25, 2.95, 1.52, 1.06, 1.68, 1.87, 1.54, 1.51, 2.22, 1.86, 1.27,
      1.51, 1.48, 2.43, 2.08, 2.02, 2.76, 1.81, 1.68,
    ],
    17,
  ],
  [
    [
      2.03, 2.78, 1.21, 2.23, 1.57, 1.13, 2.39, 1.51, 2.21, 1.72, 2.02, 1.34,
      2.8, 1.83, 2.55, 2, 1.91, 2.17, 1.61, 1.42,
    ],
    16,
  ],
];

exemples.forEach((exemple) => {
  functions.forEach((func) => test(func, exemple[0], exemple[1]));
  console.log(
    "----------------------------------------------------------------"
  );
});

const testLarge = (func, array) => {
  const startTime = new Date().getTime();
  let answer = func(array);
  const endTime = new Date().getTime();
  console.log(
    func.name +
      ": " +
      answer +
      " - execution time: " +
      (endTime - startTime) * 1000 +
      " milliseconds"
  );
};

const largeFunctions = [
  finMinimunNumberOfDaysNLog,
  finMinimunNumberOfDaysNSquare1,
  finMinimunNumberOfDaysNSquare2,
];

const getRandomArray = (N) => Array.from({length: N}, () => Math.round((Math.random() * (3.0 - 1.01) + 1.01)*100)/100);

let N = 1000;
console.log("\nTESTS WITH LARGE INPUTS (N=" + N +")\n");
largeFunctions.forEach((func) => testLarge(func, getRandomArray(N)));
console.log("----------------------------------------------------------------");
