/**
 * @author colsen@sfy.co.za
 * @since 14-Jul-17.
 */


function setJSONStringOnCdServlet(cdListStr, saveOrDelete) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/MediaCatalogue/cdData",
        data: cdListStr,

        error: function (xhr, status, error) {
            console.log(error);
            console.log(status);
            console.log(xhr);
            alert("Something went wrong...");
        },
        success: function (response) {
            localStorage.setItem("cdResponse",response);
            switch (saveOrDelete){
                case "save":
                    alert("Item saved successfully...");
                    break;
                case "delete":
                    alert("Item deleted successfully...");
                    break;
            }
        }
    });
}

function setWebContextStringOnServlet(font, color) {
    var contextObj = {
        "color": color,
        "font": font
    };

    var contextStr = JSON.stringify(contextObj);

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/MediaCatalogue/webContext",
        data: contextStr,
        dataTypes: "text",

        error: function (xhr, status, error) {
            console.log(error);
            console.log(status);
            console.log(xhr);
            alert("Something went wrong...");
        },
        success: function (response) {
            localStorage.setItem("themeResponse", response);
            alert("Settings saved successfully...");
        }
    });
}

function setJSONStringOnDvdServlet(listStr, saveOrDelete) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/MediaCatalogue/dvdData",
        data: listStr,

        error: function (xhr, status, error) {
            console.log(error);
            console.log(status);
            console.log(xhr);
            alert("Something went wrong...");
        },
        success: function (response) {
            localStorage.setItem("dvdResponse", response);
            switch (saveOrDelete){
                case "save":
                    alert("Item saved successfully...");
                    break;
                case "delete":
                    alert("Item deleted successfully...");
                    break;
            }
        }
    });
}

function readSettings() {
    var contextStr = localStorage.getItem("themeResponse");

    if (contextStr === null) {
        var contextObj = JSON.parse("{\"color\":\"green\",\"font\":\"serif\"}");
        localStorage.setItem("themeResponse", JSON.stringify(contextObj));
    } else {
        contextObj = JSON.parse(contextStr);
    }

    var color = contextObj.color;
    var font = contextObj.font;

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

function redirectToCatalogueWindow() {
    window.location.assign("../../views/html/catalogue.html");
}

function editCDObj(row) {
    var index = row.parentNode.parentNode.rowIndex;
    var cdListStr = localStorage.getItem("cdResponse");
    var cdListObj = JSON.parse(cdListStr);

    for (var r = 0; r < cdListObj.length; r++) {
        var aCDObject = cdListObj[r];
        var i = aCDObject.id;

        if ((index - 1) === i) {
            localStorage.setItem("cdId", i);
            redirectToCatalogueWindow();
        }
    }
}

function editDVDObj(row) {
    var index = row.parentNode.parentNode.rowIndex;
    var dvdListStr = localStorage.getItem("dvdResponse");
    var dvdListObj = JSON.parse(dvdListStr);

    for (var r = 0; r < dvdListObj.length; r++) {
        var aDVDObject = dvdListObj[r];
        var i = aDVDObject.id;
        if ((index - 1) === i) {
            localStorage.setItem("dvdId", i);
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
    document.getElementById("tracks").value = aCDObject.tracks;
    document.getElementById("artists").value = aCDObject.artists;

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