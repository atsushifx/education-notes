// advanced distance converter
// mile->kilo , kill->mile 切り替え可
"use strict"

(function() {
    let convertType = "m2k";
    // element
    const elH1 = document.querySelector('h1');
    const elIntro = document.querySelector('p');
    const elForm = document.querySelector('#convert');
    const elAnswer = document.querySelector('#answer');

    // キー入力
    document.addEventListener('keydown', function(event) {
        const key = event.code;
        // event.preventDefault();

        // console.log("Key:", key);
        if (key === "KeyK") {
            event.preventDefault();
            convertType = "k2m";
            elH1.innerText = "Kilometers to Miles converter";
            elIntro.innerText = "input number kilometers and click convert the distance of miles.";
        } else if (key === "KeyM") {
            event.preventDefault();
            convertType = "m2k";
            elH1.innerText = "Miles to Kilometers converter";
            elIntro.innerText = "input number miles and click convert the distance of kiloneters.;"
        }

    });

    elForm.addEventListener('submit', function(event) {
        const MILE_TO_KILO = 1.609344;
        const KILO_TO_MILE = 0.621371192;
        event.preventDefault();

        const distance = parseFloat(document.getElementById('distance').value);
        if (!distance && (distance != 0)) {
            elAnswer.innerHTML = '<h2 class="error">Error : please provide numbers</h2>';
            return; // break from funvtion
        }

        if (convertType === "k2m") {
            const distMile = (distance * KILO_TO_MILE).toFixed(3);
            elAnswer.innerHTML = `<h2>${distance} kilometers convert to ${distMile} miles</h2>`;
        } else if (convertType === "m2k") {
            const distKilo = (distance * MILE_TO_KILO).toFixed(3);
            elAnswer.innerHTML = `<h2>${distance} miles convert to ${distKilo} kilometers</h2>`;
        }
    });

}());