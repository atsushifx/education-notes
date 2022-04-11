let suspects = [];

for (let c of citizens) {
  if (c.firstName.length === 8) {
    if (c.age <= 35) {
      if (c.height <= 170) {
        if (c.smokes === false) {
          suspects.push(c);
        }
      }
    }
  }
}

console.log(
  "The first citizen off the list is ",
  citizens[0].firstName,
  citizens[0].age,
  citizens[0].height,
  citizens[0].smokes
);

let thief = citizens[2445];
console.log(thief);
let clients = [];
for (let c of citizens) {
  if (c.bankBalance > thief.bankBalance) {
    clients.push(c);
  }
}
