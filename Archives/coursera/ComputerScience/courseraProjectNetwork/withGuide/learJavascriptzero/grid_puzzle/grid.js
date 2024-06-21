const NUM_ROWS    = 12;
const NUM_COLUMNS = 12;
const gridContainer = document.querySelector("#gridContainer");
const brush = document.createElement("img");
const runButton = document.querySelector("#runButton");
const reloadButton = document.querySelector("#reloadButton");
reloadButton.addEventListener("click", ()=>window.location.reload(false));
const resetButton = document.querySelector("#resetButton");
brush.id = "brush";
brush.src = "paint.svg";
brush.style.width  = "90%";
brush.style.height = "90%";

let squares = Array(NUM_ROWS).fill().map(()=>Array(NUM_COLUMNS).fill())
let columnsString = "";
let rowsString = "";
let currentRow = 0;
let currentColumn = 0;

let workspace = Blockly.inject('blocklyDiv',
                              {toolbox: document.getElementById('toolbox'),
                               trash: true});
Blockly.JavaScript.addReservedWords('code');

Blockly.Blocks['move_left'] = {
    init: function() {
        this.setNextStatement(true);
        this.setPreviousStatement(true); 
        this.appendDummyInput().appendField(new Blockly.FieldLabel('Move left'));
        this.setOutput(false);
        this.setColour(160);
        this.setTooltip('Moves the paintbrush one to the left');
    }
};
Blockly.Blocks['move_right'] = {
    init: function() {
        this.setNextStatement(true);
        this.setPreviousStatement(true); 
        this.appendDummyInput().appendField(new Blockly.FieldLabel('Move right'));
        this.setOutput(false);
        this.setColour(160);
        this.setTooltip('Moves the paintbrush one to the right');
    }
};
Blockly.Blocks['move_up'] = {
    init: function() {
        this.setNextStatement(true);
        this.setPreviousStatement(true); 
        this.appendDummyInput().appendField(new Blockly.FieldLabel('Move up'));
        this.setOutput(false);
        this.setColour(160);
        this.setTooltip('Moves the paintbrush up one');
    }
};
Blockly.Blocks['move_down'] = {
    init: function() {
        this.setNextStatement(true);
        this.setPreviousStatement(true); 
        this.appendDummyInput().appendField(new Blockly.FieldLabel('Move down'));
        this.setOutput(false);
        this.setColour(160);
        this.setTooltip('Moves the paintbrush down one');
    }
};
Blockly.Blocks['variables_get_hasNotHitEdge'] = {
    init: function() {
        this.appendDummyInput()
        .appendField(new Blockly.FieldVariable("hasNotHitEdge"), "hasNotHitEdge");
        this.setOutput(true, null);
    }
};
Blockly.Blocks['variables_get_currentRow'] = {
    init: function() {
        this.appendDummyInput()
        .appendField(new Blockly.FieldVariable("currentRow"), "currentRow");
        this.setOutput(true, null);
    }
};
Blockly.Blocks['variables_get_currentColumn'] = {
    init: function() {
        this.appendDummyInput()
        .appendField(new Blockly.FieldVariable("currentColumn"), "currentColumn");
        this.setOutput(true, null);
    }
};


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
Blockly.JavaScript['move_left'] = ()=> "moveLeft();";
Blockly.JavaScript['move_right'] = ()=> "moveRight();";
Blockly.JavaScript['move_up'] = ()=> "moveUp();";
Blockly.JavaScript['move_down'] = ()=> "moveDown();";
Blockly.JavaScript['variables_get_hasNotHitEdge'] = ()=> "hasNotHitEdge";
Blockly.JavaScript['variables_get_currentRow'] = ()=> "currentRow";
Blockly.JavaScript['variables_get_currentColumn'] = ()=> "currentColumn";
Blockly.JavaScript.STATEMENT_PREFIX = 'highlightBlock(%1);\n';
Blockly.JavaScript.addReservedWords('highlightBlock');
let highlightPause = false;
highlightBlock = (id) => {
    highlightPause = {blockId: id};
}

runButton.addEventListener("click", () => {
    for(let i = 0; i < NUM_ROWS; i++) {
        for(let j = 0; j < NUM_COLUMNS; j++) {
            squares[i][j].style.backgroundColor = null;
            squares[i][j].innerText = i + "," + j;
        }
    }
    setStartSquare(0,0);

    initApi = (interpreter, globalObject) => {
        interpreter.setProperty(globalObject, 'moveRight',
            interpreter.createNativeFunction(moveRight));
        interpreter.setProperty(globalObject, 'moveLeft',
            interpreter.createNativeFunction(moveLeft));
        interpreter.setProperty(globalObject, 'moveUp',
            interpreter.createNativeFunction(moveUp));
        interpreter.setProperty(globalObject, 'moveDown',
            interpreter.createNativeFunction(moveDown));
        interpreter.setProperty(globalObject, 'highlightBlock',
            interpreter.createNativeFunction((id) => {
                return highlightBlock(id);
            }));
      }
    
    let code = Blockly.JavaScript.workspaceToCode(workspace);
    var myInterpreter = new Interpreter(code, initApi);

    nextStep = () => {
        if (highlightPause) {
            window.setTimeout(() => {
                workspace.highlightBlock(highlightPause.blockId);
                highlightPause = false;
                nextStep();
            }, 1000);
        } else {
            if (myInterpreter.step()) {
                window.setTimeout(nextStep, 10);
            }
        }
    }
    nextStep();
});

/*resetButton.addEventListener("click", () =>{
    for(let i = 0; i < NUM_ROWS; i++) {
        for(let j = 0; j < NUM_COLUMNS; j++) {
            squares[i][j].style.backgroundColor = null;
            squares[i][j].innerText = i + "," + j;
        }
    }
    setStartSquare(0,0);
});*/