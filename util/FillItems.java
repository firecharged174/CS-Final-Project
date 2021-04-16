import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.ImageIcon;

/**
 * FillItems.java
 * Will fill items.dat with Item.java objects from items.txt to be accessed by the other files.
 * This is a stand-slone file and runs seperatly from everything else.
 * @author Zane Yankalunas
 * @author Avin Patel
 * @version 2/21/2021
 */
public class FillItems implements Serializable
{
    private static final long serialVersionUID = 9112168208941547441L;

    public static void main(String[] args) 
    {
        File items = new File("items.txt");
        File file = new File("items.dat");
        File itemFile = new File("../Item.java");
        
        try {
            //Fetch data from items.txt
            FileInputStream fis = new FileInputStream(items);
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            //Add values to items.dat
            for (int i = 0; i < 21; i++) {
                Item item = new Item();
                
                //Get values from the file
                String name;
                double price;
                ImageIcon icon;

                //Add values to item object
                item.setName(name);
                item.setPrice(price);
                item.setIcon(icon);

                //Write Object to items.dat
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    ObjectOutputStream fout = new ObjectOutputStream(fos);
                    fout.writeObject(item);
                    fout.close();
                } catch (FileNotFoundException ex) {
                    System.err.println("File not dound in inner try block");
                } catch (IOException ex) {
                    System.err.println("IOException in inner try block");
                } catch (Exception ex) {
                    System.err.println("Gener exception in inner try block");
                }
            }

            //Close the objects
            ois.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            System.err.println("File not here err");
        } catch (Exception ex) {
            System.err.println("General exception");
        }
    }
}