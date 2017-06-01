import java.io.Serializable;

/**
 * The abstract Item class.
 * This class contains functionality to create a new Item object to add to the catalogue as well as to print a list of
 * a Item objects attributes to the console.
 * This class also contains methods to set field values as well as return field values.
 *
 * @author Chad Olsen
 * @since 2017/04/20.
 */
public abstract class Item implements Serializable {

    private long serialVersionUID = 2L;

    private String title; // Name/Title of Item object.
    private String genre; // Type of Genre for item object.
    private String duration; // Duration of item object.
    private Type type; // Type of item object.(CD or DVD item).

    public Item(String title, String genre, String duration, Type type) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.type = type;
    }

    public Item() {

    }

    /**
     * This abstract method is to be implemented by the DVD and CD classes.
     * This method returns a string to be printed by the console for each item
     *
     * @return String This returns the string to be printed
     */
    public abstract String printItem();

    /**
     * This abstract method is to be implemented by the DVD and CD classes.
     * This method creates an Item object to be added in the Catalogue.
     *
     * @return Item This returns the Item object created
     */
    public abstract Item addItem();


    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    String getGenre() {
        return genre;
    }

    void setGenre(String genre) {
        this.genre = genre;
    }

    String getDuration() {
        return duration;
    }

    void setDuration(String duration) {
        this.duration = duration;
    }

}
