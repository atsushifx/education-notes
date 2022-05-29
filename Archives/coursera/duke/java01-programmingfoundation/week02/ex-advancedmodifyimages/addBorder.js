// add border to image

function setBlack(pixel) {
    pixel.setRed(0);
    pixel.setGreen(0);
    pixel.setBlue(0);
    return pixel;
}

function addBorder(inImage, thickness) {
    let width = inImage.getWidth()
    let height = inImage.getHeight()
    // let oImage = new SimpleImage(width, height)

    for (let pixel of inImage.values()) {
        let x = pixel.getX();
        let y = pixel.getY();
        if (x<thickness || x>width-thickness || y<thickness || y>height-thickness) {
            setBlack(pixel);
            inImage.setPixel(x, y, pixel);
        } else {
            // oImage.setPixel(x, y, pixel);
        }
    }
    return inImage;
}

let image1 = new SimpleImage("smallpanda.png");
addBorder(image1, 10)

print(image1)
