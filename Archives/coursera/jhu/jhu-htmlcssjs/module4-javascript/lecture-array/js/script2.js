let greeter2 = (function () {
    let f = {}
    f.name = "Atsushi";
    f.sayHi = function () {
        console.log("Hi! " + f.name);
    }
    return f;
})();
