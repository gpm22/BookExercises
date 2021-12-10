function defaultArguments(func, params) {
    
    if (func == undefined) {
        return;
    }
    
    let paramsArray = /\(([^)]+)\)/.exec(func.toString().replace(/\/\/.+?(?=\n|\r|$)|\/\*[\s\S]+?\*\//g, ''));
    
    if (paramsArray == null) {
        return func;
    }
    paramsArray.shift();
    let paramArgumentFunc = paramsArray[0].split(',');
    let result = {};
    
    paramArgumentFunc.forEach((element) => {
        element = element.trim();
        if (params.hasOwnProperty(element)) {
            result[element] = params[element];
        } 
    });
    
    return function(arg) {
        
        let resultArray = [];

        let args = Array.prototype.slice.call(arguments);

        args.forEach((element, index) => {
            result[paramArgumentFunc[index].trim()] = element;
        });
        
        paramArgumentFunc.forEach(element => {
            resultArray.push(result[element.trim()]);
        });

        if (arg == undefined && Object.keys(result).length == 0) {
            return NaN;
        }

        return func.apply(this, resultArray);
    }
}

function add(a, b){
    return a + b;
}

const add_ = defaultArguments(add, {b: 0});

console.log(add_(10));

console.log(add_(10, 5));

function junta(a, b, c){

    return a + " " + b + " " + c;
}

const junta_ = defaultArguments(junta, {c: "tchau", b: "oi"});

console.log(junta_("hey"));

console.log(junta_("au", "pei"));

console.log(junta_("eie", "asas", "asasoa"));