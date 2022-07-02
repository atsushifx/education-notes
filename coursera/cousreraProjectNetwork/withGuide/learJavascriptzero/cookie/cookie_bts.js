const display = document.querySelector("#display");
const warningsContainer = document.querySelector("#warningsContainer");
const successString = "Congratulations, you discovered the culprit! The cookies are now safe. Or so you believe...";
const failString    = "Oh no, you locked up an innocent citizen and the cookie culprit is still at large. Change your suspects array and reload the browser to rewind and try again!";
const expectedUsualSuspects = [
    {name: "pig",    size: 100, eyes: "blue",   smokes: true},
    {name: "cat",    size: 45,  eyes: "green",  smokes: false},
    {name: "dog",    size: 80,  eyes: "blue",   smokes: false},
    {name: "lizard", size: 30,  eyes: "yellow", smokes: false},
    {name: "mouse",  size: 20,  eyes: "black",  smokes: true},
    {name: "duck",   size: 60,  eyes: "yellow", smokes: true}
];

/*if (typeof sizes !== 'undefined') {
    if(sizes.length) {
        let newElement = document.createElement("div");
        newElement.innerText = "Sizes:";
        newElement.className = "badge";
        display.append(newElement);
        sizes.forEach(size => {
            let newElement = document.createElement("div");
            newElement.innerText = size;
            newElement.className = "badge";
            display.append(newElement);
        });    
    }
}*/

let cards = [];
let generateCards = () => {
    let title = document.createElement("div");
    title.innerHTML = "The Usual Suspects";
    title.style.width = "100%";
    title.style.fontFamily = "compacta";
    title.style.fontSize = "14vh";
    title.style.textAlign = "center";
    title.className = "title";
    display.append(title);
    let cardContainer = document.createElement("div");
    display.append(cardContainer);
    cardContainer.style.display = "flex";
    cardContainer.style.flexWrap = "wrap";
    usualSuspects.forEach(suspect => {
        let card = document.createElement("div");
        cards.push(card);
        card.suspect = suspect;
        card.className = "card";
        card.style.width = "300px";
        card.style.marginRight = "20px";
        cardContainer.append(card);
        let cardImage = document.createElement("div");
        cardImage.className = "card-image";
        card.append(cardImage);
        let img = document.createElement("img");
        img.src = suspect.name + ".svg";
        img.className = "animal_image";
        img.style.width = "200px";
        img.style.marginLeft = "auto";
        img.style.marginRight = "auto";
        card.img = img;
        cardImage.append(img);
        let cardTitle = document.createElement("div");
        cardTitle.innerText = suspect.name;
        cardTitle.className = "card-title";
        cardTitle.style.textAlign = "center";
        card.cardTitle = cardTitle;
        card.append(cardTitle);
        let cardContent = document.createElement("div");
        cardContent.innerHTML = "Size: " + suspect.size + "<br>Eyes: " + suspect.eyes;
        cardContent.className = "card-content";
        card.append(cardContent);
    });
}
let showEndScreen = () => {
    let setDisplayContent = (imgURL, caption) => {
        display.innerHTML = "<img src='" + imgURL + "'' class='end-screen-image' /><div class='end-screen-text'>" + caption + "</div>";
    }
    if (suspects[0].name.toLowerCase() == "pig") {
        setDisplayContent("pig_locked.svg", successString);
    } else if (suspects[0].name.toLowerCase() === "cat") {
        setDisplayContent("cat_locked.svg", failString);
    } else if (suspects[0].name.toLowerCase() === "dog") {
        setDisplayContent("dog_locked.svg", failString);
    } else if (suspects[0].name.toLowerCase() === "lizard") {
        setDisplayContent("lizard_locked.svg", failString);
    } else if (suspects[0].name.toLowerCase() === "mouse") {
        setDisplayContent("mouse_locked.svg", failString);
    } else if (suspects[0].name.toLowerCase() === "duck") {
        setDisplayContent("duck_locked.svg", failString);
    } else {
        console.error("Unable to recognize suspect's name as pig, cat, dog, lizard, mouse or duck", suspect);
    }
}
let generateClues = () => {
    let clues = new Array(4);
    ["The suspect had to have been more than 40cm tall!",
        "The suspect only types 3 letters when typing their name!",
        "We heard the suspect had blue eyes!",
        "We found cigarette ash by the cookie jar!"].forEach((clueString, index) => {
        clues[index] = document.createElement("div");
        clues[index].className = "bubble bubble-bottom-left";
        clues[index].innerText = clueString;
    });

    display.append(clues[0]);
    if (typeof suspects === "undefined") return null;
    if (!suspects.find((suspect) => suspect.size <= 40)) {
        display.append(clues[1]);
        if (!suspects.find((suspect) => suspect.name.length !== 3)) {
            display.append(clues[2]);
            if (!suspects.find((suspect) => suspect.eyes.toLowerCase() !== "blue")) {
                display.append(clues[3]);
            }
        }
    };
};
let filterCardsGrey = () => {
    cards.forEach((card) => {
        if(!suspects.find((suspect) => {
            return suspect.name === card.suspect.name;
        })) {
            card.img.style.filter = "grayscale(100%)";
            card.cardTitle.style.textDecoration = "line-through";
        }
    });
};

if (typeof suspects !== 'undefined' && suspects.length) {
    if (suspects.length === 1) {
        showEndScreen();
    } else if (suspects.length > 1) {
        if (typeof usualSuspects !== 'undefined' && usualSuspects.length) {
            if (usualSuspects.length) {
                generateCards();
                filterCardsGrey();
                generateClues();
            } else {
                console.error("Is usualSuspects an array?", usualSuspects);
            }
        } else if (typeof suspects !== 'undefined' && suspects.length) {
            console.error("Unable to find the usualSuspects array. Did you accidently remove it when creating the suspects array?");
        }
    } else if (suspects.length === 0) {
        console.error("Suspects array is empty", suspects);
        generateWarning("Your suspects array is empty.<br>This can often happen if you mistyped a variable in a comparison.<br>For example, if you type if(suspect.variableThatDoesNotExist > 0) {<br>doSomething()<br>}<br>It will never doSomething() because the variable that does not exist will never be greater than zero");
    } else {
        console.error("Problem with suspects Array", suspects);
    }
} else {
    if (typeof usualSuspects !== 'undefined' && usualSuspects.length) {
        generateCards();
        generateClues();
    } else {
        console.log("no usualSuspects variable detected");
    }
}

let checkUsualSuspectsVariable = () => {
    if (typeof usualSuspects !== 'undefined') {
        if (usualSuspects.length == 'undefined') {
            generateWarning("Other Rhyme users encountering an error found this helpful: is your usualSuspects variable an array? Make sure it has [ and ] around it.");
        } else if (usualSuspects.length != expectedUsualSuspects.length) {
            generateWarning("Other Rhyme users encountering an error found this helpful: your usualSuspects variable has " + usualSuspects.length + " entries. If you're following the video, it should have " + expectedUsualSuspects.length + " entries");
        } else {
            for (let usualSuspect of usualSuspects) {
                if (usualSuspect.size == "undefined") {
                    generateWarning("Other Rhyme users encountering an error found this helpful: this line is missing a 'size' property: " + JSON.stringify(usualSuspect));
                } else {
                    if (typeof usualSuspect.size != 'number') {
                        generateWarning("Other Rhyme users encountering an error found this helpful: the size in this line is not recognized as a number. Did you accidently put quotation marks around it? " + JSON.stringify(usualSuspect.size));
                    } else {
                        if (usualSuspect.size !== 100 && usualSuspect.size !== 45 && usualSuspect.size !== 80 && usualSuspect.size !==30 && usualSuspect.size !== 20 && usualSuspect.size !== 60) {
                            generateWarning("Other Rhyme users encountering an error found this helpful: the size number " + usualSuspect.size + " was used. This is not one of the expected numbers");
                        }
                    }
                }
                
                if (usualSuspect.eyes == "undefined") {
                    generateWarning("Other Rhyme users encountering an error found this helpful: this line is missing an 'eyes' property: " + JSON.stringify(usualSuspect));
                }  else {
                    if (usualSuspect.eyes !== "blue" && usualSuspect.eyes !== "green" && usualSuspect.eyes !== "yellow" && usualSuspect.eyes !== "black") {
                        generateWarning("Other Rhyme users encountering an error found this helpful: the eye color " + usualSuspect.eyes + " was not recognized as one of the expected eye colors.");
                    }
                }
                
                if (usualSuspect.smokes == "undefined") {
                    generateWarning("Other Rhyme users encountering an error found this helpful: this line is missing a 'smokes' property: " + JSON.stringify(usualSuspect));
                } else {
                    if (typeof usualSuspect.smokes != 'boolean') {
                        if (typeof usualSuspect.smokes == 'string') {
                            generateWarning("Other Rhyme users encountering an error found this helpful: the smokes property " + JSON.stringify(usualSuspect.smokes) + " was not recognized as a true/false value. Did you accidently add quotation marks?");
                        } else {
                            generateWarning("Other Rhyme users encountering an error found this helpful: the smokes property " + JSON.stringify(usualSuspect.smokes) + " was not recognized as a true/false value.");
                        }
                    }
                }

                console.log(usualSuspect.name);
                if (usualSuspect.name == "undefined") {
                    generateWarning("Other Rhyme users encountering an error found this helpful: this line is missing a 'name' property: " + JSON.stringify(usualSuspect));
                } else if (usualSuspect.name != "pig" && usualSuspect.name != "cat" && usualSuspect.name != "dog" && usualSuspect.name != "lizard" && usualSuspect.name != "mouse" && usualSuspect.name !== "duck") {
                    generateWarning("Other Rhyme users encountering an error found this helpful: the name used in " + usualSuspect.name + " was not one of the expected names, pig, cat, dog, lizard, mouse or duck.")
                }
            }
        }
    }
}
let generateWarning = (warningString) => {
    console.log("generating warning", warningString);
    let newWarning = document.createElement("div");
    newWarning.innerHTML = warningString;
    newWarning.className = "warning";
    warningsContainer.append(newWarning);
}

checkUsualSuspectsVariable();