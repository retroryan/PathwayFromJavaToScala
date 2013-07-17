package javaToScala;

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

    public String getNextMessage() {
        long count = CountingService.getInstance().getCount();
        CountingService.getInstance().increment();
        return getReplacementMessage() + " [" + count + "]";
    }
}
