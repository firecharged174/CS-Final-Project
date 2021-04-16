import java.awt.Color;
import javax.swing.*;

/**
 * VendingMachine.java
 * Users can but items from a randomly generated vending machine
 * @author Zane Yankalunas
 * @author Cory Berger
 * @author Avin Patel
 * @version
 */
public class VendingMachine extends JFrame 
{  
    private static final long serialVersionUID = -6981507101762460021L;

    //The active user
    Player activeUser;
    ArrayList<Item> inventory;

    //Frame Icon
    private static ImageIcon frameIcon = new ImageIcon("./images/Vendingmachine.jpg");

    // Buttons
    private JButton a1, a2, a3;
    private JButton b1, b2, b3;
    private JButton c1, c2, c3;
    private JButton d1, d2, d3;
    private JButton exitButton;
    private JButton backpackButon;

    // Queues for each button
    private ArrayList<Queue<Item>> slots;;
    private Queue<Item> queue_a1;
    private Queue<Item> queue_a2;
    private Queue<Item> queue_a3;
    private Queue<Item> queue_b1; 
    private Queue<Item> queue_b2; 
    private Queue<Item> queue_b3; 
    private Queue<Item> queue_c1; 
    private Queue<Item> queue_c2; 
    private Queue<Item> queue_c3; 
    private Queue<Item> queue_d1; 
    private Queue<Item> queue_d2; 
    private Queue<Item> queue_d3; 

    private int response;

    // Helper variables for sizes and positions for buttons
    private static final int default_x = 100;
    private static final int defauly_y = 100;
    private int x = 100;
    private int y = 100;
    private int width = 100; //width of button
    private int height = 50; //height of button

    /**
     * Main GUI frame. Add all buttons and labels and textboxes here, somehow
     * What belongs in this class and what belongs in index and how to use each where they are?
     */
    VendingMachine(Menu menu) 
    {
        //Prepares the game
        setPlayer(menu.getPlayer());
        slots = activeUser.getVendingMachineSlots();
        setQueues(slots);
        inventory = activeUser.getPlayerInventory();

        /** Create new button buttons **/
        // Button for slot A1
        a1 = new JButton();
        a1.setBounds(x, y, width, height); //x, y, w, h
        //a1.setIcon(cheetosIcon);
        a1.addActionListener((e) -> {
            //Confirm button
            int response = JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you would like to purcahse this?",
                "Confirm", 
                JOptionPane.YES_NO_OPTION
            );

            if(response == JOptionPane.YES_OPTION) {
                System.out.println("Cheetos selected"); 
                
                updateButton(a1, queue_a1);
            } else {
                System.out.println("Selection canceled"); 
            }
        }); 

        //Button for slot A2
        a2 = new JButton();
        a2.setBounds(x+= (x+width + x/width), y, width, height);
        //a2.setIcon(fritosIcon);
        a2.addActionListener((e) -> {
            //Confirm button
            response = JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you would like to purcahse this?",
                "Confirm", 
                JOptionPane.YES_NO_OPTION
            );

            if(response == JOptionPane.YES_OPTION) {
                System.out.println("Fritos selected"); 

                updateButton(a2, queue_a2);
            } else {
                System.out.println("Selection canceled");
            }

        });

        //Button for slot A3
        a3 = new JButton();
        a3.setBounds(x+= (x+width + x/width), y, width, height);
        //a3.setIcon(snickersIcon);
        a3.addActionListener((e) -> {
            //Confirm button
            int response = JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you would like to purcahse this?",
                "Confirm", 
                JOptionPane.YES_NO_OPTION
            );

            if(response == JOptionPane.YES_OPTION) {
                System.out.println("Snickers selected"); 

                updateButton(a3, queue_a3);
            } else {
                System.out.println("Selection canceled");
            }

        });

        //Reset x
        x = default_x;

        //Button for slot B1
        b1 = new JButton();
        b1.setBounds(x, y+= (y+height + y/height), width, height);
        //b1.setIcon(crunchIcon);
        b1.addActionListener((e) -> {
            //Confirm button
            int response = JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you would like to purcahse this?",
                "Confirm", 
                JOptionPane.YES_NO_OPTION
            );
            if (response == JOptionPane.YES_OPTION) {
                System.out.println("[crunch] selected"); 
            } else {
                System.out.println("Selection canceled");
            }

        });

        //Button for slot B2
        b2 = new JButton();
        b2.setBounds(x, y+= (y+height + y/height), width, height);
        //b2.setIcon(queue_b2.get().getIcon());
        b2.addActionListener((e) -> System.out.println("button b2 works"));

        //Button for slot B3
        b3 = new JButton();
        b3.setBounds(x, y+= (y+height + y/height), width, height);
        //b2.setIcon(queue_b2.get().getIcon());
        b3.addActionListener((e) -> System.out.println("button b3 works"));

        //Reset x and y
        x = default_x;
        y = defauly_y;

        //Button for slot C1

        //Button for slot C2

        //Button for slot C3


        // Exit button with confirmation 
        exitButton = new JButton("Exit");
        exitButton.setBounds(0, 0, 70, 20); //0, 0 is top left
        exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener((e) -> {
            //Confirm button
            int response = JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you want to exit?",
                "Exit", 
                JOptionPane.YES_NO_OPTION
            );

            if(response == JOptionPane.YES_OPTION) {
                //Exits the game
                System.out.println("Exiting game..."); 
                JOptionPane.showMessageDialog(
                    this, 
                    "Thank you for shopping!\nCome again soon!", 
                    "Goodbye", 
                    JOptionPane.CANCEL_OPTION
                );
                //Menu menu = new Menu();
                menu.setVisible(true);
                this.dispose(); //closes it
            } else {
                System.out.println("Canceling exit call...");
            }

        });

        // Backpack button. Displays cash and inventory of the player
        backpackButon = new JButton("Backpack");
        backpackButon.setBounds(100, 20, 100, 30);
        backpackButon.setBackground(Color.GREEN);
        backpackButon.addActionListener((e) -> {

            if (activeUser == null) {
                JOptionPane.showMessageDialog (
                    null, 
                    "Somehow you managed to play with a null player.\nPlease actually sign in this time, ok?", 
                    "Error", 
                    JOptionPane.CLOSED_OPTION
                );
            } 
                
            JOptionPane.showMessageDialog(
                this, 
                activeUser.getPlayerName() + "'s Backpack\nCash: $" + activeUser.getPlayerCash() + "\nInventory: " + activeUser.getPlayerInventory()
            );

        });

        // Creates the frame for the game
        this.setTitle("Vending Machine Simulator"); //this sets title of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application //do_nothing_on_close
        this.setResizable(false); //prevent frame from being resized
        this.setSize(690, 690); //sets the x and y dimension 
        this.setLayout(null);   //sets the layout
        this.setIconImage(frameIcon.getImage()); //change icon of the this
        this.getContentPane().setBackground(new Color(0x123456)); //change color of background can slso be (0, 4, 5) rgb
        this.setLocationRelativeTo(null);

        //Adds buttons to the frame
        this.add(a1);
        this.add(a2);
        this.add(a3);
        this.add(b1);
        this.add(b2);
        this.add(b3);
        //this.add(c1);
        //this.add(c2);
        //this.add(c3);
        //this.add(d1);
        //this.add(d2);
        //this.add(d3);
        this.add(exitButton);
        this.add(backpackButon);
    }

    public void setPlayer(Player player) {
        activeUser = player;
    }

    /**
     * Updates the active players data and updates the button
     */
    public void updateButton(JButton button, Queue<Item> queue) {
        //Queue at size 1 has a soldout item which can not be purchased
        if(queue.size() > 1) {
            //Subtract from player's cash
            if(queue.peek().getPrice() > activeUser.getPlayerCash()) {
                JOptionPane.showMessageDialog(
                    null, "You do not have enough money to purchase this.", "Warning", JOptionPane.ERROR_MESSAGE
                );
            }
            activeUser.setPlayerCash(activeUser.getPlayerCash() - queue.peek().getPrice());
            //Remove from queue
            inventory.add(queue.poll());
            //Update queue, and arraylist for the player
            activeUser.setPlayerInventory(inventory);
            //Display new item
            button.setIcon(queue.peek().getIcon());
        }
        //debug
        System.out.println("Button " + button.getName() + " is empty");
    }

    /**
     * Sets the queues for the game from the player data.
     */
    public void setQueues(ArrayList<Queue<Item>> list) {
        queue_a1 = list.get(0);
        queue_a2 = list.get(1);
        queue_a3 = list.get(2);
        queue_b1 = list.get(3);
        queue_b2 = list.get(4);
        queue_b3 = list.get(5);
        queue_c1 = list.get(6);
        queue_c2 = list.get(7);
        queue_c3 = list.get(8);
        queue_d1 = list.get(9);
        queue_d2 = list.get(10);
        queue_d3 = list.get(11);
    }
}