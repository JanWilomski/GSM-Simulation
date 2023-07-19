import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class CommunicationThread extends Thread implements Runnable {
    CommunicationLayer layer;
    MessageSystem messageSystem;
    public final BlockingQueue<Message> messageQueue;

    CommunicationThread(CommunicationLayer layer, MessageSystem messageSystem) {
        this.layer = layer;
        this.messageSystem = messageSystem;
        this.messageQueue = new LinkedBlockingQueue<>();


    }
    void setMessageSystem(MessageSystem messageSystem){
        this.messageSystem = messageSystem;
    }

    @Override
    public abstract void run();

    abstract void addMessage(Message message);
    public CommunicationLayer getLayer() {
        return this.layer;
    }
    public int getMessageCount(){
        return messageQueue.size();
    }


}