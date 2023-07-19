public class DefaultSenderController implements SenderController {
    private MessageSystem messageSystem;
    private SenderPanel senderPanel;

    public DefaultSenderController(MessageSystem messageSystem) {
        this.messageSystem = messageSystem;

    }

    @Override
    public void sendMessage(String message) {
        messageSystem.sendMessage(message);
    }
}
