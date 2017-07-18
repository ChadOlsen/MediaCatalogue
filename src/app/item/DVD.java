package app.item;

import app.Main;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * The DVD class that extends from the abstract Item class.
 * This class contains functionality to create a new DVD object to add to the catalogue as well as to print a list of
 * a DVD objects attributes to the console it also contains functionality to set and retrieve values of the attributes.
 * This class also contains methods to set field values as well as return field values.
 *
 * @author Chad Olsen
 * @since 2017/04/19.
 */
@JsonTypeName("DVD")
public class DVD extends Item {

    private String leadActor; // Lead actor of the film for DVD object.
    private String leadActress; // Lead actress of film for DVD object.

    public DVD(String title, String genre, String duration, int id, String leadActor, String leadActress) {
        super(title, genre, duration, id, Type.DVD);
        this.leadActor = leadActor;
        this.leadActress = leadActress;
    }

    public DVD() {
        setType(Type.DVD);
    }

    @JsonGetter("leadActor")
    public String getLeadActor() {
        return leadActor;
    }

    @JsonSetter("leadActor")
    public void setLeadActor(String leadActor) {
        this.leadActor = leadActor;
    }

    @JsonGetter("leadActress")
    public String getLeadActress() {
        return leadActress;
    }

    @JsonSetter("leadActress")
    public void setLeadActress(String leadActress) {
        this.leadActress = leadActress;
    }

    /**
     * This method will return a string to print out each DVD objects fields as a list, it also calls the toString() method
     *
     * @return String This returns the string to print to the console.
     */
    @Override
    public String printItem() {
        return toString();
    }

    /**
     * This method creates a new DVD object by taking input from the scanner
     * This new object is to be added to the list of DVDs in the catalogue.
     *
     * @return DVD This method returns the new DVD object.
     */
    @Override
    public DVD addItem() {
        System.out.print("Enter title for new DVD:");
        String dvdTitle = Main.scanner.nextLine();

        while ("".equals(dvdTitle)) {
            System.out.println("Invalid DVD Title....");
            System.out.print("Enter title for new DVD:");
            dvdTitle = Main.scanner.nextLine();
        }

        System.out.print("Enter Genre for new DVD:");
        String dvdGenre = Main.scanner.nextLine();

        while ("".equals(dvdGenre)) {
            System.out.println("Invalid DVD Genre....");
            System.out.print("Enter title for new DVD:");
            dvdGenre = Main.scanner.nextLine();
        }

        System.out.print("Enter Duration for new DVD:");
        String dvdDuration = Main.scanner.nextLine();

        while ("".equals(dvdDuration)) {
            System.out.println("Invalid DVD Duration....");
            System.out.print("Enter title for new DVD:");
            dvdDuration = Main.scanner.nextLine();
        }

        System.out.print("Enter Lead Actor for new DVD:");
        String leadActor = Main.scanner.nextLine();
        System.out.print("Enter Lead Actress for new DVD:");
        String leadActress = Main.scanner.nextLine();

        System.out.println("New DVD is added to catalogue");
        return new DVD(dvdTitle, dvdGenre, dvdDuration, 1,leadActor, leadActress);
    }

    /**
     * This method creates a string to list each field for the DVD object.
     *
     * @return String This method returns the created string
     */
//    @Override
//    public String toString() {
//        return ("{\"dvdTitle\": \"" + this.getTitle() + "\",\"dvdGenre\": \"" + this.getGenre() + "\",\"dvdDuration\": \"" + this.getDuration() +
//                "\",\"leadActor\": \"" + this.getLeadActor() + "\",\"leadActress\": \"" + this.getLeadActress() + "\",\"dvdId\": " + this.getId() + "}");
//    }
}
