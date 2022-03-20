console.log("Hello world in script.js")
function makeMultiplier(multiplier) {
  var myFunFunc = function (x) {
    return multiplier * x;
  };

  return myFunFunc;
}

var operation = makeMultiplier(10);
console.log(operation(10));
