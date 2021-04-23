import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.*;
import java.io.EOFException;
import java.awt.Color;

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
        // Creates new buttons for the frame //
        //High scores button
        highScoresButton = new JButton("High Scores");
        highScoresButton.setBounds(62, 50, 120, 30); //bounds
        highScoresButton.setBackground(Color.ORANGE);
        highScoresButton.addActionListener((e) -> {

            JOptionPane.showMessageDialog(
                this, 
                displayHighScores(), 
                "High Scores", 
                JOptionPane.PLAIN_MESSAGE
            );

        });

        //Start button, duh
        startButton = new JButton("Start");
        startButton.setBounds(62, 100, 120, 30);
        startButton.setBackground(Color.GREEN);
        startButton.addActionListener((e) -> {

            String name;

            //Get user name
            //Sets the name of the new player - if no input, setPlayerName to default
            
            name = ((String) JOptionPane.showInputDialog(
                this, "Enter player name: "
            ));
            
            player = new Player();
            if (name.equals("")) {
            } else if (name == null) {
            } else if (name.trim().isEmpty()) {//if name is all spaces
            } else {
                player.setPlayerName(name);
            }
            
            //Sets the money of the new user
            player.setPlayerCash((int)(Math.random()*41 + 10)); //rand int btwn 10 and 50 [Math.random() * (max-min+1) + min]

            //Fill queues and add to arraylist of queues of item objects
            player.setVendingMachineSlots(setPlayerArray(queueList)); 

            setPlayer(player);

            //Welcome message
            JOptionPane.showMessageDialog(
                this,
                "Hello, " + player.getPlayerName() + ", and welcome to the Vending Machine!\nYou have a balance of $" + player.getPlayerCash() + "\nGood luck!"
            );
            
            //Starts the game
            vm = new VendingMachine(this);
            vm.setVisible(true);
            this.setVisible(false);
            
        });

        //About button displays how to play the game
        aboutButton = new JButton("About");
        aboutButton.setBounds(62, 150, 120, 30);
        aboutButton.setBackground(Color.MAGENTA);
        aboutButton.addActionListener((e) -> {

            JOptionPane.showMessageDialog(
                this, 
                "Vending Machine Simulator\n\nMade by:\n-Zane Yankalunas\n-Cory Berger\n-Avin Patel\n\nHow to play:\nStart by logging in or creating a new account.\nStarting the game will generate or load a custom vending machine and a random amount of money.\nUse your momey to buy things by clicking on the images.\nCheck your wallet and invetory at any time by clicking 'Backpack'\n\nSubscribe",
                "About",
                JOptionPane.CLOSED_OPTION
            );

        });

        //Exit button, to exit the game. What else would it do
        exitButton = new JButton("Exit");
        exitButton.setBounds(62, 200, 120, 30);
        exitButton.setBackground(Color.RED);
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
                
                //Thank you message
                JOptionPane.showMessageDialog( 
                    this, 
                    "Thank you for shopping!\nCome again soon!", 
                    "Goodbye", 
                    JOptionPane.CANCEL_OPTION
                );
                System.exit(1);
            } else {
            }

        });

       

        // Creates the frame
        this.setTitle("Menu");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
                    scores += ((i+1) + ". " + name + ": " + score + " points\n");
                }
                ois.close();
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
        qList.clear();
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
        q.clear();

        File file = new File("util/items.dat"); 
        ArrayList<Item> listOfItems = new ArrayList<Item>(20); 
        try {
            FileInputStream fin = new FileInputStream(file);
            ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(file));

            for (int i = 0; i < 20; i++) {
                listOfItems.add((Item) objIn.readObject());
            }

            objIn.close();
            fin.close();
        } catch (FileNotFoundException ex) {
            System.err.println("No file for queues to fill from.");
        } catch (EOFException ex) {
            System.err.println("End of Line Error... No more to read");
        } catch (IOException ex) {
            System.err.println("IOException filling queues");
        } catch (Exception ex) {
            System.err.println("Exception queue fill");
        }

        for (int i = 0; i < ((int)((Math.random() * 8) + 3)); i++) {          //makes queues of size 3 to queues of size 8 randomly
            q.add(listOfItems.get((int)(Math.random() * listOfItems.size())));
        }
        
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
