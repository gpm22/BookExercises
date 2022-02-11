function merge (sea, fresh){
    let result = [];

    while (sea.length !==0 && fresh.length !==0){
        
        let fish="";

        if(sea[0].localeCompare(fresh[0]) < 0){
            fish = sea.shift();
        } else {
            fish = fresh.shift();
        }
        result.push(fish);
    }

    if(sea.length !== 0){
        while(sea.length !==0 ){
            result.push(sea.shift());
        }
    }

    if(fresh.length !== 0){
        while(fresh.length !==0 ){
            result.push(fresh.shift());
        }
    }

    return result;
}


let sea = ["Cod", "Herring", "Marlin"];
let fresh = ["Asp", "Carp", "Ide", "Trout"];

console.log("sea: " +sea);
console.log("fresh: " + fresh);
console.log("fishs ordered: " + merge(sea, fresh));