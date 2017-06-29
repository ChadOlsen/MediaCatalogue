package app.data;

import app.catalogue.Catalogue;
import app.catalogue.CataloguePopulator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class will test the functionality of the read and write functions to save a catalogue to a file called Catalogue.dat
 *
 * @author chad_olsen
 * @since 2017/06/02.
 */
public class MemoryHandlerTest {
    private MemoryHandler memoryHandler;

    @Before
    public void setup() {
        memoryHandler = new MemoryHandler();
        memoryHandler.writeCatalogue(new Catalogue());
    }

    @Test
    public void writeCatalogueTest() throws Exception {
        memoryHandler.readCatalogue();
        assertTrue(memoryHandler.getCatalogue().getCatalogueMap().isEmpty());

        memoryHandler.writeCatalogue(CataloguePopulator.populateCatalogue());
        memoryHandler.readCatalogue();
        assertFalse(memoryHandler.getCatalogue().getCatalogueMap().isEmpty());
    }

    @Test
    public void readCatalogue_nullTest() throws Exception {
        memoryHandler.writeCatalogue(null);

        memoryHandler.readCatalogue();
        assertTrue(memoryHandler.getCatalogue().getCatalogueMap().isEmpty());
    }

    @Test
    public void readCatalogueTest() throws Exception {
        memoryHandler.readCatalogue();
        assertNotNull(memoryHandler.getCatalogue());
    }
}