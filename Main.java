import javax.swing.*;
import java.awt.*;

class Main {
    public static void main(String[] args) {

        MessageSystem messageSystem = new MessageSystem();
        DefaultLayersController layersController = new DefaultLayersController(messageSystem);
        SenderController senderController = new DefaultSenderController(messageSystem);
        DefaultRecipientController recipientController = new DefaultRecipientController(messageSystem);
        RecipientPanel recipientPanel = new RecipientPanel(recipientController);


        // Stworzenie nowego okna JFrame
        JFrame frame = new JFrame("SMS Communication System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // Utworzenie nowych paneli
        SenderPanel senderPanel = new SenderPanel(senderController);

        CommunicationPanel btsAndBcsPanel = new CommunicationPanel(layersController);




        // Dodanie paneli do okna
        frame.getContentPane().add(senderPanel, BorderLayout.WEST);
        frame.getContentPane().add(btsAndBcsPanel, BorderLayout.CENTER);
        frame.getContentPane().add(recipientPanel, BorderLayout.EAST);

        // Ustawienie rozmiaru okna na dostatecznie duży, aby pomieścić wszystkie panele
        frame.setSize(1200, 800);

        // Wyświetlenie okna
        frame.setVisible(true);




    }
}