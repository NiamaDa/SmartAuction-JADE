package TPGUI;

import jade.core.Agent;
import jade.gui.GuiEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuctionGUI extends JFrame {
    private JTextArea logArea;
    private JButton startAuctionButton;
    private Agent agent;

    public AuctionGUI(Agent agent) {
        this.agent = agent;
        setTitle("Enchère Multi-Agents");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Zone de texte pour afficher les logs
        logArea = new JTextArea();
        logArea.setEditable(false);
        add(new JScrollPane(logArea), BorderLayout.CENTER);

        // Bouton pour démarrer l'enchère
        startAuctionButton = new JButton("Démarrer Enchère");
        startAuctionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Création d'un événement GUI et appel à l'agent
                GuiEvent event = new GuiEvent(this, 1); // Code événement 1 = démarrer enchère
                if (agent instanceof AgentVendeur) {
                    ((AgentVendeur) agent).handleGuiEvent(event);
                }
                logMessage("Enchère démarrée !");
            }
        });

        add(startAuctionButton, BorderLayout.SOUTH);
    }

    // Méthode pour afficher les messages dans la zone de log
    public void logMessage(String message) {
        SwingUtilities.invokeLater(() -> logArea.append(message + "\n"));
    }
}
