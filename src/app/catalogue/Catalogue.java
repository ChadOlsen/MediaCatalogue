package app.catalogue;

import java.io.*;
import java.util.*;
import app.item.*;
import app.Main;


/**
 * Created by TwiceAsNice on 2017/04/19.
 */
public class Catalogue implements Serializable {

    private long serialVersionUID = 1L; // Catalogue object Serialization ID.

    private Map<Type, List<Item>> catalogueMap = new LinkedHashMap<>(); // Collection of catalogue items as a list organised by type.

    public Catalogue() {
    }

    public Map<Type, List<Item>> getCatalogueMap() {
        return catalogueMap;
    }

    /**
     * This method adds a new Item to a list og items in the Catalogue map.
     *
     * @param item This is the new Item object to be added to the Catalogue map list.
     */
    public void addItemToList(Item item) {
        List<Item> list = this.catalogueMap.get(item.getType());

        if (list == null) {
            list = new ArrayList<>();
            this.catalogueMap.put(item.getType(), list);
        }

        list.add(item);
    }

    /**
     * This method prints out an error message to the console if the user has entered incorrect input.
     */
    private static void invalidEntry() {
        System.out.println("INVALID!! Please try again...");
    }

    /**
     * This method prints an output of results done by a search method and returns all the items that meet the search
     * criteria or it will print a message stating no matches meet the criteria.
     *
     * @param count  This parameter is the number of search results found by the search method.
     * @param output This parameter is the list of items that is to be printed to show the results that met the criteria.
     */
    private static void printSearchResults(int count, String output) {
        System.out.println("\t\tSEARCH RESULTS\n" +
                "Results found: " + count + "\n");

        if (count == 0) {
            System.out.println("There are no matches with that criteria...");
        } else {
            System.out.println(output);
        }
    }

    /**
     * This method allows the user to search for an Item by its Title and return a list of the items that meet the
     * criteria.
     *
     * @param list        This parameter is the list that the criteria needs to search through.
     * @param titleSearch This is the input criteria the uses to search through the list of items.
     * @return List This method returns a new list of all the Item objects that met the search criteria.
     */
    private static List<Item> searchByTitle(List<Item> list, String titleSearch) {
        List<Item> newItem = new ArrayList<>();

        for (Item item : list) {
            if (item.getTitle().contains(titleSearch)) {
                newItem.add(item);
            }
        }

        return newItem;
    }

    /**
     * This method takes user input to decide whether to search for a DVD Item by title or by the name of an Actor or
     * Actress. It implements the searchByTitle(), invalidEntry() and printSearchResults() methods and prints the results
     * of the search.
     *
     * @param choice This is the user input to decide on how to do the search.
     */
    public void searchDVD(int choice) {
        String output = "";
        String newString;
        int count = 0;
        boolean isValid = false;

        if (choice == 1) {
            while (!isValid) {
                System.out.println("Enter Movie title to search: ");
                newString = Main.scanner.nextLine().toLowerCase();

                if (newString.isEmpty()) {
                    invalidEntry();
                    continue;
                }

                try {
                    List<Item> newList = searchByTitle(getCatalogueMap().get(Type.DVD), newString);
                    count = newList.size();

                    for (Item dvd : newList) {
                        output += dvd.toString() + "\n";
                        isValid = true;
                    }
                } catch (InputMismatchException e) {
                    invalidEntry();
                }

                printSearchResults(count, output);
            }
        } else if (choice == 2) {
            while (!isValid) {
                System.out.println("Enter Actor/Actress to search: ");
                newString = Main.scanner.nextLine();

                if (newString.length() == 0) {
                    invalidEntry();
                    continue;
                }

                try {
                    for (Item item : getCatalogueMap().get(Type.DVD)) {
                        DVD dvd = (DVD) item;
                        if (newString.equalsIgnoreCase(dvd.getLeadActor()) || newString.equalsIgnoreCase(dvd.getLeadActress())) {
                            output += dvd.toString();
                            count++;
                            isValid = true;
                        }
                    }
                } catch (InputMismatchException e) {
                    invalidEntry();
                }

                printSearchResults(count, output);
            }
        }
    }

    /**
     * This method takes user input to decide whether to search for a DVD Item by title or by the name of an Actor or
     * Actress. It implements the searchByTitle(), invalidEntry() and printSearchResults() methods and prints the results
     * of the search.
     *
     * @param choice This is the user input to decide on how to do the search.
     */
    public void searchCD(int choice) {
        String output = "";
        String newString;
        boolean isValid = false;
        int count = 0;

        if (choice == 1) {
            while (!isValid) {
                System.out.println("Enter Album title to search: ");
                newString = Main.scanner.nextLine().toLowerCase();

                if (newString.length() == 0) {
                    invalidEntry();
                    continue;
                }

                try {
                    List<Item> newList = searchByTitle(getCatalogueMap().get(Type.CD), newString);
                    count = newList.size();

                    for (Item cd : newList) {
                        output += cd.toString() + "\n";
                        isValid = true;
                    }
                } catch (InputMismatchException e) {
                    invalidEntry();
                }

                printSearchResults(count, output);
            }
        } else if (choice == 2) {
            while (!isValid) {
                System.out.println("Enter album artist to search: ");
                newString = Main.scanner.nextLine().toLowerCase();

                if (newString.isEmpty()) {
                    invalidEntry();
                    continue;
                }

                try {
                    for (Item item : getCatalogueMap().get(Type.CD)) {
                        CD cd = (CD) item;
                        for (int i = 0; i < cd.getArtists().size(); i++) {
                            if (newString.equalsIgnoreCase(cd.getArtists().get(i))) {
                                output += cd.toString() + "\n";
                                count++;
                                isValid = true;
                            }
                        }
                    }
                } catch (InputMismatchException e) {
                    invalidEntry();
                }

                printSearchResults(count, output);
            }
        }
    }

    /**
     * This method locates an Item object and deletes it from the Map list of that type object.
     *
     * @param item  This is the DVD type object that needs to be deleted from the list.
     * @param index This is the index of the item in the list to be deleted.
     */
    public void deleteItem(Item item, int index) {
        List<Item> list = this.catalogueMap.get(item.getType());
        if (list.isEmpty()) {
            System.out.println("Could not delete the item, index does not exist!\n" +
                    "There are only " + list.size() + " items available in the Catalogue, you tried to delete number " + index);
            return;
        }
        if ((index - 1) >= 0 && index <= list.size()) {
            System.out.println(list.get(index - 1).getTitle() + " was removed from the list." + "\n" +
                    "========================================");
            list.remove(index - 1);
        }
    }

    /**
     * This method prints all the items presently saved in the Catalogue to the console as a list of each type of Item.
     */
    public void printCatalogue() {
        System.out.println("========================================" + "\n" +
                "\t\t\t" + "Catalogue Items" + "\n" +
                "========================================");
        for (Item cd : this.catalogueMap.get(Type.DVD)) {
            System.out.println(cd.printItem());
        }
        for (Item dvd : this.catalogueMap.get(Type.CD)) {
            System.out.println(dvd.printItem());
        }
    }

    /**
     * This method prints a Goodbye message when the user enters an input to exit the program.
     */
    public void printGoodBye() {
        System.out.println("========================");
        System.out.println("GOOD BYE FOR NOW....");
        System.out.println("========================");
    }

}
