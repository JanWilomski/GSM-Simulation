public class DefaultRecipientController implements RecipientController {
    private MessageSystem messageSystem;


    public DefaultRecipientController(MessageSystem messageSystem) {
        this.messageSystem = messageSystem;

    }


    @Override
    public void addRecipient(String phoneNumber) {
        messageSystem.getRecipients().add(new Recipient(phoneNumber));

    }
}
