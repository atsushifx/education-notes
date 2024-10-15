let usualSuspects = [
    {name: "pig",    size: 100, eyes: "blue",   smokes: true},
    {name: "cat",    size: 45,  eyes: "green",  smokes: false},
    {name: "dog",    size: 80,  eyes: "blue",   smokes: false},
    {name: "lizard", size: 30,  eyes: "yellow", smokes: false},
    {name: "mouse",  size: 20,  eyes: "black",  smokes: true},
    {name: "duck",   size: 60,  eyes: "yellow", smokes: true}
];
 
let suspects = [];
for (let suspect of usualSuspects) {
    if (suspect.size > 40) {
        if (suspect.name.length === 3) {
            if (suspect.eyes.toLowerCase() === "blue") {
                if (suspect.smokes) {
                    suspects.push(suspect);
                }
            }
        }
    } 
}