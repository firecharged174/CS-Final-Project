import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.*;
import java.io.EOFException;

/**
 * Menu.java
 * Main menu GUI for the Vending Machine.
 * Allows user to login and start the game.
 * Includes instructions for how to play.
 * @author Zane Yankalunas
 * @author Avin Patel
 * @author Cory Berger
 * @version 2/11/2021
 */
public class Menu extends JFrame
{
    private static final long serialVersionUID = 745882847123195643L;
    
    private JButton startButton, highScoresButton, aboutButton, exitButton;

    //Login login;
    VendingMachine vm;
    Player player;
    private ArrayList<Queue<Item>> queueList = new ArrayList<Queue<Item>>(); //problem
    
    /**
     * Menu for the game.
     * Cotains the start, highscores, about, and exit buttons. 
     */
    Menu() 
    {
        //login = new Login(this);
        
        // Creates new buttons for the frame //
        //High scores button
        highScoresButton = new JButton("High Scores");
        highScoresButton.setBounds(75, 50, 100, 30); //bounds
        highScoresButton.addActionListener((e) -> {

            JOptionPane.showMessageDialog(
                this, 
                displayHighScores(), 
                "High Scores", 
                JOptionPane.PLAIN_MESSAGE
            );

            //Creates an instane of a login frame
            //login.setVisible(true);
            //this.setVisible(false);

        });

        //Start button, duh
        startButton = new JButton("Start");
        startButton.setBounds(75, 100, 100, 30);
        startButton.addActionListener((e) -> {

            player = new Player();

            //Get user name
            //Sets the name of the new player
            player.setPlayerName(
                (String) JOptionPane.showInputDialog(
                    this, "Enter player name: "
                )
            );

            //Sets the money of the new user
            player.setPlayerCash((int)(Math.random()*101)); //rand int btwn 10 and 100 [Math.random() * (max-min+1) + min]

            //Fill queues and add to arraylist of queues of item objects
            player.setVendingMachineSlots(setPlayerArray(queueList)); 



            /** 
            // Check for active user
            if (login.getLoggedIn() == false || login.getActiveUser() == null) {
                JOptionPane.showMessageDialog (
                    null, 
                    "Please login to play the game.", 
                    "Error", 
                    JOptionPane.CLOSED_OPTION
                );
                return;
            }
            */

            setPlayer(player);
            
            //Starts the game
            vm = new VendingMachine(this);
            vm.setVisible(true);
            this.setVisible(false);
            
        });

        //About button displays how to play the game
        aboutButton = new JButton("About");
        aboutButton.setBounds(75, 150, 100, 30);
        aboutButton.addActionListener((e) -> {

            JOptionPane.showMessageDialog(
                this, 
                "Vending Machine Simulator\n\nMade by:\n•Zane Yankalunas\n•Cory Berger\n•Avin Patel\n\nHow to play:\nStart by logging in or creating a new account.\nStarting the game will generate or load a custom vending machine and a random amount of money.\nUse your momey to buy things by clicking on the images.\nCheck your wallet and invetory at any time by clicking 'Backpack'\n\nSubscribe",
                "About",
                JOptionPane.CLOSED_OPTION
            );

        });

        //Exit button, to exit the game. What else would it do
        exitButton = new JButton("Exit");
        exitButton.setBounds(75, 200, 100, 30);
        exitButton.addActionListener((e) -> {

            //Confirm button before exiting
            int response = JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you want to exit?",
                "Exit", 
                JOptionPane.YES_NO_OPTION
            );

            if (response == JOptionPane.YES_OPTION) {
                //Exits the game
                System.out.println("Exiting game...");
                
                //Thank you message
                JOptionPane.showMessageDialog(  //Thank you message
                    this, 
                    "Thank you for shopping!\nCome again soon!", 
                    "Goodbye", 
                    JOptionPane.CANCEL_OPTION
                );
                System.exit(1);
            } else {
                System.out.println("Canceling exit call...");
            }

        });

       

        // Creates the frame
        this.setTitle("Menu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(250, 350);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        //Adds necessary buttons to the frame
        this.add(startButton);
        this.add(highScoresButton);
        this.add(aboutButton);
        this.add(exitButton);
        //this.add(playerLabel);
    } 

    /**
     * Displays the highscores
     * @return scores - String of highscores
     */
    public String displayHighScores() {
        String scores = "Top 5 High Scores:\n";
        File hs = new File("./util/player.dat");
        
        if (hs.exists()) {
            try {
                FileInputStream fin = new FileInputStream(hs);
                ObjectInputStream ois = new ObjectInputStream(fin);
                
                for (int i = 0; i < 5; i++) {
                    Player test = (Player)ois.readObject();
                    String name = test.getPlayerName();
                    int score = test.getPlayerScore();
                    scores += (i + ". " + name + ": " + score + " points\n");
                    System.out.println("loop " + i + " in menu");//for debugging 
                }

                fin.close();
            } catch (FileNotFoundException e) {
                System.err.println("No high scores file");
            } catch (EOFException e) {
                System.err.println("Ignore this.. End Of File Exception... Nothing more to read in player.dat");
            } catch (IOException e) {
                System.err.println("IO err");
            } catch (Exception e) {
                System.err.println("Exception displaying hs");
            }

            return scores;
        }
        
        return "There are no high scores to display";
    }

    public ArrayList<Queue<Item>> setPlayerArray(ArrayList<Queue<Item>> qList) {
        for (int i = 0; i < 12; i++) {
            qList.add(fillQueue(new Queue<Item>()));
        }  
        return qList; 
    }

    /**
     * Fill queue with Item objects from item.dat or item.bin
     * @return Queue of Item's
     */
    public Queue<Item> fillQueue(Queue<Item> q) //run fillItems before this
    {
        //Temp adding shit cuz procrastinate
        q.add(new Item("M&M's", new ImageIcon("images/mm.png"), 1.0, 2));
        q.add(new Item("Ruffles", new ImageIcon("images/Ruffles.png"), 1.75, 3));
        q.add(new Item("Twix", new ImageIcon("images/Twix.png"), 1.25, 2));
        q.add(new Item("Snickers", new ImageIcon("images/Snickers.png"), 1.25, 2));
        q.add(new Item("SOLD OUT!", new ImageIcon("images/sold_out.png"), 1000, 0));

        //Return the queue
        return q;
        
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
