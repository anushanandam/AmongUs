//I worked on the homework assignment alone, using only course materials.
import java.util.Arrays;
public class RedAstronaut extends Player implements Impostor {
    private String skill;

    public RedAstronaut(String name) {
        this(name, 15, "experienced");
    }

    public RedAstronaut(String name, int susLevel, String skill) {
        super(name, susLevel);
        this.skill = skill;
    }

    public void emergencyMeeting() {
        Player[] lPlayers;
        Player[] zPlayers, fPlayers;
        lPlayers = getPlayers();
        // If impost is already frozen, just return
        if (isFrozen()) {
            return;
        }

        for (int ii = 0; ii < lPlayers.length; ii++) {
            System.out.println("1.lPlayers[" + ii + "].SusLevel:" + lPlayers[ii].getName() + lPlayers[ii].getSusLevel());
        }

        Arrays.sort(getPlayers());

        for (int ii = 0; ii < lPlayers.length; ii++) {
            System.out.println("2.lPlayers[" + ii + "].SusLevel:" + lPlayers[ii].getName() + lPlayers[ii].getSusLevel());

        }

        zPlayers = new Player[lPlayers.length];
        fPlayers = new Player[lPlayers.length];

        // Sort the array based on SusLevel 
        for (int ii = 0; ii < lPlayers.length; ii++) {

            System.out.println("2.1.lPlayers[" + ii + "].SusLevel:" + lPlayers[ii].getName() + lPlayers[ii].getSusLevel());
            for (int jj = ii + 1; jj < lPlayers.length; jj++) {
                if (lPlayers[ii].getSusLevel() < lPlayers[jj].getSusLevel()) {
                    zPlayers[ii] = lPlayers[jj];
                    lPlayers[jj] = lPlayers[ii];
                    lPlayers[ii] = zPlayers[ii];
                }
            }
        }

        for (int ii = 0; ii < lPlayers.length; ii++) {
            System.out.println("3.lPlayers[" + ii + "].SusLevel:" + lPlayers[ii].getName() + lPlayers[ii].getSusLevel());

        }

        for (int ii = 0; ii < lPlayers.length; ii++) {
            System.out.println("4.lPlayers[" + ii + "].SusLevel:" + lPlayers[ii].getName() + lPlayers[ii].getSusLevel());

        }

        for (int ii = 0; ii < lPlayers.length; ii++) {
            if (lPlayers[ii].isFrozen()) {
                continue; // Skip
            }

            System.out.println("5.lPlayers[" + ii + "].SusLevel:" + lPlayers[ii].getName() + lPlayers[ii].getSusLevel());
            if (lPlayers[ii] == this) {
                return;
            }
            if (lPlayers[ii].getSusLevel() == lPlayers[ii + 1].getSusLevel()) {
                return; // First occuurence of two equal non-frozen players, return
            }

            if (lPlayers[ii] != this) {
                if (!lPlayers[ii].isFrozen()) {
                    lPlayers[ii].setFrozen(true);
                    break;
                }
            }

        }

        gameOver();

    }

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

    public void sabotage(Player p) {
        if (p instanceof Impostor || p.isFrozen() || this.isFrozen()) {
            return;
        }
        if (this.getSusLevel() < 20) {
            p.setSusLevel((int)(p.getSusLevel() * 1.5));
        } else {
            p.setSusLevel((int)(p.getSusLevel() * 1.25));
        }
        System.out.println("Sabotage:" + p.getName() + " SusLevel: " + p.getSusLevel());
    }

    public boolean equals(Object o) {
        if (o != null && o instanceof RedAstronaut) {
            RedAstronaut red = (RedAstronaut) o;
            return this.getName().equals(red.getName()) && this.isFrozen() == red.isFrozen() &&
                this.getSusLevel() == red.getSusLevel() && this.skill.equals(red.skill);
        }
        return false;
    }

    public String toString() {
        String redAstro = super.toString() + " I am an " + skill + " player!";
        if (this.getSusLevel() > 15) {
            return redAstro.toUpperCase();
        }
        return redAstro;
    }
}