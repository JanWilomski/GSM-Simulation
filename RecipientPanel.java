import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecipientPanel extends JPanel {
    private JPanel recipientListPanel;
    private JScrollPane scrollPane;
    private JButton addButton;
    private RecipientController recipientController;
    private List<JPanel> recipients;

    public RecipientPanel(RecipientController recipientController) {
        recipients = new ArrayList<>();
        this.recipientController = recipientController;

        setLayout(new BorderLayout());

        recipientListPanel = new JPanel();
        recipientListPanel.setLayout(new BoxLayout(recipientListPanel, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(recipientListPanel);
        add(scrollPane, BorderLayout.CENTER);

        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phoneNum = newRandomPhoneNumber();
                recipientController.addRecipient(phoneNum);
                addRecipient(phoneNum);
            }
        });


        add(addButton, BorderLayout.PAGE_END);
    }
    private void addRecipient(String phoneNum){
        JPanel recipient = new JPanel();
        recipient.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        recipient.add(new JLabel(phoneNum));
        recipients.add(recipient);
        recipientListPanel.add(recipient);
        recipientController.addRecipient(phoneNum);
        this.revalidate();
        this.repaint();
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
}
