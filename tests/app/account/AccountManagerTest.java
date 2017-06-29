package app.account;

import app.catalogue.Catalogue;
import org.junit.*;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * This class consists of JUnit tests to test the logic and functionality of the Account Manager class methods and
 * ensure the methods are functioning as required.
 *
 * @author chad_olsen
 * @since 2017/06/02.
 */
public class AccountManagerTest {

    private AccountManager manager;

    @Before
    public void setup() {
        manager = new AccountManager();
        manager.writeToAccount(new LinkedHashMap<>());
    }

    @Test
    public void writeToAccountTest() throws Exception {
        Account newAccount = new Account("test", "test", new Catalogue(),false);

        manager.readAccount();
        Map<String, Account> registeredAccounts = manager.getRegisteredAccounts();
        assertTrue(registeredAccounts.isEmpty());

        registeredAccounts.put("test", newAccount);
        manager.writeToAccount(registeredAccounts);

        manager.readAccount();
        registeredAccounts = manager.getRegisteredAccounts();
        assertFalse(registeredAccounts.isEmpty());
    }

    @Test
    public void readAccountTest() throws Exception {
        Account newAccount = new Account("test", "test", new Catalogue(), false);
        Map<String, Account> registeredAccounts = new LinkedHashMap<>();
        registeredAccounts.put("test", newAccount);

        manager.writeToAccount(registeredAccounts);

        manager.readAccount();
        assertFalse(manager.getRegisteredAccounts().isEmpty());
    }

    @Test
    public void readAccount_nullTest() throws Exception {
        manager.writeToAccount(null);

        manager.readAccount();
        assertTrue(manager.getRegisteredAccounts().isEmpty());
    }
}