// Lecture 47 : by value & by refference

//　console.log("Hello world in script.js")

/*
var a = "Hello world";
var b = a;

console.log("var a:" + a)
console.log("var b:" + b)

b = "good night"
console.log("updated a:" + a)
console.log("updated b:" + b)
*/

/*
var a = { x: 7 };
var b = a;

console.log(a)
console.log(b)

b.x = 5
console.log("after b.x update")

console.log(a)
console.log(b)
*/

// call function: onject parameter refrence
function changeObject(objValue) {
  console.log("in changeObject...");
  console.log("before:");
  console.log(objValue);

  objValue.x = 5;
  console.log("after:");
  console.log(objValue);
}

value = { x: 7 };
changeObject(value); // objValue = value
console.log("after changeObject, orig value:");
console.log(value);
