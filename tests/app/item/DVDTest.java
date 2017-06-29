package app.item;

import app.catalogue.Catalogue;
import app.catalogue.CataloguePopulator;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class will test the functionality of the toString method for the DVD object.
 *
 * @author chad_olsen
 * @since 2017/06/28.
 */
public class DVDTest {
    @Test
    public void toStringTest() throws Exception {
        Item dvd = new DVD("Titanic", "Drama", "2hrs", "Leonardo di Caprio", "Kate Winslet");

        String catalogueString = "Movie title: Titanic\n" +
                "Movie Genre: Drama\n" +
                "Duration: 2hrs\n" +
                "Lead Actor: Leonardo di Caprio\n" +
                "Lead Actress: Kate Winslet\n" +
                "========================";

        assertEquals(catalogueString, dvd.toString());
    }

    @Test
    public void toString_nullTest() {
        DVD dvd = new DVD();

        String catalogueString = "Movie title: null\n" +
                "Movie Genre: null\n" +
                "Duration: null\n" +
                "Lead Actor: null\n" +
                "Lead Actress: null\n" +
                "========================";

        assertEquals(catalogueString, dvd.toString());
    }

}