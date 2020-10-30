//I worked on the homework assignment alone, using only course materials.
import java.util.Arrays;
public class BlueAstronaut extends Player implements Crewmate {
    private int numTasks;
    private int taskSpeed;
    private boolean firstTime = true;

    public BlueAstronaut(String name) {
        this(name, 15, 6, 10);
    }

    public BlueAstronaut(String name, int susLevel, int numTasks, int taskSpeed) {
        super(name, susLevel);
        this.numTasks = numTasks;
        if (taskSpeed < 1) {
            taskSpeed = 1;
        }
        this.taskSpeed = taskSpeed;
        System.out.println("BlueAstronaut Name" + name);
    }

    public int getNumTasks() {
        return numTasks;
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
        //getPlayers()[i].getSusLevel() > getPlayers()[i+1].getSusLevel()
        //getPlayers()[i].compareTo(getPlayers()[i])
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

        /*
                // Sort the array to have all frozen objects first
                for (int ii = 0; ii < lPlayers.length; ii ++) {
                	if (lPlayers[ii].isFrozen()) {
                		continue;
                	}
                	for (int jj = ii+1; jj < lPlayers.length; jj ++) {
                		if (lPlayers[jj].isFrozen()) {
                			fPlayers[ii] = lPlayers[jj];
                			lPlayers[jj] = lPlayers[ii];
                			lPlayers[ii] = fPlayers[ii];
                		}
                	}
                }
        */

        for (int ii = 0; ii < lPlayers.length; ii++) {
            System.out.println("4.lPlayers[" + ii + "].SusLevel:" + lPlayers[ii].getName() + lPlayers[ii].getSusLevel());

        }

        for (int ii = 0; ii < lPlayers.length; ii++) {
            if (lPlayers[ii].isFrozen()) {
                continue; // Skip
            }

            System.out.println("5.lPlayers[" + ii + "].SusLevel:" + lPlayers[ii].getName() + lPlayers[ii].getSusLevel());

            if (lPlayers[ii].getSusLevel() == lPlayers[ii + 1].getSusLevel()) {
                return; // First occuurence of two equal non-frozen players, return
            }
            if (!lPlayers[ii].isFrozen()) {
                lPlayers[ii].setFrozen(true);
                break;
            }

        }

        gameOver();

    }

    public void completeTask() {
        if (this.isFrozen()) {
            return;
        }

        if (taskSpeed > 20) {
            numTasks -= 2;
        } else {
            numTasks -= 1;
        }
        if (numTasks < 0) {
            numTasks = 0;
        }
        if ((numTasks == 0) && firstTime) {
            firstTime = false;
            System.out.println("I have completed all my tasks");
            this.setSusLevel((int)(this.getSusLevel() * 0.5));
        }
    }

    public boolean equals(Object o) {
        if (o != null && o instanceof BlueAstronaut) {
            BlueAstronaut blue = (BlueAstronaut) o;
            return this.getName().equals(blue.getName()) && this.isFrozen() == blue.isFrozen() &&
                this.getSusLevel() == blue.getSusLevel() && this.numTasks == blue.numTasks &&
                this.taskSpeed == blue.taskSpeed;
        }
        return false;
    }

    public String toString() {
        String blueAstro = super.toString() + " I have " + numTasks + " left over.";
        if (this.getSusLevel() > 15) {
            return blueAstro.toUpperCase();
        }
        return blueAstro;
    }
}