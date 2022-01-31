function knapsack(items, maxWeight) {
  let bestValue = 0;
  let bestCandidate = new Set("");
  powerSet(items).forEach((item) => {
    if (totalWeight(item) <= maxWeight) {
      if (salesValue(item) > bestValue) {
        bestValue = salesValue(item);
        bestCandidate = item;
      }
    }
  });

  return bestCandidate;
}

function powerSet(items) {
  let powerSet = new Set();
  powerSet.add(new Set());
  items.forEach((item) => {
    let newSubSet = setCopy(powerSet);
    newSubSet.forEach((subSet) => {
      subSet.add(item);
    });
    newSubSet.add(item);
    powerSet.add(newSubSet);
  });
  return linearSet(powerSet);
}

function linearSet(set) {
  let newSet = new Set();
  set.forEach((element) => {
    if (element.size > 1) {
      element.forEach((element2) => {
        if (element2.size === undefined) {
          newSet.add(new Set([element2]));
        } else {
          newSet.add(element2);
        }
      });
    } else {
      newSet.add(element);
    }
  });
  return newSet;
}

function setCopy(set) {
  let result = new Set();

  if (set.size === 0) {
    return result;
  }

  set.forEach((subSet) => {
    subSet.forEach((subSet2) => {
      result.add(new Set([subSet2]));
    });
  });

  return result;
}

function totalWeight(items) {
  if (items.size === 0) {
    return 0;
  }
  let result = 0;

  Array.from(items).forEach((item) => {
    result += item.weight;
  });
  return result;
}

function salesValue(items) {
  if (items.size === 0) {
    return 0;
  }
  let result = 0;

  Array.from(items).forEach((item) => {
    result += item.value;
  });
  return result;
}

function createItem(name, weight, value) {
  return {
    name: name,
    weight: weight,
    value: value,
  };
}

let items = [
  createItem("sacola", 0.01, 15),
  createItem("amarela", 0.1, 10),
  createItem("azul", 0.1, 10),
  createItem("vermelho", 0.1, 10),
];

console.log("items:");
new Set(items).forEach((item) => console.log(item));
console.log("power set:");
console.log(powerSet(items));
console.log("sacola com no máximo 10:");
knapsack(items, 10).forEach((item) => console.log(item));
