function formatMoney(amount, decimalCount = 2, decimal = ".", thousands = ",") {
    try {
      decimalCount = Math.abs(decimalCount);
      decimalCount = isNaN(decimalCount) ? 2 : decimalCount;
  
      const negativeSign = amount < 0 ? "-" : "";
  
      let i = parseInt(amount = Math.abs(Number(amount) || 0).toFixed(decimalCount)).toString();
      let j = (i.length > 3) ? i.length % 3 : 0;
  
      return negativeSign + (j ? i.substr(0, j) + thousands : '') + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousands) + (decimalCount ? decimal + Math.abs(amount - i).toFixed(decimalCount).slice(2) : "");
    } catch (e) {
      console.log(e)
    }
};

const display = document.querySelector("#display");
const table = document.querySelector("table");
const cluesContainer = document.querySelector("#cluesContainer");

let citizens = new Array(5001);
let today = new Date();
let rng = new Chance(today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate());

let generateCitizen = (citizenId) => {
    return {
        citizenId,
        firstName: rng.first(),
        lastName: rng.last(),
        age: rng.age({type: 'adult'}),
        height: rng.integer({min: 140, max: 200}),
        smokes: rng.bool(),
        bankBalance: rng.integer({min:10, max: 1000000})
    }
}

let correctAnswer   = generateCitizen(rng.integer({min:10, max: 4500}));
let isAgeCorrect    = (age) => {
    return correctAnswer.age > 35 ? age > 35 : age <= 35;
}
let isHeightCorrect = (height) => {
    return correctAnswer.height > 170 ? height > 170 : height <= 170;
}
let generateClues = () => {
    let clues = new Array(4);
    ["The suspect types " + correctAnswer.firstName.length + " letters when typing their first name!",
        correctAnswer.age > 35 ? "The suspect is more than 35 years old" : "The suspect is at most 35 years old",
        correctAnswer.height > 170 ? "The suspect had to have been more than 170cm tall" : "the suspect is at most 170cm tall",
        correctAnswer.smokes ? "We found fresh ciggarette ash by the cookie jar!" : "We're confident the suspect doesn't smoke"].forEach((clueString, index) => {
        clues[index] = document.createElement("div");
        clues[index].className = "bubble bubble-bottom-left";
        clues[index].innerText = clueString;
        cluesContainer.append(clues[index]);
    });
}

for (let i = 0; i < citizens.length; i++) {
    let citizenEntered = false;
    while(!citizenEntered) {
        citizens[i] = generateCitizen(i);
        citizenEntered = true;
        if (citizens[i].firstName == correctAnswer.firstName) citizenEntered = false;
        if (citizens[i].firstName.length == correctAnswer.firstName.length &&
                        isAgeCorrect(citizens[i].age) &&
                        isHeightCorrect(citizens[i].height) &&
                        citizens[i].smokes == correctAnswer.smokes) {
            citizenEntered = false;
        }
    }
    window["citizens"] = citizens;
}

citizens[correctAnswer.citizenId] = correctAnswer;

let generateTableRows = (tableData) => {
    tableData.forEach((rowData) => {
        let newRow        = document.createElement("tr");
        let idCell        = document.createElement("td");
        let firstNameCell   = document.createElement("td");
        let lastNameCell    = document.createElement("td");
        let ageCell         = document.createElement("td");
        let heightCell      = document.createElement("td");
        let smokesCell      = document.createElement("td");
        let bankBalanceCell = document.createElement("td");
        idCell.innerText          = rowData.citizenId + "";
        firstNameCell.innerText   = rowData.firstName + "";
        lastNameCell.innerText    = rowData.lastName + "";
        ageCell.innerText         = rowData.age + "";
        heightCell.innerText      = rowData.height + "";
        smokesCell.innerText      = rowData.smokes + "";
        bankBalanceCell.innerText = "$" + formatMoney(rowData.bankBalance, 0);
        newRow.append(idCell);
        newRow.append(firstNameCell);
        newRow.append(lastNameCell);
        newRow.append(ageCell);
        newRow.append(heightCell);
        newRow.append(smokesCell);
        newRow.append(bankBalanceCell);
        table.append(newRow);
    });
}

generateClues();

window["buildTables"] = () => {
    if (typeof suspects !== "undefined" && suspects !== null) {
        generateTableRows(suspects);
    } else {
        generateTableRows(citizens);
    }    
}

correctAnswer = null;

let sortedCitizens = JSON.parse(JSON.stringify(citizens));
sortedCitizens.sort((a, b) => a.bankBalance-b.bankBalance);
let clientJSON = JSON.stringify(sortedCitizens[2500]);