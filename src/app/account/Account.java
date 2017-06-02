package app.account;

import java.io.Serializable;
import app.catalogue.*;

/**
 * This class is for a user Account object to allow a user to login using a username and password and have their
 * own catalogue associated to their user account.
 *
 * @author Chad Olsen
 * @since 2017/05/17.
 */
public class Account implements Serializable{
    private String userName;
    private String userPassword;
    private Catalogue catalogue;
    private long serialVersionUID = 3L;


    public Account(String userName, String userPassword, Catalogue catalogue) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.catalogue = catalogue;

    }

    public Catalogue getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(Catalogue catalogue) {
        this.catalogue = catalogue;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
