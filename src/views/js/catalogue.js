/**
 * @author colsen@sfy.co.za
 * @since 07-Jul-17.
 */

function loadEditedItem() {
    if ("" !== getStringObjectFromCookie("dvdID")){
        var index = parseInt(getStringObjectFromCookie("dvdID"));
        var dvdListStr = getStringObjectFromCookie("dvdlist");
        var dvdListObj = JSON.parse(dvdListStr);

        for (var i = 0; i<dvdListObj.length; i++){
            var dvd = dvdListObj[i];
            if (dvd.id === index){
                loadEditDVDForm(dvd);
                return;
            }
        }
    }

    if ("" !== getStringObjectFromCookie("cdID")){
        index = parseInt(getStringObjectFromCookie("cdID"));
        var cdListStr = getStringObjectFromCookie("cdlist");
        var cdListObj = JSON.parse(cdListStr);

        for (var j = 0; j<cdListObj.length; j++){
            var cd = cdListObj[j];
            if (cd.id === index){
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
    var dvdListStr = getStringObjectFromCookie("dvdlist");
    var dvdListObj;
    var autoIncrement = 0;

    var dvdTitle = document.getElementById("dvdTitle");
    var dvdGenre = document.getElementById("dvdGenre");
    var dvdDuration = document.getElementById("dvdDuration");
    var leadActor = document.getElementById("leadActor");
    var leadActress = document.getElementById("leadActress");

    if ("" === getStringObjectFromCookie("dvdID")) {
        if ("" === dvdListStr) {
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
                "title": dvdTitle.value,
                "genre": dvdGenre.value,
                "duration": dvdDuration.value,
                "leadActor": leadActor.value,
                "leadActress": leadActress.value,
                "id": autoIncrement
            };

            var index = dvdListObj.length;
            dvdListObj[index] = dvdObject;
            dvdListStr = JSON.stringify(dvdListObj);
            setStringObjectOnCookie("dvdlist", dvdListStr);
            return;
        }
    }

    dvdListObj = JSON.parse(dvdListStr);
    var id = parseInt(getStringObjectFromCookie("dvdID"));

    confirmation = confirm("You are about to save your edited CD Item\n" +
        "Title: " + dvdTitle.value + "\n" +
        "Genre: " + dvdGenre.value + "\n" +
        "Duration: " + dvdDuration.value + "\n" +
        "Lead Actor: " + leadActor.value + "\n" +
        "Lead Actress: " + leadActress.value);

    if (confirmation) {
        dvdObject = {
            "title": dvdTitle.value,
            "genre": dvdGenre.value,
            "duration": dvdDuration.value,
            "leadActor": leadActor.value,
            "leadActress": leadActress.value,
            "id": id
        };

        dvdListObj[id] = dvdObject;
        dvdListStr = JSON.stringify(dvdListObj);
        setStringObjectOnCookie("dvdlist", dvdListStr);
        setStringObjectOnCookie("dvdID", "");
    }
}

function saveNewCDItem() {
    var cdListStr = getStringObjectFromCookie("cdlist");
    var cdListObj;
    var autoIncrement = 0;

    var cdTitle = document.getElementById("cdTitle");
    var cdGenre = document.getElementById("cdGenre");
    var cdDuration = document.getElementById("cdDuration");
    var tracks = document.getElementById("tracks");
    var artists = document.getElementById("artists");

    if ("" === getStringObjectFromCookie("cdID")) {
        if ("" === cdListStr) {
            cdListObj = [];
            autoIncrement = 0;
        } else {
            cdListObj = JSON.parse(cdListStr);
            autoIncrement = cdListObj.length;
        }

        var confirmation = confirm("You are about to add save a new CD Item\n" +
            "Title: " + cdTitle.value + "\n" +
            "Genre: " + cdGenre.value + "\n" +
            "Duration: " + cdDuration.value + "\n" +
            "No. of Tracks: " + tracks.value + "\n" +
            "Contributing Artists: " + artists.value);

        if (confirmation) {
            var cdObject = {
                "title": cdTitle.value,
                "genre": cdGenre.value,
                "duration": cdDuration.value,
                "noOfTracks": tracks.value,
                "contributingArtists": artists.value,
                "id": autoIncrement
            };

            var index = cdListObj.length;
            cdListObj[index] = cdObject;
            cdListStr = JSON.stringify(cdListObj);
            setStringObjectOnCookie("cdlist", cdListStr);
            return;
        }
    }

    cdListObj = JSON.parse(cdListStr);
    var id = parseInt(getStringObjectFromCookie("cdID"));

    confirmation = confirm("You are about to save your edited CD Item\n" +
        "Title: " + cdTitle.value + "\n" +
        "Genre: " + cdGenre.value + "\n" +
        "Duration: " + cdDuration.value + "\n" +
        "No. of Tracks: " + tracks.value + "\n" +
        "Contributing Artists: " + artists.value);

    if (confirmation) {
        cdObject = {
            "title": cdTitle.value,
            "genre": cdGenre.value,
            "duration": cdDuration.value,
            "noOfTracks": tracks.value,
            "contributingArtists": artists.value,
            "id": id
        };

        cdListObj[id] = cdObject;
        cdListStr = JSON.stringify(cdListObj);
        setStringObjectOnCookie("cdlist", cdListStr);
        setStringObjectOnCookie("cdID", "");
    }
}
