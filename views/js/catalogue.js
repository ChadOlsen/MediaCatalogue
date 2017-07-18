/**
 * @author colsen@sfy.co.za
 * @since 07-Jul-17.
 */

function loadEditedItem() {
    var dvdIndex = localStorage.getItem("dvdId");
    var cdIndex = localStorage.getItem("cdId");
    var dvdListObj;
    var cdListObj;

    if (dvdIndex !== null || "" !== dvdIndex) {
        var dvdListStr = localStorage.getItem("dvdResponse");
        if (dvdListStr === null || "" === dvdListStr) {
            dvdListObj = [];
            localStorage.setItem("dvdResponse", dvdListObj);
        } else {
            dvdListObj = JSON.parse(dvdListStr);
        }


        for (var i = 0; i < dvdListObj.length; i++) {
            var dvd = dvdListObj[i];
            if (dvd.id === parseInt(dvdIndex)) {
                loadEditDVDForm(dvd);
                return;
            }
        }
    }

    if (cdIndex !== null || "" !== cdIndex) {
        var cdListStr = localStorage.getItem("cdResponse");
        if (cdListStr === null || "" === cdListStr) {
            cdListObj = [];
            localStorage.setItem("cdResponse", cdListObj);
        } else {
            cdListObj = JSON.parse(cdListStr);
        }

        for (var j = 0; j < cdListObj.length; j++) {
            var cd = cdListObj[j];
            if (cd.id === parseInt(cdIndex)) {
                loadEditCDForm(cd);
                return;
            }
        }
    }
}

function dvdButtonCheck() {
    document.getElementById("cdForm").style.display = "none";
    document.getElementById("dvdForm").style.display = "block";
}

function cdButtonCheck() {
    document.getElementById("cdForm").style.display = "block";
    document.getElementById("dvdForm").style.display = "none";
}

function saveNewDVDItem() {
    var dvdListStr = localStorage.getItem("dvdResponse");
    var dvdListObj;
    var autoIncrement = 0;

    var dvdTitle = document.getElementById("dvdTitle");
    var dvdGenre = document.getElementById("dvdGenre");
    var dvdDuration = document.getElementById("dvdDuration");
    var leadActor = document.getElementById("leadActor");
    var leadActress = document.getElementById("leadActress");

    if (localStorage.getItem("dvdId") === null || "" === localStorage.getItem("dvdId")) {
        if (dvdListStr === null || "" === dvdListStr) {
            dvdListObj = [];
            autoIncrement = 0;
        } else {
            dvdListObj = JSON.parse(dvdListStr);
            autoIncrement = dvdListObj.length;
        }

        var confirmation = confirm("You are about to add save a new DVD Item\n" +
            "Title: " + dvdTitle.value + "\n" +
            "Genre: " + dvdGenre.value + "\n" +
            "Duration: " + dvdDuration.value + "\n" +
            "Lead Actor: " + leadActor.value + "\n" +
            "Lead Actress: " + leadActress.value);

        if (confirmation) {
            var dvdObject = {
                "type": "DVD",
                "title": dvdTitle.value,
                "genre": dvdGenre.value,
                "duration": dvdDuration.value,
                "id": autoIncrement,
                "leadActor": leadActor.value,
                "leadActress": leadActress.value
            };

            var index = dvdListObj.length;
            dvdListObj[index] = dvdObject;
            dvdListStr = JSON.stringify(dvdListObj);
            setJSONStringOnDvdServlet(dvdListStr, "save");
            return;
        }
    }

    dvdListObj = JSON.parse(dvdListStr);
    var id = parseInt(localStorage.getItem("dvdId"));

    confirmation = confirm("You are about to save your edited DVD Item\n" +
        "Title: " + dvdTitle.value + "\n" +
        "Genre: " + dvdGenre.value + "\n" +
        "Duration: " + dvdDuration.value + "\n" +
        "Lead Actor: " + leadActor.value + "\n" +
        "Lead Actress: " + leadActress.value);

    if (confirmation) {
        dvdObject = {
            "type": "DVD",
            "title": dvdTitle.value,
            "genre": dvdGenre.value,
            "duration": dvdDuration.value,
            "id": id,
            "leadActor": leadActor.value,
            "leadActress": leadActress.value
        };

        dvdListObj[id] = dvdObject;
        dvdListStr = JSON.stringify(dvdListObj);
        setJSONStringOnDvdServlet(dvdListStr, "save");
        localStorage.setItem("dvdId", "");
    }
}

function saveNewCDItem() {
    var cdListStr = localStorage.getItem("cdResponse");
    var cdListObj;
    var autoIncrement = 0;

    var cdTitle = document.getElementById("cdTitle");
    var cdGenre = document.getElementById("cdGenre");
    var cdDuration = document.getElementById("cdDuration");
    var tracks = document.getElementById("tracks");
    var artists = document.getElementById("artists");

    if (localStorage.getItem("cdId") === null || "" === localStorage.getItem("cdId")) {
        if (cdListStr === null || "" === cdListStr) {
            cdListObj = [];
            autoIncrement = 0;
        } else {
            cdListObj = JSON.parse(cdListStr);
            autoIncrement = cdListObj.length;
        }

        var artistArray = artists.value.split("\\n");
        var confirmation = confirm("You are about to add save a new CD Item\n" +
            "Title: " + cdTitle.value + "\n" +
            "Genre: " + cdGenre.value + "\n" +
            "Duration: " + cdDuration.value + "\n" +
            "No. of Tracks: " + tracks.value + "\n" +
            "Contributing Artists: " + artistArray);

        if (confirmation) {
            var cdObject = {
                "type": "CD",
                "title": cdTitle.value,
                "genre": cdGenre.value,
                "duration": cdDuration.value,
                "id": autoIncrement,
                "tracks": tracks.value,
                "artists": artistArray
            };

            var index = cdListObj.length;
            cdListObj[index] = cdObject;
            cdListStr = JSON.stringify(cdListObj);
            setJSONStringOnCdServlet(cdListStr, "save");
            return;
        }
    }

    cdListObj = JSON.parse(cdListStr);
    var id = parseInt(localStorage.getItem("cdId"));

    confirmation = confirm("You are about to save your edited CD Item\n" +
        "Title: " + cdTitle.value + "\n" +
        "Genre: " + cdGenre.value + "\n" +
        "Duration: " + cdDuration.value + "\n" +
        "No. of Tracks: " + tracks.value + "\n" +
        "Contributing Artists: " + artistArray);

    if (confirmation) {
        cdObject = {
            "type": "CD",
            "title": cdTitle.value,
            "genre": cdGenre.value,
            "duration": cdDuration.value,
            "id": id,
            "tracks": tracks.value,
            "artists": artistArray
        };

        cdListObj[id] = cdObject;
        cdListStr = JSON.stringify(cdListObj);
        setJSONStringOnCdServlet(cdListStr, "save");
        localStorage.setItem("cdId", "");
    }
}
