function power_set(flowers) {
  let fragrances = new Set();
  fragrances.add(new Set());
  flowers.forEach((flower) => {
    let new_fragrances = fragranceCopy(fragrances);
    new_fragrances.forEach((fragrance) => {
      fragrance.add(flower);
    });
    new_fragrances.add(flower);
    fragrances.add(new_fragrances);
  });

  return fragrances;
}

function fragranceCopy(fragrances) {
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

let flowers = ["A", "B", "C", "D", "E"];
console.log("flowers: " + flowers);
let set = power_set(flowers);
console.log("fragrances: ");
console.log(set);
let size = 1;
set.forEach((element) => {
  size += element.size;
});
console.log("fragrances size: " + size);
