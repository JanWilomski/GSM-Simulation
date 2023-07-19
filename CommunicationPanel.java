import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CommunicationPanel extends JPanel {
    private List<JPanel> layers;
    private JButton addBscLayerButton;
    private JButton removeBscLayerButton;
    private JPanel buttonsPanel;
    private JPanel communicationLayersPanel;
    private LayersController layersController;


    public CommunicationPanel(LayersController layersController) {
        layers = new ArrayList<>();
        buttonsPanel = new JPanel();
        this.layersController=layersController;

        buttonsPanel.add(addBscLayerButton = new JButton("Add BSC Layer"));
        addBscLayerButton.addActionListener(e -> {
            addNewBSCLayer();
            layersController.addBSCLayer();
        });
        buttonsPanel.add(removeBscLayerButton = new JButton("Remove BSC Layer"));
        removeBscLayerButton.addActionListener(e -> {
            removeBSCLayer();
            layersController.removeBSCLayer();
        });

        communicationLayersPanel = new JPanel();
        communicationLayersPanel.setLayout(new BoxLayout(communicationLayersPanel, BoxLayout.X_AXIS));

        this.setLayout(new BorderLayout());
        this.add(communicationLayersPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);


        // Add initial layers
        addNewBTSLayer();
        addNewBTSLayer();
        addNewBSCLayer();

    }

    private void addNewBTSLayer() {
        JPanel btsLayer = new JPanel();
        btsLayer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btsLayer.setPreferredSize(new Dimension(50, 50));
        btsLayer.add(new JLabel("BTS Layer"));
        layers.add(btsLayer);
        communicationLayersPanel.add(btsLayer);

        this.revalidate();
        this.repaint();
    }


    private void addNewBSCLayer() {
        if (layers.size() >= 2) { // only add BSC layer if there are already 2 BTS layers
            JPanel bscLayer = new JPanel();
            bscLayer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            bscLayer.setPreferredSize(new Dimension(50, 50));
            bscLayer.add(new JLabel("BSC Layer"));
            layers.add(layers.size() - 1, bscLayer); // insert BSC layer before the last BTS layer
            communicationLayersPanel.add(bscLayer, layers.size() - 2); // insert at the second last position
            this.revalidate();
            this.repaint();
        }
    }

    private void removeBSCLayer() {
        if (layers.size() > 3) { // only remove BSC layer if there are more than 2 layers
            JPanel layerToRemove = layers.get(layers.size() - 2);
            layers.remove(layerToRemove);
            communicationLayersPanel.remove(layerToRemove);
            this.revalidate();
            this.repaint();
        }
    }
}