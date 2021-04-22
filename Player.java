import java.io.Serializable;

/**
 * Player.java
 * Used for holding all the information regarding each player.
 * Each player object is saved and loaded with player.dat
 * @author Zane Yankalunas
 * @version 2/11/2021 - 2/21/2021
 */
public class Player implements Serializable
{
    private static final long serialVersionUID = 7886943020312909227L;

    // Variables 
    private String playerName;
    private double playerCash;
    private int playerScore;
    private ArrayList<Item> playerInventory;
    private static final double DEFAULT_CASH = 10;
    private static final String DEFAULT_NAME = "John Doe";
    private static final int DEFAULT_SCORE = 0;
    private static final ArrayList<Item> DEFAULT_INVENTORY = new ArrayList<Item>();
    private static final ArrayList<Queue<Item>> DEFAULT_SLOTS = null; 

    // Save data for the vending machine slots per player
    private ArrayList<Queue<Item>> vendingMachineSlots = new ArrayList<Queue<Item>>();

    /**
     * Constructor for class Player
     * @param name - name of the player
     * @param money - amount of money on player
     * @param inventory - inventory of player
     */
    public Player() {
        this(DEFAULT_NAME, DEFAULT_CASH, DEFAULT_INVENTORY, DEFAULT_SLOTS, DEFAULT_SCORE);
    }

    public Player(String name, int score) {// added to create player with just name and score for saving to player.dat for highscore
        playerName = name;
        playerScore = score;
        playerInventory = null;
        playerCash = 0;
        vendingMachineSlots = null;
    }

    public Player(String name, double money, ArrayList<Item> inventory, ArrayList<Queue<Item>> slots, int score) {
        playerName = name;
        playerCash = money;
        playerInventory = inventory;
        vendingMachineSlots = slots;
        playerScore = score;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerCash(double playerCash) {
        this.playerCash = playerCash;
    }

    public double getPlayerCash() {
        return playerCash;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerInventory(ArrayList<Item> playerInventory) {
        this.playerInventory = playerInventory;
    }

    public ArrayList<Item> getPlayerInventory() {
        return playerInventory;
    }

    /**
     * Removes cash from the players account.
     * @param removedCash - cash to be removed
     * @return true - cash was removed
     * @return false - cash cant be removed
     */
    public boolean subtractCash(double removedCash) {
        if(playerCash - removedCash <= 0){
            return false;
        } 
        playerCash -= removedCash;
        return true;
        
    }

    /** Setters and Getters for the Queue Slots */
    public void setVendingMachineSlots(ArrayList<Queue<Item>> vendingMachineSlots) {
        this.vendingMachineSlots = vendingMachineSlots;
    }
    public ArrayList<Queue<Item>> getVendingMachineSlots() {
        return vendingMachineSlots;
    }
}
