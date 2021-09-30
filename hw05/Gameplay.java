import java.util.Arrays;

public class Gameplay {
    public static void main(String[] args) {
        BlueAstronaut bob = new BlueAstronaut("Bob", 20, 6, 30);
        BlueAstronaut heath = new BlueAstronaut("Heath", 30, 3, 21);
        BlueAstronaut albert = new BlueAstronaut("Albert", 44, 2, 0);
        BlueAstronaut angel = new BlueAstronaut("Angel", 0, 1, 0);
        RedAstronaut liam = new RedAstronaut("Liam", 19, "experienced");
        RedAstronaut suspiciousPerson = new RedAstronaut("Suspicious Person", 100, "expert");

        int winningTeam = 1;  // 1 == BlueAstronaut, 2 == RedAstronaut

        Player[] pl = {bob, heath, albert, angel, liam, suspiciousPerson};
        System.out.println("0. initial state");
        for (Player p : pl) System.out.println(p);

        System.out.println("\n1. liam.sabotage(bob), expected: bob susLevel 30, frozen false");
        liam.sabotage(bob);
        for (Player p : pl) System.out.println(p);

        System.out.println("\n2. liam.freeze(suspiciousPerson), expected: nothing");
        liam.freeze(suspiciousPerson);
        for (Player p : pl) System.out.println(p);

        System.out.println("\n3. liam.freeze(albert), expected: liam susLevel 19, albert frozen true");
        liam.freeze(albert);
        for (Player p : pl) System.out.println(p);

        System.out.println("\n4. albert.emergencyMeeting(), expected: nothing (albert is frozen)");
        albert.emergencyMeeting();
        for (Player p : pl) System.out.println(p);

        System.out.println("\n5. suspiciousPerson.emergencyMeeting(), expected: nothing (bob - heath tie)");
        suspiciousPerson.emergencyMeeting();
        for (Player p : pl) System.out.println(p);

        System.out.println("\n6. bob.emergencyMeeting(), expected: suspiciousPerson frozen true");
        bob.emergencyMeeting();
        for (Player p : pl) System.out.println(p);

        System.out.println("\n7. heath.completeTask(), expected: heath numTask 1");
        heath.completeTask();
        for (Player p : pl) System.out.println(p);

        System.out.println("\n8. heath.completeTask(), expected: I have completed all my tasks");
        heath.completeTask();
        for (Player p : pl) System.out.println(p);

        System.out.println("\n9. heath.completeTask(), expected: nothing");
        heath.completeTask();
        for (Player p : pl) System.out.println(p);

        System.out.println("\n10. liam.freeze(angel), expected: angel frozen true, liam susLevel 38");
        liam.freeze(angel);
        for (Player p : pl) System.out.println(p);

        System.out.println("\n11. liam.sabotage(bob) twice, expected: bob suslevel 46 (30 -> 37 -> 46)");
        liam.sabotage(bob);
        liam.sabotage(bob);
        for (Player p : pl) System.out.println(p);

        System.out.println("\n12. liam.freeze(bob), expected: bob frozen true");
        liam.freeze(bob);
        for (Player p : pl) System.out.println(p);

        if (winningTeam == 1) {
            System.out.println("\n13. angel.emergencyMeeting(), expected: Crewmate win!");
            angel.emergencyMeeting();
        } else if (winningTeam == 2) {
            System.out.println("\n14. liam.sabotage(heath) * 5, expected: heath susLevel 41 (15 -> 18 -> 22 -> 33 -> 41)");
            for (int i = 0; i < 5; i++) liam.sabotage(heath);
            for (Player p : pl) System.out.println(p);
    
            System.out.println("\n15. liam.freeze(heath), expected: Impostors win!");
            liam.freeze(heath);
            for (Player p : pl) System.out.println(p);
        }
    }
}
