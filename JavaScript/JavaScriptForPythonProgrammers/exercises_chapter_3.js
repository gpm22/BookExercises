"use strict"

//creation of a class to represents a numeric fraction
class Fraction {
    constructor(num, den) {
        this._num = num;
        this._den = den;
    }

    toString() {
        return `${this.numerator} / ${this.denominator}`;
    }

    get numerator() {
        return this._num;
    }

    get denominator() {
        return this._den;
    }

    set numerator(val) {
        console.log("error cannot set the numerator");
    }

    gcd(m, n) {
        while (m % n != 0) {
            let oldm = m;
            let oldn = n;

            m = oldn;
            n = oldm % oldn;
        }
        return n;
    }

    add(other) {
        let newDen = this.denominator * other.denominator;
        let newNum = this.numerator * other.denominator + other.numerator * this.denominator;
        let common = this.gcd(this.denominator, other.denominator);
        return new Fraction(newNum / common, newDen / common);
    }
    
    sub(other) {
        let newDen = this.denominator * other.denominator;
        let newNum = this.numerator * other.denominator - other.numerator * this.denominator;
        let common = this.gcd(this.denominator, other.denominator);
        return new Fraction(newNum / common, newDen / common);
    }
    
    mul(other) {
        let newDen = this.denominator * other.denominator;
        let newNum = this.numerator * other.numerator;
        let common = this.gcd(newDen, newNum);
        return new Fraction(newNum / common, newDen / common);
    }
    
    div(other) {
        let newDen = this.denominator * other.numerator;
        let newNum = this.numerator * other.denominator;
        let common = this.gcd(newDen, newNum);
        return new Fraction(newNum / common, newDen / common);
    }
    
    
    equals(other) {
        return this.denominator*other.numerator === other.denominator*this.numerator;
    }

}

//Rectangle class with an area method

class Rectangle {
    
    constructor(len, wid) {
        this._len = len;
        this._wid = wid; 
    }
    
    get length(){
        return this._len;
    }
    
    get width(){
        return this._wid;
    }
    
    area(){
        return this.length*this.width;
    }
}


//Rectangle class with an area method in the old js way

function RectangleOldWay(len, wid) {
	this.length = len;
        this.width = wid; 
}

RectangleOldWay.prototype.area = function() {
	return this.length*this.width;
}

//Stack data structure class

class Stack {
        
    constructor(){
        this._stack = [];
    }
    
    push(a){
        this._stack.push(a);
    }
    
    pop(){
        return this._stack.pop();
    }
    
    peek(){
        return this._stack[0];
    }
    
    size(){
        return this._stack.length;
    }
    
    isEmpty(){
        return this._stack.length === 0;
    }
    
    toString(){
        return this._stack.toString();
    }
}



//Queue data structure class

class Queue {
        
    constructor(){
        this._queue = [];
    }
    
    enqueue(a){
        this._queue.push(a);
    }
    
    dequeue(){
        return this._queue.shift();
    }
    
    peek(){
        return this._queue[0];
    }
    
    size(){
        return this._queue.length;
    }
    
    isEmpty(){
        return this._queue.length === 0;
    }
    
    toString(){
        return this._queue.toString();
    }
}


//Class Cat that inherits from Animal.

class Animal{
    
    constructor(name){
        this._name = name;
    }
    
    get name(){
        return "My name is " +this._name;
    }

    speak(){
        writln("general animal doesn't have a sound");
    }
}

class Cat extends Animal{
    
    constructor(name){
        super(name);
        this.numLegs = 4;
    }
    
    speak() {
        console.log("Miau Miau!!!!!!!!!!!");
    }
}

