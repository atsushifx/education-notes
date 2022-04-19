(function (window) {
    let obj = {};

    obj.dreamOn = function () {
        console.log("I want to see the global scope! Let me out!");
    };
    window.doer = obj;

})(window);

doer.dreamOn();

