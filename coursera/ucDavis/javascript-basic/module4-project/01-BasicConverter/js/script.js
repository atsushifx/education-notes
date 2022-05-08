// basic mile to kilometer converer
"use strict";

(function() {

    document.getElementById('convert').addEventListener('submit', (function(event) {
        const MILE_TO_KILO = 1.609344;
        event.preventDefault();
        const elAnswer = document.getElementById('answer');

        const distMile = parseFloat(document.getElementById('distance').value);
        console.log('Mile:', distMile);
        if (!distMile && (distMile != 0)) {
            console.log(distMile, "error in input");

            elAnswer.innerHTML = '<h2 class="error">Error : please provide numbers</h2>';
        } else {
            const distKilo = (distMile * MILE_TO_KILO).toFixed(3);
            elAnswer.innerHTML = `<h2>${distMile} miles convert to ${distKilo} kilometers</h2>`;
        }

    }));
}());