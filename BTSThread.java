public class BTSThread extends CommunicationThread{

    BTSThread(CommunicationLayer layer, MessageSystem messageSystem) {

        super(layer, messageSystem);

    }

    @Override
    public void run() {
        while (true) {
            if (!messageQueue.isEmpty()) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                try {
                    this.messageSystem.passMessage(messageQueue.take(),this);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

        }
    }

    @Override
    void addMessage(Message message) {

        try {

            messageQueue.put(message);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }


}
