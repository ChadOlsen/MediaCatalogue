package app.item;

import app.Main;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;
import java.util.List;

/**
 * The CD class that extends from the abstract Item class.
 * This class contains functionality to create a new CD object to add to the catalogue as well as to print a list of
 * a CD objects attributes to the console it also contains functionality to set and retrieve the values of the attributes.
 * This class also contains methods to set field values as well as return field values.
 *
 * @author Chad Olsen
 * @since 2017/04/19.
 */
@JsonTypeName("CD")
public class CD extends Item {

    private int tracks; // Number of tracks on CD for CD object.
    private List<String> artists = new ArrayList<>(); // List of names of contributing artists on CD for CD object.

    public CD(String title, String genre, String duration, int id, int tracks) {
        super(title, genre, duration, id, Type.CD);
        this.tracks = tracks;
    }

    public CD() {
        setType(Type.CD);
    }

    public void addArtist(String artist) {
        this.artists.add(artist);
    }

    @JsonGetter("tracks")
    private int getTracks() {
        return tracks;
    }

    @JsonSetter("tracks")
    public void setTracks(int tracks) {
        this.tracks = tracks;
    }

    @JsonGetter("artists")
    public List<String> getArtists() {
        return artists;
    }

    @JsonSetter("artists")
    public void setArtists(List<String> artists) {
        this.artists = artists;
    }


    /**
     * This method will return a string to print out each CD objects fields as a list, it also calls the toString() method
     *
     * @ return String This returns the string to print to the console.
     */
    @Override
    public String printItem() {
        return toString();
    }

    /**
     * This method creates a new CD object by taking input from the scanner
     * This new object is to be added to the list of CDs in the catalogue.
     *
     * @return CD This method returns the new CD object.
     */
    @Override
    public CD addItem() {
        System.out.print("Enter title for new CD:");
        String cdTitle = Main.scanner.nextLine();

        while ("".equals(cdTitle)) {
            System.out.println("Invalid CD Title....");
            System.out.print("Enter title for new DVD:");
            cdTitle = Main.scanner.nextLine();
        }

        System.out.print("Enter Genre for new CD:");
        String cdGenre = Main.scanner.nextLine();

        while ("".equals(cdGenre)) {
            System.out.println("Invalid CD Genre....");
            System.out.print("Enter title for new DVD:");
            cdGenre = Main.scanner.nextLine();
        }

        System.out.print("Enter Duration for new CD:");
        String cdDuration = Main.scanner.nextLine();

        while ("".equals(cdDuration)) {
            System.out.println("Invalid CD Duration....");
            System.out.print("Enter title for new DVD:");
            cdDuration = Main.scanner.nextLine();
        }

        int newCDTracks;
        System.out.print("Enter number of tracks for new CD:");
        while (!Main.scanner.hasNextInt()) {
            System.out.println("Please enter a valid number:");
            Main.scanner.next();
        }
        newCDTracks = Main.scanner.nextInt();
        setTracks(newCDTracks);

        CD newCD = new CD(cdTitle, cdGenre, cdDuration, 1, newCDTracks);

        int numArtists;
        System.out.print("Enter number of contributing artists for new CD:");
        while (!Main.scanner.hasNextInt()) {
            System.out.println("Please enter a valid number:");
            Main.scanner.next();
        }
        numArtists = Main.scanner.nextInt();
        Main.scanner.nextLine();

        for (int i = 0; i < numArtists; i++) {
            System.out.print("Enter name of contributing Artist:");
            String newArtist = Main.scanner.nextLine();
            newCD.addArtist(newArtist);
        }

        System.out.println("New CD is added to catalogue");
        return newCD;
    }

    /**
     * This method creates a string to list each field for the CD object as well as the list of artist names for each CD object.
     *
     * @return String This method returns the created string
     */
//    @Override
//    public String toString() {
//        StringBuilder printName = new StringBuilder();
//        Iterator iterator = this.artists.listIterator();
//        printName.append("[");
//        while (iterator.hasNext()) {
//            printName.append("\"");
//            printName.append(iterator.next());
//            printName.append("\"");
//
//            if (iterator.hasNext()){
//                printName.append(",");
//            }
//        }
//        printName.append("]");
//
//        return ("{\"cdTitle\": \"" + this.getTitle() + "\",\"cdGenre\": \"" + this.getGenre() + "\",\"cdDuration\": \"" + this.getDuration() +
//                "\",\"tracks\": " + this.getTracks() + ",\"artists\": " + printName + ",\"cdId\": " + this.getId() + "}");
//    }
}
