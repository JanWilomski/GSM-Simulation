import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SenderPanel extends JPanel {
    private JPanel senderListPanel;
    private JScrollPane scrollPane;
    private JButton addButton;
    private SenderController senderController;

    public SenderPanel(SenderController controller) {
        this.senderController = controller;

        setLayout(new BorderLayout());

        senderListPanel = new JPanel();
        senderListPanel.setLayout(new BoxLayout(senderListPanel, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(senderListPanel);
        add(scrollPane, BorderLayout.CENTER);

        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = JOptionPane.showInputDialog(
                        SenderPanel.this,
                        "Wprowadź krótką wiadomość tekstową:",
                        "Nowa wiadomość",
                        JOptionPane.PLAIN_MESSAGE
                );
                if (message != null && !message.isEmpty()) {
                    senderController.sendMessage(message);
                }
            }
        });

        add(addButton, BorderLayout.PAGE_END);
    }
}
