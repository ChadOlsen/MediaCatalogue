/**
 * @author colsen@sfy.co.za
 * @since 07-Jul-17.
 */

function changeThemeColor() {
    selectColorStyle(document.getElementById("colorSelector").value);
}

function changeFont() {
    document.body.style.fontFamily = document.getElementById("fontSelector").value;
}

function saveWebSettings() {
    var colorValue = document.getElementById("colorSelector").value;
    var fontValue = document.getElementById("fontSelector").value;

    document.cookie = "color=" + colorValue;
    document.cookie = "font=" + fontValue;
}