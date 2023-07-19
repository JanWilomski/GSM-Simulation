public class DefaultLayersController implements LayersController{
    private MessageSystem messageSystem;

    public DefaultLayersController(MessageSystem messageSystem){
        this.messageSystem = messageSystem;

    }
    @Override
    public void addBSCLayer(){
        messageSystem.addBSCLayer();
    }

    @Override
    public void removeBSCLayer() {
        messageSystem.removeBSCLayer();
    }

}
