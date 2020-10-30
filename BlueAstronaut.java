//I worked on the homework assignment alone, using only course materials.
import java.util.Arrays;
/**
 *This class represents a BlueAstronaut who is a player in the game.
 *The Crewmate interface is implemented.
 * @author Anusha Nandam
 * @version 1.0
 */
public class BlueAstronaut extends Player implements Crewmate {
    private int numTasks;
    private int taskSpeed;
    private boolean firstTime = true;
    /**
     *constructor assigning default values
     *@param name player's name
     */
    public BlueAstronaut(String name) {
        this(name, 15, 6, 10);
    }
    /**
     *constructor taking in name, susLevel, numTasks, and taskSpeed
     *@param name player's name
     *@param susLevel player's susLevel
     *@param numTasks player's numTasks
     *@param taskSpeed player's taskSpeed
     */
    public BlueAstronaut(String name, int susLevel, int numTasks, int taskSpeed) {
        super(name, susLevel);
        this.numTasks = numTasks;
        if (taskSpeed < 1) {
            taskSpeed = 1;
        }
        this.taskSpeed = taskSpeed;
    }
    /**
     *Getter for numTasks
     *@return numTasks
     */
    public int getNumTasks() {
        return numTasks;
    }
    /**
     *Emergency meeting called and most sus person will be frozen.
     *The person who called the meeting can be frozen.
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
            while (getPlayers()[ii].getSusLevel() == getPlayers()[ii - count].getSusLevel()) {
                if (getPlayers()[ii - count].isFrozen()) {
                    count++;
                } else {
                    return; //First occuurence of two equal non-frozen players
                }
            }

            if (!getPlayers()[ii].isFrozen()) {
                getPlayers()[ii].setFrozen(true);
                break;
            }
        }
        gameOver();
    }
    /**
     *A frozen player can't complete tasks
     *When players complete tasks, a task completion message gets printed once.
     *Also, upon completion, susLevel reduces by 50% once.
     */
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
            this.setSusLevel((int) (this.getSusLevel() * 0.5));
        }
    }
    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof BlueAstronaut) {
            BlueAstronaut blue = (BlueAstronaut) o;
            return this.getName().equals(blue.getName()) && this.isFrozen() == blue.isFrozen()
                && this.getSusLevel() == blue.getSusLevel() && this.numTasks == blue.numTasks
                && this.taskSpeed == blue.taskSpeed;
        }
        return false;
    }
    @Override
    public String toString() {
        String blueAstro = super.toString() + " I have " + numTasks + " left over.";
        if (this.getSusLevel() > 15) {
            return blueAstro.toUpperCase();
        }
        return blueAstro;
    }
}