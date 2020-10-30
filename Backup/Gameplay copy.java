public class Gameplay {
    public static void main(String[] args) {
        BlueAstronaut b1 = new BlueAstronaut("Bob", 20, 6, 30);
        BlueAstronaut b2 = new BlueAstronaut("Heath", 30, 3, 21);
        BlueAstronaut b3 = new BlueAstronaut("Albert", 44, 2, 8);
        BlueAstronaut b4 = new BlueAstronaut("Angel", 0, 0, 0);
        RedAstronaut r1 = new RedAstronaut("Liam", 19, "experienced");
        RedAstronaut r2 = new RedAstronaut("Suspicious Person", 100, "expert");
        //b3.gameOver();
        r1.sabotage(b1);
        //b3.gameOver();
        System.out.println("susLevel = " + b1.getSusLevel() + ", frozen = " + b1.isFrozen());
        r1.freeze(r2);
        //b3.gameOver();
        r1.freeze(b3);
        //b3.gameOver();
        System.out.println("Liam susLevel = " + r1.getSusLevel() + ", Albert frozen = " + b3.isFrozen());
        b3.emergencyMeeting();


        r2.emergencyMeeting();
        b1.emergencyMeeting();
        System.out.println("Sus person frozen = " + r2.isFrozen());
        b2.completeTask();
        System.out.println("numTasks = " + b2.getNumTasks());
        b2.completeTask();
        System.out.println("numTasks = " + b2.getNumTasks() + " susLevel = " + b2.getSusLevel());
        b2.completeTask();
        r1.freeze(b4);
        System.out.println("Liam susLevel = " + r1.getSusLevel() + ", Angel frozen = " + b4.isFrozen());
        r1.sabotage(b1);
        r1.sabotage(b1);
        System.out.println("Bob susLevel = " + b1.getSusLevel());
        r1.freeze(b1);
        System.out.println("Bob frozen = " + b1.isFrozen());
        //Option 1
        /*
        b4.emergencyMeeting();
        System.out.println("Liam frozen = " +r1.isFrozen());
        */

        //Option 2
        r1.sabotage(b2);
        r1.sabotage(b2);
        r1.sabotage(b2);
        r1.sabotage(b2);
        r1.sabotage(b2);
        System.out.println("Heath susLevel = " + b2.getSusLevel());
        r1.freeze(b2);
        System.out.println("Heath frozen = " + b2.isFrozen()); 

    }
}