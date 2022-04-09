let grid_bts = localStorage.getItem("grid_bts.json");
if (grid_bts) {
    if (Date.now > (grid_bts.timestamp + 259200000)) {
        grid_bts = null;
    }
}
if (!grid_bts) {
    fetch('http://swirlystudios.com/coursera/grid_bts.php')
      .then(response => {
          let resJson = response.json();
          return resJson;
        })
        .then(data => {
          data.timestamp = Date.now();
          localStorage.setItem("grid_bts.json", JSON.stringify(data))  
          grid_bts = data;
        });
}

window.addEventListener("error", (e) => {
    console.log("error", e);
    console.log(e.message);
});