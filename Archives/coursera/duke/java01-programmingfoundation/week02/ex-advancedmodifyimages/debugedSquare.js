// debug 2x2 color square
var img = new SimpleImage(200,200);
for (var px of img.values()) {
    var x = px.getX();
    var y = px.getY();
    if (x < img.getWidth()/2){
        px.setRed(255);
        if (y <img.getHeight()/2) {
        } else {
            px.setBlue(255);
        }
    } else {
        if (y<img.getHeight()/2){
            px.setGreen(255);
        } else {
            px.setBlue(255);
        }
    }
}
print(img);
