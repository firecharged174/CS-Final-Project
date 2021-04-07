import java.awt.Color;
import java.io.*;
import javax.swing.*;

/**
 * Login.java
 * Can be used to create a new account or login to an existing one.
 * Uses information from player.dat and items.dat
 * @author Zane Yankalunas
 * @author Cory B
 * @author Avin P
 * @version 2/12/2021 - 4/ /2021
 */
public class Login extends JFrame //implements Serializable -> this is redundent
{

    private static final long serialVersionUID = 7394498702038555000L;

    private JButton signIn, newAccount, back;
    private JLabel info = new JLabel("You must be logged in to start.");
    private Player activeUser;
    private ArrayList<String> playerNames = new ArrayList<String>(); 
    private ArrayList<Player> players = new ArrayList<Player>(); 
    private File user = new File("./util/player.dat");
    private ImageIcon frameIcon = new ImageIcon("./images/small_lock_clipart.png");
    private boolean loggedIn = false;
    private ArrayList<Queue<Item>> queueList = new ArrayList<Queue<Item>>();

    Login() 
    {
        //creates new player.dat if it dosen't exist
        if(!(user.isFile())) {
            try {
                FileOutputStream data = new FileOutputStream("./util/player.dat");
                data.close();
            } catch (Exception e) {
                System.err.println("error, could not make file");
            } 
        }

        //Fills players all players in player.dat
        fillPlayers();
        fillPlayerNames();

        /** Sign in button gives user access to previously made account */
        signIn = new JButton("Sign In");
        signIn.setBounds(10, 10, 150, 30);
        signIn.addActionListener((e) -> {

            // Checks if players is valid and closes
            if (playerNames.size == 0) {
                JOptionPane.showMessageDialog (

                    null, 
                    "There are no players to select from.\n Please create a new account.", 
                    "Error: Players is null", 
                    JOptionPane.CLOSED_OPTION

                );
                return;
            }

            String playerName = (String) JOptionPane.showInputDialog(  //why

                this,   //parent component
                "Enter player name: ", //message
                "Login", //title
                JOptionPane.QUESTION_MESSAGE //type of message

            );

            boolean isplayer = false;
            for (int i = 0; i < playerNames.size(); i++) {
                if (playerName == playerNames.get(i)) {
                    break;
                }
            }
            if (isplayer == false) {
                return;
            }
            
            //Checks player.dat for the player object associated with the name and loads the data
            if (user.exists()) {
                try {
                    //file input from player.dat
                    ObjectInputStream fin = new ObjectInputStream(new FileInputStream(user)); 

                    //Looks though all player objects in player.dat to find one with matching name
                    for (Player users: players) {
                        fin.readObject();   

                        if ((users.getPlayerName()) == playerName){ 
                            //Sets the active user
                            activeUser = users;
                            break;
                            }
                    }
                        
                    fin.close();
                } catch (FileNotFoundException ex) {
                    System.err.println("err baby");
                } catch (Exception ex) {
                    System.err.println("oops");
                } //Added to fix error
            }

            info.setText("You are now signed in as " + playerName);

            // Confirmation message
            JOptionPane.showMessageDialog(

                this,                           //frame used
                "Hello, " + playerName + ", and welcome to 'Vending Machine Simulator\nPress 'ok' to begin shopping",
                "Login Successful",             //window name
                JOptionPane.CANCEL_OPTION       //what kinda window it is

            );

            setLoggedIn(true);   
                            
        });

        /** New Account Button **/
        newAccount = new JButton("New Account");
        newAccount.setBounds(10, 70, 200, 30);
        newAccount.addActionListener((e) -> {

            Player newUser = new Player();

            //Sets the name of the new player
            newUser.setPlayerName(
                (String) JOptionPane.showInputDialog(
                    this, 
                    "Enter player name: "
                )
            );
            
            //Sets the money of the new user
            newUser.setPlayerCash((int)(Math.random()*101)); //rand int btwn 10 and 100 [Math.random() * (max-min+1) + min]

            //Fill queues and add to arraylist of queues of item objects
            //newUser.setVendingMachineSlots(this.setPlayerArray(queueList)); 

            //Adds newUser to player.dat
            if (user.exists()) {
                try {
                    ObjectOutputStream fout = new ObjectOutputStream(new FileOutputStream(user));
                    fout.writeObject(newUser);
                    fout.close();
                } catch (FileNotFoundException ex) {
                    System.err.println("File read error when adding new user to player.dat");
                } catch (IOException ex) {
                    System.err.println("IOException when adding new user to player.dat"); //thrown every time ... Player is not serializable
                    
                }
            }

            // Welcome message to the new user
            JOptionPane.showMessageDialog (

                this, 
                newUser.getPlayerName() + ", welcome to the Vending Machine\nClick back then start to begin.", 
                "New Account Created", 
                JOptionPane.INFORMATION_MESSAGE
                
            );

            info.setText("You are currently signed in as:\n" + newUser.getPlayerName());
            activeUser = newUser;
            setLoggedIn(true);

        });

        // Back button goes back to the menu
        back = new JButton("Back");
        back.setBounds(10, 120, 100, 30);
        back.setBackground(Color.RED);
        back.addActionListener((e) -> {
            this.dispose();
        });

        // Information label regarding who is logged in
        info.setLocation(0, 350);
        

        /** Creates the frame */
        this.setTitle("Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(300, 400);
        this.setIconImage(frameIcon.getImage());
        this.setLocationRelativeTo(null);

        // Adds necessary buttons to the frame
        this.add(signIn);
        this.add(newAccount);
        this.add(back);

        this.add(info); 

    }

    /**
     * Fills the ArrayList of player names from the Player objects in player.dat
     */
    public void fillPlayers() 
    {
        try {
            //File input from player.dat
            ObjectInputStream fin = new ObjectInputStream(new FileInputStream(user)); 

            //Looks though all player objects in player.dat and adds them to playerNames
            while (true) {
                try {
                    Player temp = null;
                    temp = (Player) fin.readObject();
                    if (temp != null) {
                        players.add(temp);
                    }
                    else { 
                        break; 
                    }
                } catch (FileNotFoundException ex) {
                    System.err.println("File not found. Did not");
                } catch (ClassNotFoundException e) {
                    System.err.println("ClassNotFoundException trying to fill players");
                }
            }
            
            fin.close();
        } catch (FileNotFoundException ex) {
            System.err.println("The file, " + user + ", does not exist"); //getting this alot

            System.out.println("Attempting to create a player.dat file...");

            try {
                FileOutputStream fout = new FileOutputStream(user); //creates player.dat if it dosent exist
                fout.close();
            } catch (Exception e) {
                System.err.println("General exception on creating player.dat");
            }
        } catch (IOException ex) {
            System.err.println("IOException when accessing player.dat");
        }
    }

    /**
     * Fills player names from players.
     */
    public void fillPlayerNames() 
    {
        for (int i = 0; i < players.size(); i++) {
            playerNames.add(players.get(i).getPlayerName());
        }
    }

    /**
     * Fill queue with Item objects from item.dat or item.bin
     * @return Queue of Item's
     */
    public Queue<Item> fillQueue(Queue<Item> q) 
    {
        File file = new File("./util/items.dat"); 

        try {
            FileReader fin = new FileReader(file);
            ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(file));
            //u think u can figure this out? 
            
            for (int i = 0; i < 5; i++) { //5 is number items per slot
                //q.add(fin.getObject()); //randomize .. Add items.dat to array then randoml pikck and add 5 items to q
            }
            //q.add(//defaultchips);

            //if q.empty, display sold out
            if (q.isEmpty()) {

            }
            objIn.close();
            fin.close();
        } catch (FileNotFoundException ex) {
            System.err.println("No file for queses to fill from.");
        } catch (IOException ex) {
            System.err.println("IOException filling queues");
        }
        return q;
    }

    /**
     * Allows access to the active user in other files
     * @return activeUser - the user that is logged in to the game
     */
    public Player getActiveUser() {
        return activeUser;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean getLoggedIn() {
        return loggedIn;
    }

    public void setPlayerArray(ArrayList<Queue<Item>> qList)
    {
        for (int i = 0; i < 12; i++) {
            queueList.add(fillQueue(new Queue<Item>()));
        }   
    }
}
