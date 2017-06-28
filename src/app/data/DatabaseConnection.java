package app.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This Class deals with the connection to the database as well as any database controls.
 *
 * @author chad_olsen
 * @since 2017/06/09.
 */
public class DatabaseConnection {

    private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver"; // MySQL Driver name.
    private static final String URL = "jdbc:mysql://localhost:3306/MediaCatalogue"; // MySQL Database URL for Media Catalogue database.
    private static final String USER = "root"; // Database username.
    private static final String PASSWORD = "root"; // Database password.

    /**
     * This method connects the program to the database and prints out a message if it is successful as well as if it is
     * unsuccessful.
     */
    public void connectToDatabase() {
        try {
            Class.forName(MYSQL_DRIVER);
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Successful connection to the database...");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Unsuccessful connection to the database");
            e.printStackTrace();
        }
    }
}
