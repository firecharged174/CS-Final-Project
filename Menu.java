import javax.swing.*;

/**
 * Menu.java
 * Main menu GUI for the Vending Machine.
 * Allows user to login and start the game.
 * Includes instructions for how to play.
 * @author Zane Yankalunas
 * @version 2/11/2021
 */
public class Menu extends JFrame
{
    private static final long serialVersionUID = 745882847123195643L;
    
    JButton startButton, loginButton, aboutButton, exitButton;
    JLabel playerLabel = new JLabel("You must login to start");
    Login login = new Login();
    
    /**
     * Menu for the game.
     * Cotains the start, login, about, and exit buttons. 
     */
    Menu() 
    {
        // Creates new buttons for the frame //
        //Login button
        loginButton = new JButton("Login");
        loginButton.setBounds(75, 50, 100, 30); //bounds
        loginButton.addActionListener((e) -> {

            //Creates an instane of a login frame
            login.setVisible(true);

        });

        //Start button, duh
        startButton = new JButton("Start");
        startButton.setBounds(75, 100, 100, 30);
        startButton.addActionListener((e) -> {
            
            // Check for active user
            if (login.getLoggedIn() == false || login.getActiveUser() == null) {
                JOptionPane.showMessageDialog (

                    null, 
                    "Please login to play the game.", 
                    "Error", 
                    JOptionPane.CLOSED_OPTION

                );
            } else {
                //Starts the game
                new VendingMachine().setVisible(true);
                this.setVisible(false);
            }

        });

        //About button displays how to play the game
        aboutButton = new JButton("About");
        aboutButton.setBounds(75, 150, 100, 30);
        aboutButton.addActionListener((e) -> {

            JOptionPane.showMessageDialog(
                
                this, 
                "Vending Machine Simulator\n\nMade by:\nZane Yankalunas\nCory Berger\nAvin Patel\n\nHow to play:\nStart by logging in or creating a new account.\nStarting the game will generate or load a custom vending machine and a random amount of money.\nUse your momey to buy things by clicking on the images.\nCheck your wallet and invetory at any time by clicking 'Backpack'\n\nSubscribe",
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

        if(login.getLoggedIn()) {
            playerLabel.setText("You are logged in as:\n" + login.getActiveUser().getPlayerName());
        }

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
        this.add(loginButton);
        this.add(aboutButton);
        this.add(exitButton);
    }    
}
