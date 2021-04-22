import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.*;
import java.io.EOFException;

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
    //private static final long serialVersionUID = -6981507101762460021L;

    //The active user
    Player activeUser;
    ArrayList<Item> inventory;
    private ArrayList<Player> playerList = new ArrayList<Player>();

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
        a1.setName("A1");
        a1.setBounds(x, y, width, height); //x, y, w, h
        a1.setIcon(queue_a1.peek().getIcon());
        a1.addActionListener((e) -> {
            //Confirm button
            int response = JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you would like to purchase " + queue_a1.peek().getName() + "?",
                "Confirm", 
                JOptionPane.YES_NO_OPTION
            );

            if(response == JOptionPane.YES_OPTION) {
                System.out.println("*A1* selected"); 
                
                updateButton(a1, queue_a1);//if this works uncomment the rest
            } else {
                System.out.println("Selection canceled"); //debug
            }
        }); 

        //Button for slot A2
        a2 = new JButton();
        a2.setName("A2");
        a2.setBounds(300, y, width, height);
        a2.setIcon(queue_a2.peek().getIcon());
        a2.addActionListener((e) -> {
            //Confirm button
            response = JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you would like to purchase" + queue_a2.peek().getName() + "?",
                "Confirm", 
                JOptionPane.YES_NO_OPTION
            );

            if(response == JOptionPane.YES_OPTION) {
                System.out.println("*A2* selected"); 

                updateButton(a2, queue_a2);
            } else {
                System.out.println("Selection canceled");
            }

        });

        //Button for slot A3
        a3 = new JButton();
        a3.setName("A3");
        a3.setBounds(500, y, width, height);
        a3.setIcon(queue_a3.peek().getIcon());
        a3.addActionListener((e) -> {
            //Confirm button
            int response = JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you would like to purchase" + queue_a3.peek().getName() + "?",
                "Confirm", 
                JOptionPane.YES_NO_OPTION
            );

            if(response == JOptionPane.YES_OPTION) {
                System.out.println("*A3* selected"); 

                updateButton(a3, queue_a3);
            } else {
                System.out.println("Selection canceled");
            }

        });

        //Reset x
        x = default_x;

        //Button for slot B1
        b1 = new JButton();
        b1.setName("B1");
        b1.setBounds(100, 225, width, height);
        b1.setIcon(queue_b1.peek().getIcon());
        b1.addActionListener((e) -> {
            //Confirm button
            int response = JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you would like to purchase" + queue_b1.peek().getName() + "?",
                "Confirm", 
                JOptionPane.YES_NO_OPTION
            );
            if (response == JOptionPane.YES_OPTION) {
                System.out.println("*B1* selected");

                updateButton(b1, queue_b1);
            } else {
                System.out.println("Selection canceled");
            }

        });

        //Button for slot B2
        b2 = new JButton();
        b2.setName("B2");
        b2.setBounds(300, 225, width, height);
        b2.setIcon(queue_b2.peek().getIcon());
        b2.addActionListener((e) -> {
            //Confirm button
            int response = JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you would like to purchase" + queue_b2.peek().getName() + "?",
                "Confirm", 
                JOptionPane.YES_NO_OPTION
            );
            if (response == JOptionPane.YES_OPTION) {
                System.out.println("*B2* selected");

                updateButton(b2, queue_b2);
            } else {
                System.out.println("Selection canceled");
            }

        });

        //Button for slot B3
        b3 = new JButton();
        b3.setName("B3");
        b3.setBounds(500, 225, width, height);
        b3.setIcon(queue_b3.peek().getIcon());
        b3.addActionListener((e) -> {
            //Confirm button
            int response = JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you would like to purchase" + queue_b3.peek().getName() + "?",
                "Confirm", 
                JOptionPane.YES_NO_OPTION
            );
            if (response == JOptionPane.YES_OPTION) {
                System.out.println("*B3* selected"); 

                updateButton(b3, queue_b3);
            } else {
                System.out.println("Selection canceled");
            }

        });

        //Reset x and y
        x = default_x;
        y = defauly_y;

        //Button for slot C1
        c1 = new JButton();
        c1.setName("C1");
        c1.setBounds(x, 350, width, height);
        c1.setIcon(queue_c1.peek().getIcon());
        c1.addActionListener((e) -> {
            //Confirm button
            int response = JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you would like to purchase" + queue_c1.peek().getName() + "?",
                "Confirm", 
                JOptionPane.YES_NO_OPTION
            );
            if (response == JOptionPane.YES_OPTION) {
                System.out.println("*C1* selected"); 

                updateButton(c1, queue_c1);
            } else {
                System.out.println("Selection canceled");
            }

        });

        //Button for slot C2
        c2 = new JButton();
        c2.setName("C2");
        c2.setBounds(300, 350, width, height);
        c2.setIcon(queue_c2.peek().getIcon());
        c2.addActionListener((e) -> {
            //Confirm button
            int response = JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you would like to purchase" + queue_c2.peek().getName() + "?",
                "Confirm", 
                JOptionPane.YES_NO_OPTION
            );
            if (response == JOptionPane.YES_OPTION) {
                System.out.println("*C2* selected"); 

                updateButton(c2, queue_c2);
            } else {
                System.out.println("Selection canceled");
            }

        });

        //Button for slot C3
        c3 = new JButton();
        c3.setName("c3");
        c3.setBounds(500, 350, width, height);
        c3.setIcon(queue_c3.peek().getIcon());
        c3.addActionListener((e) -> {
            //Confirm button
            int response = JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you would like to purchase" + queue_c3.peek().getName() + "?",
                "Confirm", 
                JOptionPane.YES_NO_OPTION
            );
            if (response == JOptionPane.YES_OPTION) {
                System.out.println("*C3* selected"); 

                updateButton(c3, queue_c3);
            } else {
                System.out.println("Selection canceled");
            }

        });

        //d1
        d1 = new JButton();
        d1.setName("D1");
        d1.setBounds(x, 475, width, height);
        d1.setIcon(queue_d1.peek().getIcon());
        d1.addActionListener((e) -> {
            //Confirm button
            int response = JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you would like to purchase" + queue_d1.peek().getName() + "?",
                "Confirm", 
                JOptionPane.YES_NO_OPTION
            );
            if (response == JOptionPane.YES_OPTION) {
                System.out.println("*D1* selected"); 

                updateButton(d1, queue_d1);
            } else {
                System.out.println("Selection canceled");
            }

        });

        //d2
        d2 = new JButton();
        d2.setName("D2");
        d2.setBounds(300, 475, width, height);
        d2.setIcon(queue_d2.peek().getIcon());
        d2.addActionListener((e) -> {
            //Confirm button
            int response = JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you would like to purchase" + queue_d2.peek().getName() + "?",
                "Confirm", 
                JOptionPane.YES_NO_OPTION
            );
            if (response == JOptionPane.YES_OPTION) {
                System.out.println("*D2* selected"); 

                updateButton(d2, queue_d2);
            } else {
                System.out.println("Selection canceled");
            }

        });

        //d3
        d3 = new JButton();
        d3.setName("D3");
        d3.setBounds(500, 475, width, height);
        d3.setIcon(queue_d3.peek().getIcon());
        d3.addActionListener((e) -> {
            //Confirm button
            int response = JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you would like to purchase" + queue_d3.peek().getName() + "?",
                "Confirm", 
                JOptionPane.YES_NO_OPTION
            );
            if (response == JOptionPane.YES_OPTION) {
                System.out.println("*D3* selected"); 

                updateButton(d3, queue_d3);
            } else {
                System.out.println("Selection canceled");
            }

        });


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

                File scores = new File("./util/player.dat");

                if(!(scores.isFile())) {
                    try {
                        FileOutputStream data = new FileOutputStream("./util/player.dat");
                        data.close();
                    } catch (Exception ex) {
                        System.err.println("error, could not make file");
                    } 
                }

                boolean madeLeaderboard = false;

                try {
                    FileInputStream fis = new FileInputStream(scores);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    
                    //Add players from player.dat to playerList
                    for (int i = 0; i < 5; i++) {
                        playerList.add((Player) ois.readObject()); //check player.dat size cause default max heap space is 316 MB .. just push to github i'll is check whats using so much memory tommorw
                        System.out.println("loop " + i + " in vm read");//for debugging 
                    }

                    fis.close();
                    ois.close();
                } catch (FileNotFoundException ex) {
                    System.err.println("fatality ");
                } catch (EOFException ex) {
                    System.err.println("Ignore this... player.dat has less than 5 players");
                } catch (IOException ex){
                    System.err.println("not like that...");
                } catch (Exception ex) {
                    System.err.println("god why");
                }

                //Check if current players score is greater than the least
                    //if so, add it to hs

                if (playerList.size() < 5) {
                    boolean added = false;
                    madeLeaderboard = true;
                    for (int i = 0; i < playerList.size(); i++) {
                        if (playerList.get(i).getPlayerScore() < activeUser.getPlayerScore()) {
                            playerList.add(i, activeUser);
                            added = true;
                        }
                    }
                    if (!(added)) {
                        playerList.add(activeUser);
                    }    
                } else {
                    if (playerList.get(4).getPlayerScore() < activeUser.getPlayerScore()) {
                        boolean added = false;
                        madeLeaderboard = true;
                        playerList.remove(4);
                        for (int i = 0; i < playerList.size(); i++) {
                            if (playerList.get(i).getPlayerScore() < activeUser.getPlayerScore()) {
                                playerList.add(i, activeUser);
                                added = true;
                            }
                        }
                        if (!(added)) {
                            playerList.add(4, activeUser);
                        }
                    }
                }

                //TODO: save player to highscores if score meets criteria
                if (madeLeaderboard) {
                    try {
                        FileOutputStream fos = new FileOutputStream(scores);
                        ObjectOutputStream ois = new ObjectOutputStream(fos);

                        for (int i = 0; i < playerList.size(); i++) {
                            ois.writeObject(playerList.get(i));
                            System.out.println("loop " + i + " in vm write");//for debugging
                        }
                            
                        ois.close();
                        fos.close();
                        System.out.println("New high score generated");
                    } catch (FileNotFoundException ex) {
                        System.err.println("No hs file to write to");
                    } catch (IOException ex) {
                        System.err.println("IOException writing to high scores");
                    } catch (Exception ex) {
                        System.err.println("Exception writing to hish scores");
                    }
                }

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
                activeUser.getPlayerName() + "'s Backpack\nCash: $" + activeUser.getPlayerCash() + "\nInventory: " + activeUser.getPlayerInventory() + "\nScore: " + activeUser.getPlayerScore()
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
        this.add(c1);
        this.add(c2);
        this.add(c3);
        this.add(d1);
        this.add(d2);
        this.add(d3);
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
            } else {//this was missing which is why it let you but without having enough money
                activeUser.setPlayerCash(activeUser.getPlayerCash() - queue.peek().getPrice());
                //Update score
                activeUser.setPlayerScore(activeUser.getPlayerScore() + queue.peek().getPoint()); //adds current to
                System.out.println("Current playerScore while in VM: " + activeUser.getPlayerScore());
                //Remove from queue
                inventory.add(queue.poll());
                //Update queue, and arraylist for the player
                activeUser.setPlayerInventory(inventory);
                //Display new item
                button.setIcon(queue.peek().getIcon());
                //button.setText(queue.peek().getName());
            }
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