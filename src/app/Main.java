package app;

import app.account.Account;
import app.account.AccountManager;
import app.catalogue.Catalogue;
import app.catalogue.CataloguePopulator;
import app.data.DatabaseConnection;
import app.item.CD;
import app.item.DVD;
import app.item.Item;
import app.item.Type;

import java.util.*;

/**
 * This Main class runs and executes the program Media Catalogue. And provides functionality to control the program.
 *
 * @author Chad Olsen.
 * @since 2017/04/19.
 */
public class Main {

    public static Scanner scanner = new Scanner(System.in);
    private static Catalogue catalogue;
    private static AccountManager accountManager = new AccountManager();
    private static Map<String, Account> registeredAccounts;
    private static Set registeredUserNames;
    private static DatabaseConnection databaseConnection = new DatabaseConnection();

    public static void main(String[] args) {
        databaseConnection.connectToDatabase();
        boolean quit = false;

        while (!quit) {
            logInScreen();
            accountManager.readAccount();
            registeredAccounts = accountManager.getRegisteredAccounts();

            int option;
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid option number");
                scanner.next();
            }
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    Account userAccount = accountChecker();
                    if (userAccount == null) {
                        menuOptions(true, userAccount);
                    } else {
                        menuOptions(false, userAccount);
                    }
                    break;
                case 2:
                    try {
                        userAccount = registerAccount();
                        if (userAccount == null) {
                            menuOptions(true, userAccount);
                        } else {
                            menuOptions(false, userAccount);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("=============================");
                        System.err.println("Could not create account: Admin credentials are incorrect...");
                        System.out.println("=============================");
                    }
                    break;
                case 3:
                    System.out.println("Exiting the application....");
                    quit = true;
                    break;
            }
        }
    }

    private static void menuOptions(boolean logout, Account userAccount) {
        while (!logout) {
            printOptions();

            int option;
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid option number:");
                scanner.next();
            }
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    catalogue.printCatalogue();
                    break;
                case 2:
                    System.out.println("========================");
                    System.out.println("\t\tCDs");
                    System.out.println("========================");
                    if (catalogue.getCatalogueMap().get(Type.CD).isEmpty()) {
                        System.out.println("There are no CD items in the Catalogue to display..\n");
                    } else {
                        for (Item cd : catalogue.getCatalogueMap().get(Type.CD)) {
                            System.out.println(cd.printItem());
                        }
                    }
                    break;
                case 3:
                    System.out.println("========================");
                    System.out.println("\t\tDVDs");
                    System.out.println("========================");
                    if (catalogue.getCatalogueMap().get(Type.DVD).isEmpty()) {
                        System.out.println("There are no DVD items in the Catalogue to display..\n");
                    } else {
                        for (Item cd : catalogue.getCatalogueMap().get(Type.DVD)) {
                            System.out.println(cd.printItem());
                        }
                    }
                    break;
                case 4:
                    addItem();

                    while (!scanner.hasNextInt()) {
                        System.out.println("Please enter a valid option number:");
                        scanner.next();
                    }
                    option = scanner.nextInt();
                    scanner.nextLine();
                    switch (option) {
                        case 1:
                            catalogue.addItemToList(new DVD().addItem());
                            break;
                        case 2:
                            catalogue.addItemToList(new CD().addItem());
                            break;
                    }
                    break;
                case 5:
                    deleteItem();

                    while (!scanner.hasNextInt()) {
                        System.out.println("Please enter a valid option number:");
                        scanner.next();
                    }
                    option = scanner.nextInt();
                    scanner.nextLine();
                    switch (option) {
                        case 1:
                            removeItem(Type.DVD);
                            while (!scanner.hasNextInt()) {
                                System.out.println("Please enter a valid option number:");
                                scanner.next();
                            }
                            option = scanner.nextInt();
                            scanner.nextLine();
                            catalogue.deleteItem(Type.DVD, option);
                            break;
                        case 2:
                            removeItem(Type.CD);
                            while (!scanner.hasNextInt()) {
                                System.out.println("Please enter a valid option number:");
                                scanner.next();
                            }
                            option = scanner.nextInt();
                            scanner.nextLine();
                            catalogue.deleteItem(Type.CD, option);
                            break;
                    }
                    break;
                case 6:
                    searchItem();

                    while (!scanner.hasNextInt()) {
                        System.out.println("Please enter a valid option number:");
                        scanner.next();
                    }
                    option = scanner.nextInt();
                    scanner.nextLine();
                    switch (option) {
                        case 1:
                            searchForDVD();

                            while (!scanner.hasNextInt()) {
                                System.out.println("Please enter a valid option number:");
                                scanner.next();
                            }
                            option = scanner.nextInt();
                            scanner.nextLine();
                            catalogue.searchDVD(option);
                            break;
                        case 2:
                            searchForCD();

                            while (!scanner.hasNextInt()) {
                                System.out.println("Please enter a valid option number:");
                                scanner.next();
                            }
                            option = scanner.nextInt();
                            scanner.nextLine();
                            catalogue.searchCD(option);
                            break;
                    }
                case 7:
                    linkCatalogueToAccount(userAccount);
                    break;
                case 8:
                    userAccount.setCatalogue(catalogue);
                    registeredAccounts.put(userAccount.getUserName(), userAccount);
                    System.out.println("===========================\n" +
                            "Catalogue saved successfully...");
                    accountManager.writeToAccount(registeredAccounts);
                    catalogue.printGoodBye();
                    logout = true;
                    break;
            }
        }
    }

    private static String enterLinkAccountUsername() {
        System.out.println("Please enter the username for the Account you want to link to your Catalogue: ");
        String username = scanner.nextLine();
        while ("".equals(username)) {
            System.out.println("Please enter the username for the Account you want to link to your Catalogue: ");
            username = scanner.nextLine();
        }
        return username;
    }

    private static String enterLinkAccountPassword(Account currentUser, String username) {
        System.out.println(currentUser.getUserName() + ", Please enter " + username + "'s password to continue:");
        String password = scanner.nextLine();
        while ("".equals(password)) {
            System.out.println(currentUser.getUserName() + ", Please enter " + username + "'s password to continue:");
            password = scanner.nextLine();
        }
        return password;
    }

    private static void linkCatalogueToAccount(Account currentUser) {
        String username = enterLinkAccountUsername();
        String password = enterLinkAccountPassword(currentUser, username);
        if (registeredAccounts.get(username) == null || !username.equals(registeredAccounts.get(username).getUserName())
                || !password.equals(registeredAccounts.get(username).getUserPassword())) {
            System.out.println("=================================\n" +
                    "Details entered are incorrect...\n" +
                    "That user does not exist...\n" +
                    "=================================");
            return;
        }
        registeredAccounts.get(username).setCatalogue(currentUser.getCatalogue());
        System.out.println("=================================\n" +
                "Successfully Linked  your catalogue to " + username + "'s account...\n" +
                "=================================");
    }

    private static void createNewAccount() {
        System.out.println("Would you like to add a new account?\n" +
                "========================\n" +
                "1. Yes\n" +
                "2. No\n" +
                "3. Return to login screen\n" +
                "========================");
        System.out.println("Enter option: ");
    }

    private static Account accountChecker() {

        String userName;
        String password;

        if (registeredAccounts == null) {
            registeredAccounts = new LinkedHashMap<>();
        }
        if (registeredAccounts.isEmpty()) {
            System.err.println("Setting up system for first use...");
            String adminUsername = enterUserName();
            while ("".equals(adminUsername)) {
                adminUsername = enterUserName();
            }
            String adminPassword = enterPassword(adminUsername);
            while ("".equals(adminPassword)) {
                adminPassword = enterPassword(adminUsername);
            }
            Account adminAccount = new Account(adminUsername, adminPassword, new Catalogue(), true);
            registeredAccounts.put(adminUsername, adminAccount);
            registeredUserNames = registeredAccounts.keySet();
            accountManager.writeToAccount(registeredAccounts);
            loadCatalogue(adminUsername);
            return adminAccount;
        } else {
            registeredUserNames = registeredAccounts.keySet();
            userName = enterUserName();
            while ("".equals(userName)) {
                System.err.println("Invalid username...");
                userName = enterUserName();
            }
            password = passwordValidation(userName);

            Account account = registeredAccounts.get(userName);

            while (!registeredUserNames.contains(userName) || !account.getUserPassword().equals(password)) {
                System.err.println("User account does not exist...");
                createNewAccount();
                int choice;
                while (!scanner.hasNextInt()) {
                    System.out.println("Please enter a valid option number:");
                    scanner.next();
                }
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        try {
                            account = registerAccount();
                            return account;
                        } catch (InputMismatchException e) {
                            System.out.println("=============================");
                            System.err.println("Could not create account: Admin credentials are incorrect...");
                            System.out.println("=============================");
                            return null;
                        }
                    case 2:
                        userName = enterUserName();
                        enterPassword(userName);
                        loadCatalogue(userName);
                        account = registeredAccounts.get(userName);
                        return account;
                    case 3:
                        return null;
                }
            }
            System.err.println("Loading user account...");
            loadCatalogue(userName);
            return account;
        }
    }

    private static Account registerAccount() {
        System.out.println("Please enter admin user name: ");
        String adminUsername = scanner.nextLine();
        while ("".equals(adminUsername)) {
            System.err.println("Invalid username...");
            System.out.println("Please enter admin user name: ");
            adminUsername = scanner.nextLine();
        }

        System.out.println(adminUsername + " ,Please enter your password: ");
        String adminPassword = scanner.nextLine();
        while ("".equals(adminPassword)) {
            System.out.println(adminUsername + " ,Please enter your password: ");
            adminPassword = scanner.nextLine();
        }

        Account adminAccount = registeredAccounts.get(adminUsername);
        if (adminAccount == null || !adminAccount.isAdminUser() || !adminUsername.equals(adminAccount.getUserName()) || !adminPassword.equals(adminAccount.getUserPassword())) {
            throw new InputMismatchException();
        } else {
            System.out.println("=============================");
            String username = enterUserName();
            String password = enterPassword(username);
            Account newUserAccount = new Account(username, password, new Catalogue(), false);
            registeredAccounts.put(username, newUserAccount);
            registeredUserNames = registeredAccounts.keySet();
            accountManager.writeToAccount(registeredAccounts);
            loadCatalogue(username);
            return newUserAccount;
        }
    }

    private static void loadCatalogue(String userName) {
        catalogue = registeredAccounts.get(userName).getCatalogue();
        if (catalogue.getCatalogueMap() == null || catalogue == null) {
            return;
        }
        if (catalogue.getCatalogueMap().isEmpty()) {
            System.out.println("================================================\n" +
                    "Your catalogue is empty, a default catalogue will be loaded...");
            catalogue = CataloguePopulator.populateCatalogue();
        }
        System.out.println("========================================" + "\n" +
                "Welcome to your catalogue, " + userName + "\n" +
                "========================================");
    }

    private static String passwordValidation(String userName) {
        String password = enterPassword(userName);
        while ("".equals(password)) {
            System.out.println("Invalid password...");
            password = enterPassword(userName);
        }
        return password;
    }

    private static String enterUserName() {
        System.out.println("Please enter an username to continue: ");
        return scanner.nextLine();
    }

    private static String enterPassword(String userName) {
        System.out.println(userName + ", Please enter your password to continue:");
        return scanner.nextLine();
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

    private static void removeItem(Type type) {
        List<Item> list = catalogue.getCatalogueMap().get(type);
        if (list == null || list.isEmpty()) {
            System.out.println("There are no more items to delete...");
            return;
        }
        System.out.println("Select item to delete: ");
        for (Item itemInList : list) {
            System.out.println((list.indexOf(itemInList) + 1) + ") Title: " + itemInList.getTitle() +
                    "  Duration: " + itemInList.getDuration());
        }
        System.out.print("\n" + "Choose the index of the item you want to delete: ");
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
                "7. Link Catalogue to another account.\n" +
                "8. Log out" + "\n" +
                "========================================");
        System.out.println("Enter option: ");
    }

    private static void logInScreen() {
        System.out.println("\tWelcome to the media Catalogue\n" +
                "========================================\n" +
                "1. Log in\n" +
                "2. Register new User\n" +
                "3. Exit\n" +
                "========================================");
        System.out.println("Enter option: ");

    }
}