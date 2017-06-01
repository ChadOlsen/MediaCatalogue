import java.io.*;

/**
 * This class contains functionality to handle the data from the Catalogue and store it in a file as well as functionality
 * to be able to read data from a file and add it to the Catalogue.
 *
 * @author Chad Olsen.
 * @since 2017/05/10.
 */
public class MemoryHandler {

    /**
     * This void static method will retrieve a catalogue object via the parameter and write the data of the Catalogue to a file
     * named Catalogue.dat.
     * A message will be printed to the console that the file was written.
     *
     * @param catalogue This is the Catalogue to be written to the Catalogue.dat file.
     * @catch IOException This exception will be handled if there are any exceptions when trying to write the file.
     */
    public static void writeCatalogue(Catalogue catalogue) {
        try (ObjectOutputStream catalogueFile = new ObjectOutputStream(new FileOutputStream("Catalogue.dat"))) {
            catalogueFile.writeObject(catalogue);
            System.err.println("Catalogue saved...");
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * This static method will read the data from the Catalogue.dat file and create a Catalogue object.
     * A message will be printed to the console that the file was read successfully.
     *
     * @return Catalogue This will return the created Catalogue object from the file. This method should never return null.
     * @catch FileNotFoundException  This will print a message to the console if there is no file named Catalogue.dat.
     * @catch IOException            This will print a message to the console stating the IO exception.
     * @catch ClassNotFoundException This will print a message to the console stating the ClassNotFound exception.
     */
    public static Catalogue readCatalogue() {
        try (ObjectInputStream catalogueFile = new ObjectInputStream(new FileInputStream("Catalogue.dat"))) {
            System.err.println("Loading Catalogue...");
            return (Catalogue) catalogueFile.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("Catalog not found, creating default...");
        } catch (IOException io) {
            System.out.println("IOException: " + io.getMessage());
            io.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
