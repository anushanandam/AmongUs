/**
 * This class represents the Impostor interface in the Amidst Us game.
 * @author Anusha Nandam
 * @version 1.0
 */
public interface Impostor {
    /**
     *abstract method taking in a player object and returns nothing
     *@param p player
     */
    void freeze(Player p);
    /**
     *abstract method taking in a player object and returns nothing
     *@param p player
     */
    void sabotage(Player p);
}