package app.catalogue;

import app.item.CD;
import app.item.DVD;
import app.item.Item;
import app.item.Type;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * This class tests all the methods within the Catalogue class that modify the data of the catalogue.
 *
 * @author chad_olsen
 * @since 2017/06/02.
 */
public class CatalogueTest {
    private Catalogue catalogue;
    private Item cd = new CD("80s music", "Pop", "1hr23mins", 1, 10);
    private Item dvd = new DVD("Sponge Bob", "Animation", "2hrs", 1, "Sponge Bob", "Sandy");

    @Before
    public void setup() {
        catalogue = new Catalogue();
    }

    @Test
    public void addItemToListTest() throws Exception {
        catalogue.addItemToList(new CD());
        catalogue.addItemToList(cd);
        assertEquals(2, catalogue.getCatalogueMap().get(Type.CD).size());

        catalogue.addItemToList(new DVD());
        catalogue.addItemToList(dvd);
        assertEquals(2, catalogue.getCatalogueMap().get(Type.DVD).size());
    }

    @Test
    public void addItemToList_nullTest() {
        catalogue.addItemToList(null);
        assertNull(catalogue.getCatalogueMap().get(Type.CD));
        assertNull(catalogue.getCatalogueMap().get(Type.DVD));
    }

    @Test
    public void searchByTitleTest() {
        catalogue.addItemToList(cd);
        List<Item> cds = catalogue.getCatalogueMap().get(new CD().getType());
        List<Item> newList = Catalogue.searchByTitle(cds, "80");
        assertEquals(cds, newList);

        catalogue.addItemToList(dvd);
        List<Item> dvds = catalogue.getCatalogueMap().get(new DVD().getType());
        newList = Catalogue.searchByTitle(dvds, "bob");
        assertEquals(dvds, newList);
    }

    @Test
    public void searchByTitle_nullTest() {
        assertNotNull(Catalogue.searchByTitle(null, ""));

        List<Item> list = new ArrayList<>();
        list.add(null);
        list.add(new CD("title", "genre", "length", 1, 10));

        assertTrue(Catalogue.searchByTitle(list, "tit").size() == 1);
        assertTrue(Catalogue.searchByTitle(list, null).size() == 0);
        assertTrue(Catalogue.searchByTitle(list, "").size() == 0);
    }

    @Test
    public void deleteItemTest() throws Exception {
        catalogue.addItemToList(cd);
        catalogue.deleteItem(cd.getType(), 1);
        List<Item> cds = catalogue.getCatalogueMap().get(new CD().getType());
        assertEquals(0, cds.size());

        catalogue.addItemToList(dvd);
        catalogue.addItemToList(new DVD());
        catalogue.deleteItem(new DVD().getType(), 2);
        List<Item> dvds = catalogue.getCatalogueMap().get(new DVD().getType());
        assertEquals(1, dvds.size());
    }

    @Test
    public void deleteItem_nullTest() {
        assertNull(catalogue.getCatalogueMap().get(Type.CD));
        assertNull(catalogue.getCatalogueMap().get(Type.DVD));

        catalogue.deleteItem(null, 0);
        catalogue.deleteItem(cd.getType(), 1);
    }
}