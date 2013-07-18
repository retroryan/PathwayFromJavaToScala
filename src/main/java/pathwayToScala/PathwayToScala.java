package pathwayToScala;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PathwayToScala {

    final Pattern msgPattern = Pattern
            .compile("(?:m|M)\\s+(.++)");
    final Pattern quitPattern = Pattern.compile("(?:q|quit)");
    final Pattern countPattern = Pattern.compile("(?:c|count)");

    public static void main(String[] args) throws Exception {
        new PathwayToScala().commandLoop();
    }

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

                if (msgMatcher.find()) {
                    String newMessage = msgMatcher.group(1);
                    MessageReplacementService.getInstance().setReplacementMessage(newMessage);
                    System.out.println("replacement message set to " + newMessage);
                } else if (countMatcher.find()) {
                    //todo make this a random number
                    Integer personId = 5;
                    String nextMessage = MessageReplacementService.getInstance().getNextMessage(personId);
                    System.out.println("nextMessage = " + nextMessage);
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

}
