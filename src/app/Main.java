package app;

import app.account.Account;
import app.account.AccountManager;
import app.catalogue.Catalogue;
import app.catalogue.CataloguePopulator;
import app.data.MemoryHandler;
import app.item.CD;
import app.item.DVD;
import app.item.Item;
import app.item.Type;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * This Main class runs and executes the program Media Catalogue.
 *
 * @author Chad Olsen.
 * @since 2017/04/19.
 */
public class Main {

    public static Scanner scanner = new Scanner(System.in);
    private static Catalogue catalogue;
    private static AccountManager accountManager = new AccountManager();
    private static Map<String, Account> registeredAccounts;

    //after asking to add a new account and then entering incorrect admin values, it still
    //loads a catalogue.
    //admin account seems to be overwritten.
    public static void main(String[] args) {

        boolean quit = false;

        while (!quit) {
            logInScreen();
            accountManager.readAccount();
            registeredAccounts = accountManager.getRegisteredAccounts();

            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    accountChecker();

                    boolean logout = false;
                    while (!logout) {
                        printOptions();

                        option = scanner.nextInt();
                        switch (option) {
                            case 1:
                                catalogue.printCatalogue();
                                break;
                            case 2:
                                System.out.println("========================");
                                System.out.println("\t\tCDs");
                                System.out.println("========================");
                                for (Item item : catalogue.getCatalogueMap().get(Type.CD)) {
                                    System.out.println(item.printItem());
                                }
                                break;
                            case 3:
                                System.out.println("========================");
                                System.out.println("\t\tDVDs");
                                System.out.println("========================");
                                for (Item item : catalogue.getCatalogueMap().get(Type.DVD)) {
                                    System.out.println(item.printItem());
                                }
                                break;
                            case 4:
                                addItem();

                                option = scanner.nextInt();
                                scanner.nextLine();
                                switch (option) {
                                    case 1:
                                        DVD newDVD = new DVD();
                                        catalogue.addItemToList(newDVD.addItem());
                                        break;
                                    case 2:
                                        CD newCD = new CD();
                                        catalogue.addItemToList(newCD.addItem());
                                        break;
                                }
                                break;
                            case 5:
                                deleteItem();

                                option = scanner.nextInt();
                                scanner.nextLine();
                                switch (option) {
                                    case 1:
                                        DVD dvd = new DVD();
                                        removeItem(dvd);
                                        catalogue.deleteItem(dvd, option);
                                        break;
                                    case 2:
                                        CD cd = new CD();
                                        removeItem(cd);
                                        catalogue.deleteItem(cd, option);
                                        break;
                                }
                                break;
                            case 6:
                                searchItem();

                                option = scanner.nextInt();
                                scanner.nextLine();
                                switch (option) {
                                    case 1:
                                        searchForDVD();

                                        option = scanner.nextInt();
                                        scanner.nextLine();
                                        catalogue.searchDVD(option);
                                        break;
                                    case 2:
                                        searchForCD();

                                        option = scanner.nextInt();
                                        scanner.nextLine();
                                        catalogue.searchCD(option);
                                        break;
                                }
                                break;
                            case 7:
                                MemoryHandler.writeCatalogue(catalogue);
                                accountManager.writeToAccount(registeredAccounts);
                                catalogue.printGoodBye();
                                logout = true;
                                break;
                        }
                    }
                    break;
                case 2:
                    System.out.println("Exiting the application....");
                    quit = true;
                    break;
            }
        }
    }
    private static void createNewAccount(){
        System.out.println("Would you like to add a new account?\n" +
                "========================\n"+
                "1. Yes\n"+
                "2. No\n" +
                "========================");
        System.out.println("Enter option: ");
    }

    private static void accountChecker() {
        if (registeredAccounts.isEmpty()) {
            System.err.println("Setting up system for first use...");
            System.out.println("Please enter an admin username to continue: ");
            String userName = scanner.nextLine();
            System.out.println(userName + ", Please enter your password to continue:");
            String userPassword = scanner.nextLine();
            registeredAccounts.put(userName, new Account(userName, userPassword, new Catalogue()));

            catalogue = MemoryHandler.readCatalogue();

            if (catalogue == null) {
                catalogue = CataloguePopulator.populateCatalogue();
                MemoryHandler.writeCatalogue(catalogue);
            }
            System.out.println("========================================" + "\n" +
                    "Welcome to your catalogue, " + userName + "\n" +
                    "========================================");
        } else {
            System.out.println("Please enter an admin username to continue: ");
            String userName = scanner.nextLine();
            System.out.println(userName + ", Please enter your password to continue:");
            String userPassword = scanner.nextLine();

            Account account = registeredAccounts.get(userName);
            while (account == null || !account.getUserName().equals(userName) || !account.getUserPassword().equals(userPassword)) {
                System.err.println("User account does not exist...");
                createNewAccount();
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice){
                    case 1:
                        System.out.println("Please enter an admin username to continue: ");
                        userName = scanner.nextLine();
                        System.out.println(userName + ", Please enter your password to continue:");
                        userPassword = scanner.nextLine();
                        registeredAccounts.put(userName, new Account(userName, userPassword, new Catalogue()));
                        account = registeredAccounts.get(userName);
                        break;
                    case 2:
                        System.out.println("Please enter an admin username to continue: ");
                        userName = scanner.nextLine();
                        System.out.println(userName + ", Please enter your password to continue:");
                        userPassword = scanner.nextLine();
                }
            }
            System.err.println("Loading user account...");

            catalogue = MemoryHandler.readCatalogue();

            if (catalogue == null) {
                catalogue = CataloguePopulator.populateCatalogue();
                MemoryHandler.writeCatalogue(catalogue);
            }
            System.out.println("========================================" + "\n" +
                    "Welcome to your catalogue, " + userName + "\n" +
                    "========================================");
        }
    }

    private static void searchItem() {
        System.out.println("WHAT ITEM DO YOU WANT TO SEARCH FOR?\n" +
                "========================================\n" +
                "1. DVD\n" +
                "2. CD\n" +
                "========================================");
    }

    private static void searchForDVD() {
        System.out.println("What do you want to search by?\n" +
                "========================================\n" +
                "1. Movie Title\n" +
                "2. Actor\n" +
                "========================================");
        System.out.println("Enter option: ");
    }

    private static void searchForCD() {
        System.out.println("What do you want to search by?\n" +
                "========================================\n" +
                "1. Album Title\n" +
                "2. Artist\n" +
                "========================================\n");
        System.out.println("Enter option: ");
    }

    private static void removeItem(Item item) {
        List<Item> list = catalogue.getCatalogueMap().get(item.getType());
        if (list.isEmpty()) {
            System.out.println("There are no more items to delete...");
            return;
        }
        System.out.println("Select item to delete: ");
        for (Item itemInList : list) {
            System.out.println((list.indexOf(itemInList) + 1) + ") Title: " + itemInList.getTitle() +
                    "  Duration: " + itemInList.getDuration());
        }
        System.out.print("\n" + "Choose the index of the item you want to delete: ");
        scanner.nextInt();
        scanner.nextLine();
    }

    private static void addItem() {
        System.out.println("========================================" + "\n" +
                "\t" + "WHAT ITEM DO YOU WANT TO ADD" + "\n" +
                "========================================" + "\n" +
                "1. DVD" + "\n" +
                "2. CD" + "\n" +
                "========================================");
        System.out.println("Enter option: ");

    }

    private static void deleteItem() {
        System.out.println("========================================" + "\n" +
                "\t" + "WHAT ITEM DO YOU WANT TO DELETE" + "\n" +
                "========================================" + "\n" +
                "1. DVD" + "\n" +
                "2. CD" + "\n" +
                "========================================");
        System.out.println("Enter option: ");
    }

    private static void printOptions() {
        System.out.println("========================================" + "\n" +
                "\t\tDVD & CD Catalogue" + "\n" +
                "========================================" + "\n" +
                "1. Show all items in the catalogue" + "\n" +
                "2. Show all CD items" + "\n" +
                "3. Show all DVD items" + "\n" +
                "4. Add item to catalogue" + "\n" +
                "5. Delete item from catalogue" + "\n" +
                "6. Search for item in the catalogue" + "\n" +
                "7. Log out" + "\n" +
                "========================================");
        System.out.println("Enter option: ");
    }

    private static void logInScreen() {
        System.out.println("\tWelcome to the media Catalogue\n" +
                "========================================\n" +
                "1. Log in\n" +
                "2. Exit\n" +
                "========================================");
        System.out.println("Enter option: ");

    }
}
