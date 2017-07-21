/**
 * @author colsen@sfy.co.za
 * @since 14-Jul-17.
 */

function getStringObjectFromCookie(objectKey) {
    var allKeysAndValues = document.cookie.split(";");

    for (var i = 0; i < allKeysAndValues.length; i++) {
        var keyAndValue = allKeysAndValues[i];
        var keyAndValueAsArray = keyAndValue.split("=");
        var keyStr = keyAndValueAsArray[0].trim();

        if (keyStr === objectKey) {
            return keyAndValueAsArray[1];
        }
    }
    setStringObjectOnCookie(objectKey, "");
    return getStringObjectFromCookie(objectKey);
}

function setStringObjectOnCookie(objectKey, objectValue) {
    var allKeysAndValues = document.cookie.split(";");

    if (!(allKeysAndValues > 1)) {
        document.cookie = objectKey + "=" + objectValue;
        return;
    }

    for (var i = 0; i < allKeysAndValues.length; i++) {
        var keyAndValue = allKeysAndValues[i];
        var keyAndValueAsArray = keyAndValue.split("=");

        var keyStr = keyAndValueAsArray[0].trim();
        if (keyStr === objectKey) {
            var newValue = objectKey + "=" + objectValue;
            document.cookie = document.cookie.replace(keyAndValue, newValue);
            return;
        }
    }

    document.cookie = document.cookie + ";" + objectKey + "=" + objectValue;
}

function readSettings() {
    var cookies = document.cookie.split(";");

    for (var i = 0; i < cookies.length; i++) {
        var cookieValue = cookies[i].split("=");
        var name = cookieValue[0].trim();

        if (name === "color") {
            selectColorStyle(cookieValue[1].trim());
        }

        if (name === "font") {
            document.body.style.fontFamily = cookieValue[1].trim();
        }
    }
}

function selectColorStyle(color) {
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

function redirectToCatalogueWindow() {
    window.location.assign("../../../src/views/html/catalogue.html");
}

function editCDObj(row) {
    var index = row.parentNode.parentNode.rowIndex;
    var cdListStr = getStringObjectFromCookie("cdlist");
    var cdListObj = JSON.parse(cdListStr);

    for (var r = 0; r < cdListObj.length; r++) {
        var aCDObject = cdListObj[r];
        var i = aCDObject.id;

        if ((index - 1) === i) {
            setStringObjectOnCookie("cdID", i);
            redirectToCatalogueWindow();
        }
    }
}

function loadEditCDForm(aCDObject) {
    document.getElementById("cdForm").style.display = "block";
    document.getElementById("dvdForm").style.display = "none";

    document.getElementById("cdTitle").value = aCDObject.title;
    document.getElementById("cdGenre").value = aCDObject.genre;
    document.getElementById("cdDuration").value = aCDObject.duration;
    document.getElementById("tracks").value = aCDObject.noOfTracks;
    document.getElementById("artists").value = aCDObject.contributingArtists;

}

function editDVDObj(row) {
    var index = row.parentNode.parentNode.rowIndex;
    var dvdListStr = getStringObjectFromCookie("dvdlist");
    var dvdListObj = JSON.parse(dvdListStr);

    for (var r = 0; r < dvdListObj.length; r++) {
        var aDVDObject = dvdListObj[r];
        var i = aDVDObject.id;
        if ((index - 1) === i) {
            setStringObjectOnCookie("dvdID", i);
            redirectToCatalogueWindow();
        }
    }
}

function loadEditDVDForm(dvd) {
    document.getElementById("dvdForm").style.display = "block";
    document.getElementById("cdForm").style.display = "none";

    document.getElementById("dvdTitle").value = dvd.title;
    document.getElementById("dvdGenre").value = dvd.genre;
    document.getElementById("dvdDuration").value = dvd.duration;
    document.getElementById("leadActor").value = dvd.leadActor;
    document.getElementById("leadActress").value = dvd.leadActress;
}