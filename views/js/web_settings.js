/**
 * @author colsen@sfy.co.za
 * @since 07-Jul-17.
 */

function changeThemeColor() {
    var color = document.getElementById("colorSelector").value;

    switch (color) {
        case "green":
            document.getElementById("blueTheme").disabled = true;
            document.getElementById("redTheme").disabled = true;
            document.getElementById("greenTheme").disabled = false;
            break;
        case "blue":
            document.getElementById("redTheme").disabled = true;
            document.getElementById("greenTheme").disabled = true;
            document.getElementById("blueTheme").disabled = false;
            break;
        case "red":
            document.getElementById("blueTheme").disabled = true;
            document.getElementById("greenTheme").disabled = true;
            document.getElementById("redTheme").disabled = false;
            break;
    }
}

function changeFont() {
    var font = document.getElementById("fontSelector").value;

    switch (font) {
        case "sans-serif":
            document.body.style.fontFamily = "sans-serif";
            break;
        case "cursive":
            document.body.style.fontFamily = "cursive";
            break;
        case "serif":
            document.body.style.fontFamily = "serif";
            break;
    }
}

function saveWebSettings() {
    var colorValue = document.getElementById("colorSelector").value;
    var fontValue = document.getElementById("fontSelector").value;

    setWebContextStringOnServlet(fontValue, colorValue, "new");
}