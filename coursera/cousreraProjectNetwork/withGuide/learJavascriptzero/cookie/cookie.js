// data structure
let usualSuspects = [
  {
    name: "pig",
    size: 100,
    eyes: "blue",
    smokes: true,
  },
  {
    name: "cat",
    size: 45,
    eyes: "green",
    smokes: false,
  },
  {
    name: "dog",
    size: 80,
    eyes: "blue",
    smokes: false,
  },
  {
    name: "lizard",
    size: 20,
    eyes: "yellow",
    smokes: false,
  },
  {
    name: "mouse",
    size: 30,
    eyes: "black",
    smokes: true,
  },
  {
    name: "duck",
    size: 60,
    eyes: "yellow",
    smokes: true,
  },
];

let suspects = [];

for (us of usualSuspects) {
  if (us.size > 40) {
    if (us.name.length === 3) {
      if (us.eyes === "blue") {
        if (us.smokes === true) {
          suspects.push(us);
        }
      }
    }
  }
}
