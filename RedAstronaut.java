//I worked on the homework assignment alone, using only course materials.
import java.util.Arrays;
/**
 *This class represents a RedAstronaut who is a player in the game.
 *The Impostor interface is implemented.
 * @author Anusha Nandam
 * @version 1.0
 */
public class RedAstronaut extends Player implements Impostor {
    private String skill;
    /**
     *constructor assigning default values
     *@param name player's name
     */
    public RedAstronaut(String name) {
        this(name, 15, "experienced");
    }
    /**
     *constructor taking in name, susLevel, and skill
     *@param name player's name
     *@param susLevel player's susLevel
     *@param skill player's skill
     */
    public RedAstronaut(String name, int susLevel, String skill) {
        super(name, susLevel);
        this.skill = skill;
    }
    /**
     *Emergency meeting called and most sus person will be frozen.
     *The person who called the meeting can't be frozen.
     *A frozen player can't call the meeting and already frozen players can't be impacted.
     *If two players have the same susLevel, no one gets frozen.
     */
    public void emergencyMeeting() {
        int count = 1;
        if (isFrozen()) {
            return; // If impost is already frozen, just return
        }
        Arrays.sort(getPlayers());
        for (int ii = getPlayers().length - 1; ii < getPlayers().length; ii--) {
            if (getPlayers()[ii].isFrozen()) {
                continue; // Skip
            }
            if (getPlayers()[ii] == this) {
                return;
            }
            while (getPlayers()[ii].getSusLevel() == getPlayers()[ii - count].getSusLevel()) {
                if (getPlayers()[ii - count].isFrozen()) {
                    count++;
                } else {
                    return; // First occuurence of two equal non-frozen players, return
                }
            }
            if (getPlayers()[ii] != this) {
                if (!getPlayers()[ii].isFrozen()) {
                    getPlayers()[ii].setFrozen(true);
                    break;
                }
            }
        }
        gameOver();
    }
    /**
     *Implements method in Impostor interface
     *Not possible to freeze another Impostor and a frozen Impostor can't freeze anyone
     *The method should end if p is an Impostor and freezing a frozen player should have no impact.
     *A successful freeze changes the Crewmate boolean value.
     *@param p player
     */
    public void freeze(Player p) {
        if (p instanceof Impostor || p.isFrozen() || this.isFrozen()) {
            return;
        }

        if (this.getSusLevel() < p.getSusLevel()) {
            p.setFrozen(true);
        } else {
            this.setSusLevel(this.getSusLevel() * 2);
        }

        gameOver();
        return;
    }
    /**
     *Can't sabotage impostor and frozen impostor can't sabotage
     *Sabotaging frozen player has no impact
     *@param p player
     */
    public void sabotage(Player p) {
        if (p instanceof Impostor || p.isFrozen() || this.isFrozen()) {
            return;
        }
        if (this.getSusLevel() < 20) {
            p.setSusLevel((int) (p.getSusLevel() * 1.5));
        } else {
            p.setSusLevel((int) (p.getSusLevel() * 1.25));
        }
    }
    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof RedAstronaut) {
            RedAstronaut red = (RedAstronaut) o;
            return this.getName().equals(red.getName()) && this.isFrozen() == red.isFrozen()
                && this.getSusLevel() == red.getSusLevel() && this.skill.equals(red.skill);
        }
        return false;
    }

    @Override
    public String toString() {
        String redAstro = super.toString() + " I am an " + skill + " player!";
        if (this.getSusLevel() > 15) {
            return redAstro.toUpperCase();
        }
        return redAstro;
    }
}