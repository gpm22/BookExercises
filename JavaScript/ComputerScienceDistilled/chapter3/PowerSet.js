function power_set(flowers) {
  let fragrances = new Set();
  fragrances.add(new Set());
  flowers.forEach((flower) => {
    let new_fragrances = setCopy(fragrances);
    new_fragrances.forEach((fragrance) => {
      fragrance.add(flower);
    });
    new_fragrances.add(flower);
    fragrances.add(new_fragrances);
  });

  return linearSet(fragrances);
}

function setCopy(fragrances) {
  let result = new Set();

  if(fragrances.size ===0){
      return result;
  }

  fragrances.forEach((element) => {
    element.forEach(element2 => {
        result.add(new Set(element2));
    });
  });

  return result;
}

function linearSet(set){
  let newSet = new Set();
  set.forEach(element => {
      if(element.size > 1){
          element.forEach(element2 => {
              if(element2.size === undefined){
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

let flowers = ["A", "B", "C", "D", "E"];
console.log("flowers: " + flowers);
let set = power_set(flowers);
console.log("fragrances: ");
console.log(set);
console.log("fragrances size: " + set.size);
