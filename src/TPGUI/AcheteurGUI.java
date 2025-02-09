package TPGUI;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AcheteurGUI extends JFrame {
    private JTextField offerField;
    private JButton sendOfferButton;
    private JTextArea logArea;
    private Agent agent;

    public AcheteurGUI(Agent agent) {
        this.agent = agent;

        setTitle("Agent Acheteur : " + agent.getLocalName());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Zone de log
        logArea = new JTextArea();
        logArea.setEditable(false);
        add(new JScrollPane(logArea), BorderLayout.CENTER);

        // Zone pour soumettre une offre
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        offerField = new JTextField(10);
        panel.add(new JLabel("Votre offre :"));
        panel.add(offerField);

        sendOfferButton = new JButton("Envoyer Offre");
        sendOfferButton.addActionListener((ActionEvent e) -> {
            int prix = Integer.parseInt(offerField.getText());
            ((AgentAcheteur) agent).envoyerProposition(prix);
            logMessage("Offre soumise : " + prix);
        });
        panel.add(sendOfferButton);

        add(panel, BorderLayout.SOUTH);
    }

    public void logMessage(String message) {
        SwingUtilities.invokeLater(() -> logArea.append(message + "\n"));
    }
}
