package pathwayToScala;

public class MessageReplacementService {

    public static final MessageReplacementService MESSAGE_REPLACEMENT_SERVICE = new MessageReplacementService();

    public static MessageReplacementService getInstance() {
        return MESSAGE_REPLACEMENT_SERVICE;
    }

    private MessageReplacementService() {
    }

    private String replacementMessage = "SUPER JAVA!";

    public String getReplacementMessage() {
        return replacementMessage;
    }

    public void setReplacementMessage(String replacementMessage) {
        this.replacementMessage = replacementMessage;
    }

    public String getNextMessage(Integer personId) {
        long count = CountingService.getInstance().getCount();
        CountingService.getInstance().increment(personId);
        return getReplacementMessage() + " [" + count + "]";
    }
}
