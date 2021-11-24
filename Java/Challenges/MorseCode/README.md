# Morse Code Translator

In this project a morse code translator was created based on the codes in this [image](https://upload.wikimedia.org/wikipedia/commons/c/ca/Morse_code_tree3.png);

If you want to see the possible letter from a certain code you can use ? instead of . or - like in the following examples:

```java
MorseCode.possibilities(".");
// return [E]

MorseCode.possibilities("?");
// return [E, T]

 MorseCode.possibilities("????");
// return [H, V, F, Ü, L, Ä, P, J, B, X, C, Y, Z, Q, Ö, CH]
```

