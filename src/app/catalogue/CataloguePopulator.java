package app.catalogue;

import app.item.*;

/**
 * This class will populate the Catalogue if no file exists to populate the Catalogue.
 *
 * @author Chad Olsen.
 * @since 2017/05/10.
 */
public class CataloguePopulator {

    /**
     * This method will populate the fields for each Item object and add the objects to the Catalogue.
     *
     * @return Catalogue This will return a Catalogue object to be written to a file.
     */
    public static Catalogue populateCatalogue() {
        Catalogue catalogue = new Catalogue();

        CD cd1 = new CD("Now 45", "Pop", "1hr30mins", 12);
        cd1.addArtist("Bob");
        cd1.addArtist("Sam");

        CD cd2 = new CD();
        cd2.setTitle("Top Hits");
        cd2.setGenre("Rock");
        cd2.setDuration("1hr45mins");
        cd2.setTracks(10);
        cd2.setType(Type.CD);
        cd2.addArtist("AC/DC");
        cd2.addArtist("Aerosmith");

        Item dvd1 = new DVD("Titanic", "Drama", "2hrs",
                "Leonardo di Caprio", "Kate Winslet");

        DVD dvd2 = new DVD();
        dvd2.setTitle("iRobot");
        dvd2.setGenre("Action");
        dvd2.setDuration("1hr23mins");
        dvd2.setType(Type.DVD);
        dvd2.setLeadActor("Will Smith");
        dvd2.setLeadActress("Jane Doe");

        catalogue.addItemToList(cd1);
        catalogue.addItemToList(cd2);
        catalogue.addItemToList(dvd1);
        catalogue.addItemToList(dvd2);

        return catalogue;
    }
}
