public class BSCLayer extends CommunicationLayer{
    MessageSystem messageSystem;
    BSCLayer(MessageSystem messageSystem){
        super(messageSystem);
        CommunicationThread newThread = createThread();
        newThread.start();
        threads.add(newThread);

    }
    @Override
    CommunicationThread createThread() {
        System.out.println("Creating BSC thread...");
        return new BSCThread(this, messageSystem);
    }
}
