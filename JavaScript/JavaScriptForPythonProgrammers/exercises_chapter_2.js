
function sumList(list){
    
    //Function that sums all numbers of a list
    let total = 0;
    for(let i of list){
        total += i;
    }
    return total;
}

function maxNum(lista){

    //Function that return the biggest number of a list
    let maxNum = Number.MIN_VALUE;
    for(let i of lista){
        if(i>maxNum){
            maxNum = i;
        }
    }
    
    return maxNum;
}

function arrayOfNPrimeNumbers(n){

    //Function thar constructs an Array containing the first n prime numbers
    let arrayOfNPrimeNumbers = [3];
    let i = 1;
    let j = 5;
    let m = n-1;
    let primo = true;
    while(i<m){
        let a = 0;
        for(let k of arrayOfNPrimeNumbers){
            if(j!=k && j%k == 0){
                primo = false;
                break;
            }
            a++;
        }
        
        if(a == arrayOfNPrimeNumbers.length){
            primo = true;
        }
        
        if(primo){
            arrayOfNPrimeNumbers.push(j);
            i++;
        }
        
        j+=2;
    }
    arrayOfNPrimeNumbers.unshift(2);
    return arrayOfNPrimeNumbers;
}


function removeLetterofString(stringIn, letter){

    //Function that removes a letter from a string
    let stringOut="";
    for(let i of stringIn){
        if(i != letter){
            stringOut += i;
        }
    }
    return stringOut;
}

function reverseString(stringIn){

    //Function that reverses a String
    let stringOut = "";
    for(let i = stringIn.length-1; i >= 0; i--){
        stringOut += stringIn[i];
    }
    return stringOut;
}

function numberOfDigits(num){

    //Function that returns the number of digits of an Integer number
    let numString = "" + num;
    return numString.length;
}

function isPalindrome(stringIn){
    //function that verifies if a word is a palindrome;
    return (stringIn === reverseString(stringIn));
}





