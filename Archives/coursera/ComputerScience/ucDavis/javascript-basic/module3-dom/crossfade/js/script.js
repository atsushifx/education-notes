/* script.js crossfade slideshow */
"use strict";

(function() {
  const myPhotos = ["image1.jpg", "image2.jpg", "image3.jpg", "image4.jpg", "image5.jpg"];
  let currentImage = 0;
  // element
  const prevButton = document.querySelector("#previous");
  const nextButton = document.querySelector("#next");

  /**
   * スライドショー用にfadeinイメージを追加
   */
  function fadeinImage(index) {
    const container = document.querySelector("#content") 
    const newSlide = document.createElement('img');
    newSlide.src = 'slides/' + myPhotos[index];
    newSlide.className = 'fadeinImage';

    container.appendChild(newSlide);

    if (container.children.length > 2) {
      container.removeChild(container.children[0]);
    }
  }

  nextButton.addEventListener('click', function (e){
    e.preventDefault();
    currentImage++;
    if (currentImage>=myPhotos.length){
      currentImage = 0;
    }
    fadeinImage(currentImage);
  });

  prevButton.addEventListener('click', function(e){
    e.preventDefault();

    currentImage--;
    if (currentImage < 0) {
      currentImage = myPhotos.length - 1;
    }

    fadeinImage(currentImage);
  });
})();
