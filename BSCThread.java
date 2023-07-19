public class BSCThread extends CommunicationThread implements Runnable{

    BSCThread(CommunicationLayer layer, MessageSystem messageSystem) {

        super(layer, messageSystem);

    }

    @Override
    public void run() {
        while (true) {
            if (!messageQueue.isEmpty()) {
                try {
                    Thread.sleep((5+((int)(Math.random()*10)))* 1000);
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
