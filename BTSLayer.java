public class BTSLayer extends CommunicationLayer{
    BTSLayer(MessageSystem messageSystem){
        super(messageSystem);
        CommunicationThread newThread = createThread();
        newThread.start();
        threads.add(newThread);
    }

    @Override
    CommunicationThread createThread() {
        System.out.println("Creating BTS thread...");
        return new BTSThread(this, messageSystem);
    }


}
