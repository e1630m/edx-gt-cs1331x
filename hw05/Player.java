/**
 * This class represents the Player class in the Amidst Us game.
 * It will represent a player in the game
 * @author CS 1331 TAs
 * @version 1.1
 */
public abstract class Player implements Comparable<Player> {
    private String name;
    private int susLevel;
    private boolean frozen;
    // It's ok to move around elemnts of this array, but taking out Players or
    // manually adding them will result in errors.
    private static Player[] players;

    /**
     * Constructor for Player Objects. Be sure to use this constructor, as it properly populates the players array.
     * The logic in this constructor ensures that players is always full, although frozen players will still be in it.
     * NOTE: Use getPlayers() each time you need it, since the reference will change with every new instance.
     * @param name Name of the player
     * @param susLevel Integer that represents the suspicion level, with higher being more suspicious.
     *                 Negative values will be set to a minimum of 0.
     */
    public Player(String name, int susLevel) {
        this.name = name;
        if (susLevel >= 0) {
            this.susLevel = susLevel;
        } else {
            this.susLevel = 0;
        }
        if (players == null) {
            players = new Player[1];
            players[0] = this;
        } else {
            int length = players.length + 1;
            Player[] temp = players;
            players = new Player[length];
            for (int i = 0; i < temp.length; i++) {
                players[i] = temp[i];
            }
            players[players.length - 1] = this;
        }
    }

    /**
     * This method is an abstract method called emergencyMeeting.
     * This method will be overridden by any child class and will be used to
     * have an emergency meeting.
     */
    //YOUR CODE HERE

    /**
     * This method is the compareTo method from the Comparable interface.
     * It will compare two Player objects based on their susLevel
     * @param p this method takes in a Player to compare values to
     * @return int this value will tell you the ordering of 2 Player objects
     */
    public int compareTo(Player p) {
        // YOUR CODE HERE
        return 0; // delete this once you have implemented this method
    }

    /**
     * This is the equals method which tells you if two Players are equal
     * @param o the other Player that you are comparing
     * @return boolean based on whether or not the two objects are equal
     */
    public boolean equals(Object o) {
        if (o instanceof Player) {
            Player player = (Player) o;
            return this.name.equals(player.name) && this.frozen == player.frozen
                    && this.susLevel == player.susLevel;
        }
        return false;
    }

    /**
     * This method overrides the toString method to specify what will be returned
     * if a specific instance of a Player object is printed out.
     * @return String of the values that we want to print out
     */
    public String toString() {
        String frozenString = frozen ? "frozen" : "not frozen";
        return "My name is " + this.name + ", and I have a susLevel of "
                + this.susLevel + ". I am currently " + frozenString + ".";
    }

    /**
     * This method tells you whether the game is over or not.
     * You will call this method in your frozen and emergencyMeeting methods.
     * @return boolean whether or not the game is over
     */
    public boolean gameOver() {
        int impostorCount = 0;
        int crewmateCount = 0;
        for (Player p : players) {
            if (p instanceof Impostor && !p.frozen) {
                impostorCount++;
            } else if (p instanceof Crewmate && !p.frozen) {
                crewmateCount++;
            }
        }
        if (impostorCount == 0) {
            System.out.println("Crewmates Win!");
            return true;
        } else if (impostorCount >= crewmateCount) {
            System.out.println("Impostors Win!");
            return true;
        }
        return false;
    }

    /**
     * This is the getter for the instance variable name
     * @return String the Player's name
     */
    public String getName() {
        return name;
    }

    /**
     * This is the getter for the susLevel
     * @return int the Player's susLevel
     */
    public int getSusLevel() {
        return susLevel;
    }

    /**
     * This is the setter for susLevel. SusLevel is always a positive value.
     * @param susLevel the value to change susLevel to
     */
    public void setSusLevel(int susLevel) {
        if (susLevel >= 0) {
            this.susLevel = susLevel;
        } else {
            this.susLevel = 0;
        }
    }

    /**
     * This is the getter for frozen;
     * @return boolean if the Player is frozen
     */
    public boolean isFrozen() {
        return frozen;
    }

    /**
     * this is the setter for frozen.
     * @param frozen what to change frozen to
     */
    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    /**
     * This is the getter for the players array
     * @return Player[] the entire player array
     */
    public static Player[] getPlayers() {
        return players;
    }

}
