/**
 * @author colsen@sfy.co.za
 * @since 07-Jul-17.
 */

function displayDVDTable() {
    document.getElementById("cdTableHeader").style.display = "none";
    document.getElementById("cdTable").style.display = "none";

    document.getElementById("dvdTableHeader").style.display = "block";
    document.getElementById("dvdTable").style.display = "block";
}

function displayCDTable() {
    document.getElementById("dvdTableHeader").style.display = "none";
    document.getElementById("dvdTable").style.display = "none";

    document.getElementById("cdTableHeader").style.display = "block";
    document.getElementById("cdTable").style.display = "block";
}

function deleteDVDEntry(row) {
    var index = row.parentNode.parentNode.rowIndex;

    if (confirm("Are you sure you want to delete this entry from the DVD Table?")) {
        var listOfAllDVDsStr = localStorage.getItem("dvdResponse");
        var listOfAllDVDsObj = JSON.parse(listOfAllDVDsStr);

        for (var r = 0; r < listOfAllDVDsObj.length; r++) {
            var aDVDObject = listOfAllDVDsObj[r];
            var ind = aDVDObject.id;
            if ((index - 1) === ind) {
                document.getElementById("dvdTable").deleteRow(index);
                listOfAllDVDsObj.splice(ind, 1);
                if (listOfAllDVDsObj.length > 0){
                    var newValue = JSON.stringify(listOfAllDVDsObj);
                } else {
                    newValue = [];
                }
                setJSONStringOnDvdServlet(newValue, "delete");
            }
        }
    }
}

function deleteCDEntry(row) {
    var index = row.parentNode.parentNode.rowIndex;

    if (confirm("Are you sure you want to delete this entry from the CD Table?")) {
        var listOfAllCDsStr = localStorage.getItem("cdResponse");
        var listOfAllCDsObj = JSON.parse(listOfAllCDsStr);

        for (var r = 0; r < listOfAllCDsObj.length; r++) {
            var aCDObject = listOfAllCDsObj[r];
            var ind = aCDObject.id;
            if ((index - 1) === ind) {
                document.getElementById("cdTable").deleteRow(index);
                listOfAllCDsObj.splice(ind, 1);
                if (listOfAllCDsObj.length > 0){
                    var newValue = JSON.stringify(listOfAllCDsObj);
                } else {
                    newValue = [];
                }
                setJSONStringOnCdServlet(newValue, "delete");
            }
        }
    }
}

function drawTable() {
    var listOfAllDVDsStr = localStorage.getItem("dvdResponse");
    var listOfAllDVDsObj = JSON.parse(listOfAllDVDsStr);

    var tbl = document.getElementById("dvdTable");

    for (var r = 0; r < listOfAllDVDsObj.length; r++) {
        var aDVDObject = listOfAllDVDsObj[r];

        var dvdRow = document.createElement("tr");

        addCol(aDVDObject.title, dvdRow);
        addCol(aDVDObject.genre, dvdRow);
        addCol(aDVDObject.duration, dvdRow);
        addCol(aDVDObject.leadActor, dvdRow);
        addCol(aDVDObject.leadActress, dvdRow);
        addColImage("../../images/EditButtonImage.png", dvdRow, "dvdEdit");
        addColImage("../../images/DeleteButtonImage.png", dvdRow, "dvdDelete");

        tbl.appendChild(dvdRow); // add the dvdRow to the end of the table body
    }

    var listOfAllCDsStr = localStorage.getItem("cdResponse");
    var listOfAllCDsObj = JSON.parse(listOfAllCDsStr);


    tbl = document.getElementById("cdTable");

    for (var j = 0; j < listOfAllCDsObj.length; j++) {
        var aCDObject = listOfAllCDsObj[j];

        var cdRow = document.createElement("tr");

        addCol(aCDObject.title, cdRow);
        addCol(aCDObject.genre, cdRow);
        addCol(aCDObject.duration, cdRow);
        addCol(aCDObject.tracks, cdRow);
        addCol(aCDObject.artists, cdRow);
        addColImage("../../images/EditButtonImage.png", cdRow, "cdEdit");
        addColImage("../../images/DeleteButtonImage.png", cdRow, "cdDelete");

        tbl.appendChild(cdRow); // add the dvdRow to the end of the table body
    }
}

function addCol(value, row) {
    var cell = document.createElement("td");
    var cellText = document.createTextNode(value);

    cell.appendChild(cellText);
    row.appendChild(cell);
}

function addColImage(url, row, type) {
    var cell = document.createElement("td");
    var img = document.createElement("img");

    img.src = url;
    img.class = "editOrDeleteIcon";
    img.style.width = "20px";

    switch (type) {
        case "cdEdit":
            img.setAttribute("onclick", "editCDObj(this)");
            cell.appendChild(img);
            row.appendChild(cell);
            break;
        case "cdDelete":
            img.setAttribute("onclick", "deleteCDEntry(this)");
            cell.appendChild(img);
            row.appendChild(cell);
            break;
        case "dvdEdit":
            img.setAttribute("onclick", "editDVDObj(this)");
            cell.appendChild(img);
            row.appendChild(cell);
            break;
        case "dvdDelete":
            img.setAttribute("onclick", "deleteDVDEntry(this)");
            cell.appendChild(img);
            row.appendChild(cell);
            break;
    }
}