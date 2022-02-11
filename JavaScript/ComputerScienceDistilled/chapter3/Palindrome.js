function palindrome(word){
    if (word.length <= 1){
        return true;
    }

    if(word.charAt(0) !== word.charAt(word.length-1)){
        return false;
    }

    return palindrome(word.substring(1, word.length-1));
}

function stringPalindrome(word){
    return "Is " + word + " a palindrome? " + (palindrome(word)? "Yes": "No");
}

let words = ["racecar", "ada", "rome"];

words.forEach(word => console.log(stringPalindrome(word)));