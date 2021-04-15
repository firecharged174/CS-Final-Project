import java.io.*;

import javax.swing.ImageIcon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * FillItems.java
 * Will fill items.dat with Item objects from items.json to be accessed by other files.
 * This file not be used by other files and is a creater only way to add items to the vending machine.
 * @author Zane Yankalunas
 * @author Avin Patel
 * @version 2/21/2021
 */
public class FillItems implements Serializable
{
    private static final long serialVersionUID = 9112168208941547441L;

    public static void main(String[] args) 
    {
        File file = new File("./util/items.dat");
        
        try {
            //Fetch data from JSON aray
            Object obj = new FileReader(file);
            JSONObject jobj = new JSONObject(obj);

            JSONArray arr = jobj.getJSONArray("items");
            
            //Add values to items.dat
            for (int i = 0; i < arr.length(); i++) {
                Item item = new Item();

                JSONObject itemObj = arr.getJSONObject(i);

                //Get values from the array
                String name = itemObj.getString("name"); 
                double price = itemObj.getDouble("price");
                ImageIcon icon = new ImageIcon(itemObj.getString("image"));

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

        } catch (FileNotFoundException ex) {
            System.err.println("File not here err");
        } catch (JSONException ex) {
            System.err.println("JSON exception ");
        } catch (Exception ex) {
            System.err.println("General exception");
        }

        //make sure everything is added
    }
}