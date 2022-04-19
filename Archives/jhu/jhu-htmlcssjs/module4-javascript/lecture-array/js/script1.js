// clojure by js
/*
function makeGreetings(greetings) {
    function printGreetings() {
        console.log(greetings);
    }
    printGreetings();
    return function (name) {
        return greetings + ", " + name + "!";
    };
}
let g1 = makeGreetings("Hello");
console.log(g1("world"));

let g2 = makeGreetings("good night");
console.log(g2("Japan"));
*/
// namespaces
let greeter1 = (function () {
    let f = {}
    f.name = "Fubuki";
    f.sayHello = function () {
        console.log("Hello " + greeter1.name);
    }
    return f;
})()

