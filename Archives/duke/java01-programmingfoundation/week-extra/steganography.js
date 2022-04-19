

// crop : crop image to show 
function crop(image, width, height) {
    var cropImage = new SimpleImage(width, height);
    
    var w2 = image.getWidth();
    var h2 = image.getHeight();
    for (var px of cropImage.values()) {
        let x = px.getX();
        let y = px.getY();
        
        if (x<w2 && y<h2) {
            cropImage.setPixel(x, y, image.getPixel(x, y));
        }
    }
    return cropImage;
}

//
function pixchange(col) {
    return Math.floor(col/16) * 16;
}

function chop2Hide(image) {
    var chopedImage = new SimpleImage(image.getWidth(), image.getHeight());
    
    for (let pixel of chopedImage.values()) {
        let x = pixel.getX();
        let y = pixel.getY();
        
        pixel.setRed(pixchange(image.getRed(x, y)));
        pixel.setGreen(pixchange(image.getGreen(x, y)));
        pixel.setBlue(pixchange(image.getBlue(x, y)));
    }
    return chopedImage;
}

function shift(image) {
    let shiftedImage = new SimpleImage(image.getWidth(), image.getHeight());
    
    for (let pixel of shiftedImage.values()) {
        let x = pixel.getX();
        let y = pixel.getY();
        
        pixel.setRed(image.getRed(x, y) / 16);
        pixel.setGreen(image.getGreen(x, y) / 16);
        pixel.setBlue(image.getBlue(x, y) / 16); 
    }
    return shiftedImage;
}

function combine(showImage, hideImage) {
    let combinedImage = new SimpleImage(showImage.getWidth(), showImage.getHeight());
    
    for (pixel of combinedImage.values()) {
        let x = pixel.getX();
        let y = pixel.getY();
        
        pixel.setRed(newpv(showImage.getRed(x, y), hideImage.getRed(x, y)));
        pixel.setGreen(newpv(showImage.getGreen(x, y), hideImage.getGreen(x, y)));
        pixel.setBlue(newpv(showImage.getBlue(x,y), hideImage.getBlue(x, y)));
    }
    
    return combinedImage;
}

// for hide graphics : hide graphics overflow
function newpv(p, q) {
    let ans = p + q;
    if (ans > 255) {
        throw new Error("pixel color overflow: p="+p+", q="+q);
    }
    return ans;
}

//
function encodeImage(showImage, hideImage) {
    let showImage2 = chop2Hide(showImage);
    let hideImage2 = shift(crop(hideImage, showImage.getWidth(), showImage.getHeight()));
    return combine(showImage2, hideImage2);
    
}

function decodePixel(col) {
    return (col%16) * 16;
}

function decodeImage(image) {
    let decodedImage = new SimpleImage(image.getWidth(), image.getHeight());
    
    for (pixel of decodedImage.values()) {
        let x = pixel.getX(); let y = pixel.getY();
        
        pixel.setRed(decodePixel(image.getRed(x, y)));
        pixel.setGreen(decodePixel(image.getGreen(x, y)));
        pixel.setBlue(decodePixel(image.getBlue(x, y)));
    }
    
    return decodedImage;
}

// main
var showImage = new SimpleImage("astrachan.jpg");
var hideImage = new SimpleImage("smallpanda.png");
var steganoImage = encodeImage(showImage, hideImage);
var decodedImage = decodeImage(steganoImage);

print(hideImage);
print(steganoImage);
print(decodedImage);
