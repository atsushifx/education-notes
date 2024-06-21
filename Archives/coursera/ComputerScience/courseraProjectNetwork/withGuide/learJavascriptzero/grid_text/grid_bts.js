const NUM_ROWS    = 20;
const NUM_COLUMNS = 20;
const gridContainer = document.querySelector("#gridContainer");
const brush = document.createElement("img");
const reloadButton = document.querySelector("#reloadButton");
reloadButton.addEventListener("click", ()=>window.location.reload(false));
brush.id = "brush";
brush.src = "paint.svg";
brush.style.width  = "90%";
brush.style.height = "90%";

let squares = Array(NUM_ROWS).fill().map(()=>Array(NUM_COLUMNS).fill())
let columnsString = "";
let rowsString = "";
let currentRow = 0;
let currentColumn = 0;

for(let i = 0; i < NUM_COLUMNS; i++) {
    columnsString += " 1fr";
}
for(let i = 0; i < NUM_ROWS; i++) {
    rowsString += " 1fr";
}

gridContainer.style.gridTemplateColumns = columnsString;
gridContainer.style.gridTemplateRows = rowsString;

for(let i = 0; i < NUM_ROWS; i++) {
    for(let j = 0; j < NUM_COLUMNS; j++) {
        let newSquare = document.createElement("div");
        newSquare.className = "square";
        newSquare.style.height = "30px";
        gridContainer.append(newSquare);
        squares[i][j] = newSquare;
        newSquare.innerText = i + "," + j;
        newSquare.paint = () => {
            setStartSquare(i, j);
        };
    }
}


let setStartSquare = (rowNum, columnNum) => {
    currentRow = rowNum;
    currentColumn = columnNum;
    squares[rowNum][columnNum].innerText = "";
    squares[rowNum][columnNum].append(brush);
    squares[rowNum][columnNum].style.backgroundColor = "green";    
}

let processChange = (change) => {
    squares[currentRow][currentColumn].removeChild(brush);
    change();
    squares[currentRow][currentColumn].innerText = "";
    squares[currentRow][currentColumn].append(brush);
    squares[currentRow][currentColumn].style.backgroundColor = "green";
};
let moveLeft = () => {
    if(currentColumn-1 >= 0) {
        processChange(()=>currentColumn--);
    }
};
let moveRight = () => {
    if(currentColumn+1 < NUM_COLUMNS) {
        processChange(()=>currentColumn++);
    }
};
let moveUp = () => {
    if(currentRow-1 >= 0) {
        processChange(()=>currentRow--);
    }
};
let moveDown = () => {
    if(currentRow+1 < NUM_ROWS) {
        processChange(()=>currentRow++);
    }
};

setStartSquare(0,0);




let grid_bts = JSON.parse(localStorage.getItem("grid_bts.json"));
if (grid_bts) {
    if (Date.now > (grid_bts.timestamp + 259200000)) {
        grid_bts = null;
    } else {
        //console.log("keeping current localstore", JSON.stringify(grid_bts));
    }
}
if (!grid_bts) {
    fetch('http://swirlystudios.com/coursera/grid_bts.php')
      .then(response => {
          let resJson = response.json();
          return resJson;
        })
        .then(data => {
          data.timestamp = Date.now();
          localStorage.setItem("grid_bts.json", JSON.stringify(data))  
          grid_bts = data;
        });
}

window.addEventListener("error", (e) => {
    console.log("error found", e);
    grid_bts.errors.forEach((error) => {
        let match = false;
        error.keywords.forEach((keyword) => {
            if (e.message.includes(keyword)) {
                match = true;
            }
        });
        if (match) {
            console.log("Previous Rhyme users found this useful: ", error.response);
        }
    });
});