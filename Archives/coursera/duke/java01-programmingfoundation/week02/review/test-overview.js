// overview

// q1 duke-devil backslash
print("<br /> q.1  duke-devil slashed")
var image = new SimpleImage("duke_blue_devil.png");
w = image.getWidth()

for (let pixel of image.values()) {
    x = pixel.getX();
    y = pixel.getY();
    if (x > y) {
        pixel.setRed(0)
        pixel.setBlue(0)
    }
}
// print(image);


// q4 advanced border
print("<br /> q.4 - addBorders");
function setBlack(pixel) {
    pixel.setRed(0);
    pixel.setGreen(0);
    pixel.setBlue(0);
}


function pixelOnEdge(image, pixel, horizontalThick, verticalThick){
    var x = pixel.getX();
    var y = pixel.getY();
    if (x < verticalThick || x > image.getWidth() - verticalThick){
        return true;
    }
    if (y < horizontalThick || y > image.getHeight() - horizontalThick){
        return true;
    }
    return false;
}

function addBorders(image,horizontalThick, verticalThick){
    for (var px of image.values()){
        if (pixelOnEdge(image,px,horizontalThick,verticalThick)){
            px = setBlack(px);
        }
    }
    return image;
}

var img = new SimpleImage("nyc-skyline.jpg");
img = addBorders(img,40,20);
// print(img);


// q6 string connet
print("<br /> q.6 about compose function.")
function compose(word, separator) {
    return word+separator+word+separator+word;
}

var phrase = compose("duck", "goose");
print(phrase);

var phrase2 = compose("meow","-");
print(phrase2);


// q.7
print("<br /> q.7 - x,y if condition");

function q7(x, y) {
    if (x > 10) {
        print("one");
        if (y < 10) {
            print("two");
        }
    }
    if (x < 20) {
        print("three");
        if (y > 5) {
            print("four");
        }
    }
}
q7(30, 8)
