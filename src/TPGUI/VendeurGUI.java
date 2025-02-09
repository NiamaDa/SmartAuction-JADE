package TPGUI;

import jade.core.Agent;
import jade.gui.GuiEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class VendeurGUI extends JFrame {
    private JTextArea logArea;
    private JButton startAuctionButton;
    private Agent agent;

    public VendeurGUI(Agent agent) {
        this.agent = agent;

        setTitle("Agent Vendeur");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Zone de log
        logArea = new JTextArea();
        logArea.setEditable(false);
        add(new JScrollPane(logArea), BorderLayout.CENTER);

        // Bouton pour démarrer une enchère
        startAuctionButton = new JButton("Lancer l'enchère");
        startAuctionButton.addActionListener((ActionEvent e) -> {
            GuiEvent event = new GuiEvent(this, 1); // Code événement : Lancer enchère
            ((AgentVendeur) agent).handleGuiEvent(event);
            logMessage("Enchère lancée.");
        });
        add(startAuctionButton, BorderLayout.SOUTH);
    }

    public void logMessage(String message) {
        SwingUtilities.invokeLater(() -> logArea.append(message + "\n"));
    }
}
