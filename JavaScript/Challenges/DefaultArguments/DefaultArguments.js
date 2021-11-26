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
        } else if (element == 'arg') {
            for (i in params) {
                if (params.hasOwnProperty(i)) {
                    result[i] = params[i];
                }
            }
        }
    });
    
    return function(arg) {
        
        let resultArray = [];
        let args = Array.prototype.slice.call(arguments);
        
        if (paramArgumentFunc == 'arg') {
            args.forEach((element, index) => {
                if (isNaN(element)) {
                    return NaN;
                }
                result[index] = element;
            });
        }

        args.forEach((element, index) => {
            result[paramArgumentFunc[index]] = element;
        });
        
        for (i in result) {
            if (result.hasOwnProperty(i)) {
                delete resultArray;
                resultArray.push(result[i]);
            }
        }
        if (arg == undefined && Object.keys(result).length == 0) {
            return NaN;
        }
        return func.apply(this, resultArray);
    }
}
