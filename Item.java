import javax.swing.ImageIcon;
import java.io.Serializable;

/**
 * Item.java
 * An item object which will be in the vending machine
 * @author Zane Yankalunas
 * @version 2/21/2021
 */
public class Item implements Serializable
{
    private static final long serialVersionUID = 3596916216650912190L;
    
    //Declare private instance variables
    private String name;
    private ImageIcon icon;
    private double price;
    private int point;

    private static final String DEFAULT_NAME = "Chips";
    private static final ImageIcon DEFAULT_ICON = new ImageIcon("../items/DefaultChips.png");
    private static final double DEFAULT_PRICE = 1.25;
    private static final double DEFAULT_POINT = 1;

    /**
     * Default constructor for class Item
     */
    public Item()
    {
        this(DEFAULT_NAME, DEFAULT_ICON, DEFAULT_PRICE, DEFAULT_POINT);
    }

    /**
     * Constructor for class Item
     * @param itemName - name of the item
     * @param itemIcon - image for the item
     * @param itemPrice - price of the item
     * @param itemPoint - point value of item
     */
    public Item (String itemName, ImageIcon itemIcon, double itemPrice, int itemPoint)
    {
        name = itemName;
        icon = itemIcon;
        price = itemPrice;
        point = itemPoint;
    }


    // Setters and getters for variables //
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
    
    public void setPoint(int point) {
        this.point = point;
    }
    
    public int getPoint() {
        return point;
    }
}
