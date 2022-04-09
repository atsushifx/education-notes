let suspects = [];

for (let citizen of citizens) {
    suspects.push(citizen);
}

console.log("The first citizen off the list is ", citizens[0].firstName, citizens[0].age, citizens[0].height, citizens[0].smokes);
console.log(suspects);