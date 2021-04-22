import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import javax.swing.ImageIcon;
import java.io.Serializable;

/**
 * FillItems.java
 * Will fill items.dat with Item.java objects from items.txt to be accessed by the other files.
 * This is a stand-slone file and runs seperatly from everything else.
 * @author Zane Yankalunas
 * @author Avin Patel
 * @author Cory Berger
 * @version 2/21/2021
 */
public class FillItems implements Serializable
{
    public static void main(String[] args) 
    {
        File items = new File("./util/items.txt");
        File file = new File("./util/items.dat");
        
        try {
            //Fetch data from items.txt
            Scanner scanner = new Scanner(items);
            
            //Add values to items.dat
            for (int i = 1; i < 21; i++) {
                Item item = new Item();
                
                //Get values from the file
                scanner.useDelimiter(",\\s*");
                String name = scanner.next(); 
                double price = scanner.nextDouble();
                String iconPath = scanner.next();
                ImageIcon icon = new ImageIcon(iconPath);
                int point = scanner.nextInt();

                //Add values to item object
                item.setName(name);
                item.setPrice(price);
                item.setIcon(icon);
                item.setPoint(point);

                //Write Object to items.dat
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    ObjectOutputStream fout = new ObjectOutputStream(fos);
                    fout.writeObject(item);
                    fout.close();
                } catch (FileNotFoundException ex) {
                    System.err.println("File not found in inner try block");
                } catch (IOException ex) {
                    System.err.println("IOException in inner try block");
                    System.out.println("line " + i + " in items.txt- name: " + name + " price: " + price + " iconPath: " + iconPath);
                } catch (Exception ex) {                                    
                    System.err.println("Gener exception in inner try block");
                }
            }

            //Close the objects
            scanner.close();
        } catch (FileNotFoundException ex) {
            System.err.println("File not here err");
        } catch (Exception ex) {
            System.err.println("General exception");
        }
    }
}
