// rampup exercise

function phrase3words(value1, value2, value3) {
    var answer = value1 + " " + value2 + " " + value3;
    return answer;
}

var result1 = phrase3words("smile","at","everyone");
print(result1);
var result2 = phrase3words("everyone","wave", "back");
print(result2);
var result3 = phrase3words("coding","is", "fun");
print(result3)

function reformatName(first, last) {
    return last + ", " + first;
}

var result = reformatName("Susan", "Rodger");
print(result);
result = reformatName("Robert", "Duvall");
print(result);

function numberPixels(namefile) {
    var someImg = new SimpleImage(namefile);
    var width  = someImg.getWidth();  
    var height = someImg.getHeight(); 
    return width * height;
}

var result = numberPixels("chapel.png");
print(result);
result = numberPixels("dinos.png");
print(result);

// ex4 perimeter
print("<br>Ex.4 perimeter");
function perimeter(imageName) {
    let img = new SimpleImage(imageName);
    let w = img.getWidth();
    let h = img.getHeight();
    return w+w + h+h;
}

print(perimeter("rodger.png"));


// ex5 getPixel
print("<br />Ex.5 print Pixel");
function printPixel(nameImage, xpos, ypos) {
    img = new SimpleImage(nameImage)

    print("Red is ",   img.getRed(xpos, ypos));
    print("Green is ", img.getGreen(xpos, ypos));
    print("Blue is ",  img.getBlue(xpos, ypos));
}

printPixel("drewgreen.png",10, 10);
printPixel("drewgreen.png",250, 500);

// Ex.6 Exercise 6 - Sum of the RGB values for a Pixel
print("<br />Ex.6 - Sum of the RGB values for a Pixel");

function sumPixel(nameOfImage, xpos, ypos) {
    var img = new SimpleImage(nameOfImage);
    var r = img.getRed(xpos, ypos);
    var g = img.getGreen(xpos, ypos);
    var b = img.getBlue(xpos, ypos);
    
    return r + g + b;
}

var answer = sumPixel("drewgreen.png", 250, 500);
print(answer);
answer = sumPixel("drewgreen.png",10, 10);
print(answer);

