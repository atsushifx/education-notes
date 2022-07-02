if (typeof client !== "undefined" && client !== null) {
    if (JSON.stringify(client) === clientJSON) {
        document.body.innerHTML = "<div class='client-end-screen'>Congratulations, you found the cookie client!</div>";
    } else {
        document.body.innerHTML = "<div class='client-end-screen'>Sorry, that is not the correct cookie client</div>";
    }
} else {
    buildTables();
}