const undoRedo = object => {
  
  let objectState = [{...object}];
  
  let state = objectState.length-1;
  return {
    set(key, value) {
      if(state < (objectState.length -1)){
       objectState = [{
        ...object
      }];
        state = 0;
      }
      
      object[key] = value;
      objectState.push({
        ...object
      });
      state = objectState.length-1;
    },
    get(key) { return object[key]},
    del(key) {
      if(state < (objectState.length -1)){
        objectState = [{
        ...object
      }];
        state = 0;
      }
      
      delete object[key];
      objectState.push({
        ...object
      });
      state = objectState.length-1;
    },
    undo() {
      if(state < 1){
        throw new Error("There is no operation to undo");
      }
      state -= 1;
      Object.keys(object).forEach((property) => {
            delete object[property];
        });
        Object.keys(objectState[state]).forEach((property) => {
            object[property] = objectState[state][property];
        });
    },
    redo() {
      if(state > (objectState.length-2)){
        throw new Error("There is no operation to redo");
      }
      state += 1;
      Object.keys(object).forEach((property) => {
            delete object[property];
        });
      
        Object.keys(objectState[state]).forEach((property) => {
            object[property] = objectState[state][property];
        });
    }
  };
};