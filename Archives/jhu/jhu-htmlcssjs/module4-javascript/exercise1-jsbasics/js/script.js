// exercise1: javascript
function Dog(name) {
    this.name = name;
}

Dog.prototype.bark = function () {
    console.log(this.name + " likes barking! Bark!");
}

var max = new Dog("Max", "Buddy");
max.bark();
