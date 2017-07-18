package app.item;

import app.catalogue.Catalogue;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class will test the functionality of the toString method for the CD object.
 *
 * @author chad_olsen
 * @since 2017/06/28.
 */
public class CDTest {
    @Test
    public void toStringTest() throws Exception {
        CD cd = new CD("Now 45", "Pop", "1hr30mins",1, 12);
        cd.addArtist("Bob");
        cd.addArtist("Sam");

        String catalogueString = ("Album name: Now 45\n" +
                "Album Genre: Pop\n" +
                "Duration: 1hr30mins\n" +
                "Number of tracks: 12\n" +
                "Contributing Artists: " + "\n" +
                "========================" + "\n" +
                "Bob\n" +
                "Sam\n" +
                "\n========================");

        assertEquals(catalogueString, cd.toString());
    }

    @Test
    public void toString_nullTest() {
        CD cd = new CD();

        String catalogueString = ("Album name: null\n" +
                "Album Genre: null\n" +
                "Duration: null\n" +
                "Number of tracks: 0\n" +
                "Contributing Artists: " + "\n" +
                "========================" + "\n" +
                "\n========================");

        Catalogue catalogue = new Catalogue();
        catalogue.addItemToList(cd);

        assertEquals(catalogueString, cd.toString());
    }

}