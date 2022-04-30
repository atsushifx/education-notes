"use strict";

(function() {

 
  const myImages = ['image1.jpg', 'image2.jpg', 'image3.jpg', 'image4.jpg', 'image5.jpg', ];
  let currentImage = 0;;

  const dispImage = (function(index) {
      const img = document.querySelector("#myimage");
      img.src = "images/" + myImages[index];
  });

  const prevPhoto = (function() {
    currentImage--;
    if (currentImage < 0) {
      currentImage = myImages.length - 1;
    }
    dispImage(currentImage);
  });

  const nextPhoto = (function() {
    currentImage++;
    if (currentImage >= myImages.length) {
      currentImage = 0;
    }
    dispImage(currentImage);
  });
  
  document.getElementById("previous").onclick = prevPhoto;
  document.getElementById('next').addEventListener('click', nextPhoto);
})();

