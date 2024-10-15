// Exercise:Modifying Images
print("Exercise Modifying Images")

print("<br />  Part.1 - vertical 3")
function verticalStripe(image) {
    let width = image.getWidth();
    for (var px of image.values()) {
        x = px.getX();
        if (x < width/3) {
            px.setRed(255);
        } else if (x < (width*2 / 3)) {
            px.setGreen(255);
        } else {
            px.setBlue(255);
        }
        
    }
    return image;
}

image = new SimpleImage("hilton.jpg")
result = verticalStripe(image)
print(result)

// part2 swap color
print("<br/>  part.2 - swap Color")
function swapRedGreen(pixel) {
    let newRed = pixel.getGreen()
    let newGreen = pixel.getRed()
    pixel.setRed(newRed)
    pixel.setGreen(newGreen)
    return pixel
}

function swapAllPixels(image) {
    for (pixel of image.values()) {
        swapRedGreen(pixel)
    }
    return image
}

image = new SimpleImage("smallhands.png")
result = swapAllPixels(image)
print(result)

// part 3 duke devil to yellow
print("<br />part.3 - devil to yellow")

function toYellow(image) {
    for (px of image.values()) {
        let r = px.getRed()
        let g = px.getGreen()
        let b = px.getBlue()
        if (b >= 220) {
            if (r < 100) { // blue color
                px.setRed(255)
                px.setGreen(255)
                px.setBlue(0)
            } else { // to white
                px.setRed(255)
                px.setBlue(255)
                px.setGreen(255)
            }
        }
    }
    return image;
}

image = new SimpleImage("duke_blue_devil.png")
result = toYellow(image)
print(result)

