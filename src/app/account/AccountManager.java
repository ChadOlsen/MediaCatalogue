package app.account;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This class controls the data handling of reading and writing of the registered user account to a file named
 * AccountManager.dat.
 *
 * @author Chad Olsen
 * @since 2017/05/17.
 */
public class AccountManager {

    private Map<String, Account> registeredAccounts = new LinkedHashMap();

    public AccountManager() {
    }

    public Map<String, Account> getRegisteredAccounts() {
        return registeredAccounts;
    }

    public void setRegisteredAccounts(Map<String, Account> registeredAccounts) {
        this.registeredAccounts = registeredAccounts;
    }

    /**
     * This method writes a Map of all the registered user accounts and their relevant catalogues
     * data to the AccountManager.dat file.
     *
     * @param registeredAccounts This parameter is the map to be written to the file.
     */
    public void writeToAccount(Map<String, Account> registeredAccounts) {
        try (ObjectOutputStream accountFile = new ObjectOutputStream(new FileOutputStream("AccountManager.dat"))) {
            accountFile.writeObject(registeredAccounts);
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * This method reads the map data from the file and sets the data to the registeredAccounts field. If the file
     * contains no data user Account objects will be created and written to the file.
     */
    public void readAccount() {
        try (ObjectInputStream accountFile = new ObjectInputStream(new FileInputStream("AccountManager.dat"))) {
            registeredAccounts = (LinkedHashMap<String, Account>) accountFile.readObject();
        } catch (FileNotFoundException e) {

        } catch (IOException io) {
            System.out.println("IOException: " + io.getMessage());
            io.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException: " + e.getMessage());
            e.printStackTrace();
        }
    }
}