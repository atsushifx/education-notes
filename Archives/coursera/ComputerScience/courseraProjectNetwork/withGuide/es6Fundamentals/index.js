// Task 1: Introduction

// console.log('Hello, ES6!!!!');
console.log("ES6 fundamentals");

// ---------------------------------------------
// Task 2: Variables (let) and Scoping
/*
let dog = "ダックスフント";
console.log(dog);

dog = "シベリアンハスキー";
console.log(dog);
*/
// Task 2: Execise 1: Fix the code to print 15

// var age = 15;

// for (var age = 1; age <= 10; age++) {
//     console.log(age);
// }

// console.log(age); // Should print 15 instead of 10

// Task 2: Execise 2: Fix the code to print Jack Russell Terrier
/*
let myDog = "Jack Russell Terrier";
let shortLeggies = true;

if (shortLeggies) {
  let myDog = "Welsh Corgie";
  console.log(myDog);
} else {
  let myDog = "Border Collie";
  console.log(myDog);
}

console.log(myDog); // Jack Russell Terrier
*/
// ---------------------------------------------
// Task 3: Variables (const) and Immutability

// TASK 3: Exercise: Refactor the code to use let/const
/*
const painter = {
  name: "Van Gogh",
  nationality: "Dutch",
  paintings: ["The Starry Night", "Irises", "Almond Blossoms"],
};

Object.freeze(painter);
Object.freeze(painter.paintings);
painter.birthDate = "March 30, 1853";
// painter.paintings.push("Something");

console.log(painter); // Should print { name: 'Van Gogh', nationality: 'Dutch', paintings: [ 'The Starry Night', 'Irises', 'Almond Blossoms' ] }
*/
/*
const paintings = ["一面の菜の花", "雪月花"];
paintings.push("萌え萌えキュン");
console.log(paintings);
*/
/*
// challenge factory
function getSquaredValuesFactory(numbers) {
  var functions = [];

  for (let i = 0; i < numbers.length; i++) {
    let n = numbers[i];
    let getSquaredValue = function () {
      return n * n;
    };
    functions.push(getSquaredValue);
  }
  return functions;
}
var numbers = [1, 2, 3, 4];
var squaredValueFns = getSquaredValuesFactory(numbers);
squaredValueFns.forEach((fn) => console.log(fn()));
// console.log(squaredValueFns);
*/
// ---------------------------------------------
// TASK 4: Destructuring
/*
const recipe = {
  name: "Red Lantill dahil",
  timeInminutes: 30,
  ingredients: ["Red Lantill", "cumin", "tarmelic"],
};

function cook({ name, ingredients }) {
  console.log("recipe:" + name);
  console.log(ingredients);
}
// cook(recipe);

// Task 4: Exercise: Extract data with destructuring

const ingredients = {
  //tea: "black",
  milk: "soy",
  //sweetener: "honey",
  spices: ["ginger", "cardamom", "cinnamon", "nutmeg"],
};

// // Destructure the parameters
function prepareChai({ tea = "regular", milk, spices, sweetener = "sugar" }) {
  console.log(
    "Mix the " +
      tea +
      " tea " +
      "with the " +
      spices +
      " in a small pot. " +
      "Add a cup of water and bring to boil. Boil for 2-3 min. " +
      "Add " +
      milk +
      " milk and " +
      sweetener +
      ". " +
      "Simmer for 3 min. Serve masala chai hot or warm!"
  );
}

//prepareChai(ingredients);
const ing2 = ["豚肉", "caldamon", "cumin"];
const [meet, ...spices] = ing2;

console.log("spices:" + spices);
*/
// ---------------------------------------------
// TASK 5: Strings and Interpolation
/*
const language1 = "Japanese";

//console.log(language1.includes("pa"));
console.log("StartWith:" + language1.startsWith("Ja"));
console.log("repeat:", "hello ".repeat(3));
console.log(`I speak ${language1 === "Japanese" ? "日本語" : "none"}`);
*/
// TASK 5: Exercise: Refactor the code to use the ES6 String utility methods
/*
const country = "Bulgaria";
const city = "Sofia";

if (country.startsWith("Bulg")) {
  console.log("The country starts with Bulg");
}

if (city.startsWith("so")) {
  console.log("The name starts with So");
}

if (city.endsWith("a")) {
  console.log("The name ends with a");
}

console.log(`The capital of ${country} is the city of ${city}`);
*/
// ---------------------------------------------
// TASK 6: Arrow functions
/*
const num1 = [1, 2, 3, 4, 5, 6];
const calcDouble = (n) => n * 2;

const dnum1 = num1.map(calcDouble);

console.log("doubled:", dnum1);
*/
// TASK 6: Exercise: Filter out only the prime numbers from the array
/*
const isPrime = (n) => n % 2 === 0;
const primeNumbers = [1, 2, 3, 4, 5, 6].filter(
  isPrime
);
console.log(primeNumbers);
*/
// ---------------------------------------------
// TASK 7: Arrow functions and this

// Task 7: Exercise

function Translator() {
  this.phrase = "good day";
  this.Dictionary = {
    good: "こんにちは",
    day: "良い日ですね",
  };
}

Translator.prototype.getJapanesePhrase = function () {
  return this.phrase
    .split(" ")
    .map((word) => this.Dictionary[word])
    .join(" ");
};

const translator = new Translator();
console.log(translator.getJapanesePhrase());
