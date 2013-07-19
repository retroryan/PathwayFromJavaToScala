package pathwayToScala;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PathwayToScala {

    final Pattern msgPattern = Pattern.compile("(?:m|message)\\s+(.++)");
    final Pattern quitPattern = Pattern.compile("(?:q|quit)");
    final Pattern countPattern = Pattern.compile("(?:c|count)");
    final Pattern historyPattern = Pattern.compile("(?:h|history)");
    final Pattern updatePattern = Pattern.compile("(?:u|update)\\s+(\\d++)\\s+(\\d++)");

    public static void main(String[] args) throws Exception {
        new PathwayToScala().commandLoop();
    }

    private Random rand = new Random();

    protected void commandLoop() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(isr);
        try {
            boolean finished = false;
            while (!finished) {
                String command = reader.readLine();
                Matcher msgMatcher = msgPattern.matcher(command);
                Matcher countMatcher = countPattern.matcher(command);
                Matcher quitMatcher = quitPattern.matcher(command);
                Matcher historyMatcher = historyPattern.matcher(command);
                Matcher updateMatcher = updatePattern.matcher(command);

                if (msgMatcher.find()) {
                    String newMessage = msgMatcher.group(1);
                    MessageReplacementService.getInstance().setReplacementMessage(newMessage);
                    System.out.println("replacement message set to " + newMessage);
                } else if (countMatcher.find()) {
                    //make the person id range from 1 to 10
                    Integer personId = rand.nextInt(10) + 1;
                    Person person = new Person(personId, "Liam");
                    String nextMessage = MessageReplacementService.getInstance().getNextMessage(person);
                    System.out.println("nextMessage = " + nextMessage);
                } else if (historyMatcher.find()) {
                    printCountHistory();
                } else if (updateMatcher.find()) {
                    int oldPersonId = Integer.parseInt(updateMatcher.group(1));
                    int newPersonId = Integer.parseInt(updateMatcher.group(2));
                    CountingService.getInstance().updatePersonId(oldPersonId, newPersonId);
                } else if (quitMatcher.find()) {
                    finished = true;
                } else {
                    System.out.println("Unknown command! " + command
                            + ". Try:\n"
                            + "'m Message' to set a new message\n"
                            + "'c' current count of the Counting Service\n"
                            + "'q' for quit");
                }
            }
        } finally {
            reader.close();
            isr.close();
        }
    }

    private void printCountHistory() {
        Map<Integer, List<CountEntry>> mapCountEntries = CountEntry.getMapCountEntries();

        for (Integer personId : mapCountEntries.keySet()) {
            System.out.println("Person Id " + personId + " made increments on the following dates");
            List<CountEntry> countEntries = mapCountEntries.get(personId);
            for (CountEntry countEntry : countEntries) {
                System.out.println(" " + countEntry.toString());
            }
        }
    }

}
