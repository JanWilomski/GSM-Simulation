import java.util.ArrayList;
import java.util.List;

public abstract class CommunicationLayer{
    List<CommunicationThread> threads = new ArrayList<>();

    MessageSystem messageSystem;

    public CommunicationLayer(MessageSystem messageSystem){
        this.messageSystem = messageSystem;

    }

    void addThread() {
        CommunicationThread thread = createThread();
        threads.add(thread);
        new Thread(thread).start();
    }
    public CommunicationThread getThreadWithMinMessages() {
        CommunicationThread minThread = null;
        int minMessages = Integer.MAX_VALUE;

        for(CommunicationThread thread : threads) {
            int messageCount = thread.getMessageCount();
            if(messageCount < minMessages) {
                minMessages = messageCount;
                minThread = thread;
            }
        }
        return minThread;
    }


    abstract CommunicationThread createThread();

}
