// rampup ex: Loops & Conditions

// Ex.1 change to red
print("Ex.1 - Turn the chapel red.")
var image = new SimpleImage("chapel.png");
for (var px of image.values()) {
    px.setRed(255)  // to Reden   
}
print(image);
print("<br/>")

// Exercise 2 - Remove all the red
print("Ex.2 - Remove all the red")
image = new SimpleImage("chapel.png");
for (px of image.values()) {
    px.setRed(0);
}
print(image);
print("<br />")

// Exercise 3 - Turn the eggs less red
print("Ex.3 - Turn the eggs less red.")
var image3 = new SimpleImage("eastereggs.jpg");
for (let px3 of image3.values()) {
    if (px3.getRed() >= 70) {
        px3.setRed(70);
    }
}
print(image3);

// Exercise 4 - Add Thick Black Line to Bottom of Owen
print("<br /> Ex.4 - Add Thick Black Line to Bottom of Owen")
var image4 = new SimpleImage("astrachan.jpg");
var height = image4.getHeight();
for (var px of image4.values()) {
    let x = px.getX();
    let y = px.getY();
    
    if (y >= (height-10)) {
        px.setRed(0);
        px.setGreen(0);
        px.setBlue(0)
    }
}
print(image4);

// Exercise 5 - Green square in top left corner
print("<br />Ex.5 - Green square in top left corner");
var image5 = new SimpleImage("chapel.png");
for (var px of image5.values()) {
    let x = px.getX();
    let y = px.getY();
    
    if (x<=50 && y<=50) {
        px.setRed(0);
        px.setGreen(255);
        px.setBlue(0);
    }
}
print(image5);

// Exercise 6 - Rectangle of any color in top right corner
print("<br />Ex.6 - Rectangle of any color in top right corner")
function topRightCorner(width, height, image, red, green, blue) {
    let width2 = image.getWidth() - width;
    let height2 = height;
    for (var px of image.values()) {
        x = px.getX();
        y = px.getY();
        if ((x>=width2) && (y<=height2)) {
            px.setRed(red);
            px.setGreen(green);
            px.setBlue(blue);
        }
        
    }
    
    return image;
}

var picture = new SimpleImage("chapel.png");
var result = topRightCorner(30, 60, picture, 255, 255, 0);
print(result);

var picture2 = new SimpleImage("smalllion.jpg");
var result2 = topRightCorner(125, 20, picture2, 255, 0, 0);
print(result2);


// Exercise 7 - Changes in Red
print("<br /> Ex.7 - Changes in Red")

function changeRed(width, height) {
    let picture = new SimpleImage(width, height);
    var red = 0;
    
    for (var px of picture.values()) {
        let x = px.getX();
        let y = px.getY();
        
        red = (x>=256) ? 255 : x;
        px.setRed(red);
        px.setGreen(0);
        px.setBlue(0);
    }
    return picture;
}

var result = changeRed(256,200);
print(result);

// optional change red2
print("<br /> Optional : change red 2")

function changeRed2(width, height, green, blue) {
    let image = new SimpleImage(width, height);
    let = red = 0;
    for (var px of image.values()) {
        x = px.getX();
        y = px.getY();
        px.setRed(red);
        px.setGreen(green);
        px.setBlue(blue);
        
        red++;
        if (x == 0) {
            red = 0;
        }
    }
    
    return image;
}

image = changeRed2(256, 200, 200, 100);
print(image);
