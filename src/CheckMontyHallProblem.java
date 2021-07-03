import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class CheckMontyHallProblem {
    public static volatile int checkResultYouChoice=0;

    private static int firstPlayersChoice,
                       choiceOfTheHost;

    private static long numberOfAttempts;



    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
        System.out.println("How many times will we play?");
        while (true) {
            try {
                numberOfAttempts = Long.parseLong(reader.readLine());
                break;
            } catch (Exception e) {
                System.out.println("Something happened. Try again.");
            }
        }
        for (int i = 0; i < numberOfAttempts; i++) {
            if (startGame(true)) checkResultYouChoice++;
        }
        System.out.println("How many times have you won when you change your choice: " + (float) (checkResultYouChoice*100)/numberOfAttempts + " % ");


        checkResultYouChoice = 0;
        for (int i = 0; i < numberOfAttempts; i++) {
            if (startGame(false)) checkResultYouChoice++;
        }
        System.out.println("How many times have you won while keeping your choice: " + (float) (checkResultYouChoice*100)/numberOfAttempts + " % ");
    }

    public static boolean startGame(boolean changeOfChocie){

        List<Door> doors = Arrays.asList(
                new Door("Door number 1", false),
                new Door("Door number 2", false),
                new Door("Door number 3", false));

        int doorWithAPrize = new Random().nextInt(3)+1;
        firstPlayersChoice = new Random().nextInt(3)+1;

        do{
            choiceOfTheHost = new Random().nextInt(3)+1;
        } while ((firstPlayersChoice == choiceOfTheHost) ||  (doorWithAPrize == choiceOfTheHost));

        doors.stream()
                .filter(c -> c.getName().equals("Door number " + doorWithAPrize))
                .findFirst()
                .get()
                .setStatus(true);


        if (changeOfChocie) {
            return doors.stream()
                    .filter(c -> !c.getName().equals("Door number " + choiceOfTheHost))
                    .filter(c -> !c.getName().equals("Door number " + firstPlayersChoice))
                    .findFirst()
                    .get().isStatus();
        } else {
            return doors.stream()
                    .filter(c -> c.getName().equals("Door number " + firstPlayersChoice))
                    .findFirst()
                    .get().isStatus();
        }
    }


}
