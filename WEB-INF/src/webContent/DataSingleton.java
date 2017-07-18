package webContent;

import app.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * @author colsen@sfy.co.za
 * @since 18-Jul-17.
 */

public class DataSingleton {
    private static List<Item> cdList;
    private static List<Item> dvdList;
    private static WebContext webContext;

    private static final DataSingleton instance = new DataSingleton();

    private DataSingleton() {
        cdList = new ArrayList<>();
        dvdList = new ArrayList<>();
        webContext = new WebContext();
    }

    public static DataSingleton getInstance() {
        return instance;
    }

    public List<Item> getCdList() {
        return cdList;
    }

    public void setCdList(List<Item> cdList) {
        DataSingleton.cdList = cdList;
    }

    public List<Item> getDvdList() {
        return dvdList;
    }

    public void setDvdList(List<Item> dvdList) {
        DataSingleton.dvdList = dvdList;
    }

    public WebContext getWebContext() {
        return webContext;
    }

    public void setWebContext(WebContext webContext) {
        DataSingleton.webContext = webContext;
    }
}