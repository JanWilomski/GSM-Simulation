import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MessageSystem{
    private List<CommunicationLayer> communicationLayers = new ArrayList<>();
    private List<Recipient> recipients = new ArrayList<>();

    public List<Recipient> getRecipients() {
        return recipients;
    }

    public void sendMessage(String content) {
        String num = getRandomRecipient().getRecipientPhoneNumber();
        // Tworzenie nowej wiadomości
        Message message = new Message(content,num);
        //message.encode(content, num);
        System.out.println("Wyslano wiadomosc o tresci: \"" + content +"\"");

        // Wysyłanie wiadomości do pierwszej warstwy BTS
        sendMessageToFirstLayer(message);

    }

    MessageSystem(){

        communicationLayers.add(new BTSLayer(this));
        communicationLayers.add(new BSCLayer(this));
        communicationLayers.add(new BTSLayer(this));
    }
    public Recipient getRandomRecipient(){
        return recipients.get((int)(recipients.size()*Math.random()));
    }

    public void addBSCLayer(){
        BSCLayer bsc = new BSCLayer(this);
        communicationLayers.add(communicationLayers.size() - 1, bsc);
        System.out.println("Dodano warstwę BSC");
    }

    public synchronized void passMessage(Message message, CommunicationThread senderThread) {
        int currentLayerIndex = this.communicationLayers.indexOf(senderThread.getLayer());

        // Upewnij się, że nie jest to ostatnia warstwa.
        if (currentLayerIndex != this.communicationLayers.size() - 1) {
            CommunicationLayer nextLayer = this.communicationLayers.get(currentLayerIndex + 1);

            // Wybierz wątek z najmniejszą liczbą wiadomości.
            CommunicationThread minThread = nextLayer.getThreadWithMinMessages();
            minThread.setMessageSystem(this);


            if (minThread != null) {
                // Dodaj wiadomość do kolejki wątku.
                minThread.addMessage(message);
                System.out.println("Wiadomość przekazana do następnej warstwy");
            } else {
                System.out.println("Nie można przekazać wiadomości, brak dostępnych wątków");
            }
        } else {
            // Jeżeli to jest ostatnia warstwa, to jest to warstwa BTS, więc wiadomość powinna być dostarczona do odbiorcy.
            System.out.println("Wiadomość dostarczona do odbiorcy");
            //message.decode(message.getContent());
            System.out.println(message.getContent());
        }
    }



    public void removeBSCLayer() {
        if(communicationLayers.size() > 2){
            communicationLayers.remove(communicationLayers.size() - 2);
            System.out.println("Usunięto ostatnią warstwę BSC.");
        } else {
            System.out.println("Nie można usunąć więcej warstw BSC.");
        }
    }
    void addRecipient() {
        recipients.add(new Recipient(newRandomPhoneNumber()));
    }
    public void removeRecipient(Recipient recipient) {
        recipients.remove(recipient);
    }

    String newRandomPhoneNumber(){
        Random random = new Random();
        StringBuilder phoneNumberBuilder = new StringBuilder();

        // Dodaj pierwszą cyfrę (1-9)
        phoneNumberBuilder.append(random.nextInt(9) + 1);

        // Dodaj kolejne 8 cyfr
        for (int i = 0; i < 8; i++) {
            phoneNumberBuilder.append(random.nextInt(10));
        }
        return phoneNumberBuilder.toString();
    }
    public synchronized void sendMessageToFirstLayer(Message message) {
        // Pobierz pierwszą warstwę - powinna to być warstwa BTS
        CommunicationLayer firstLayer = communicationLayers.get(0);

        // Wyszukaj wątek z najmniejszą liczbą wiadomości
        CommunicationThread targetThread = firstLayer.getThreadWithMinMessages();
        //targetThread.start();

        // Dodaj wiadomość do wybranego wątku

        targetThread.addMessage(message);


    }

}

