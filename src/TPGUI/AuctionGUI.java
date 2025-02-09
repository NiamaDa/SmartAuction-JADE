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
        setTitle("Ench�re Multi-Agents");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Zone de texte pour afficher les logs
        logArea = new JTextArea();
        logArea.setEditable(false);
        add(new JScrollPane(logArea), BorderLayout.CENTER);

        // Bouton pour d�marrer l'ench�re
        startAuctionButton = new JButton("D�marrer Ench�re");
        startAuctionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cr�ation d'un �v�nement GUI et appel � l'agent
                GuiEvent event = new GuiEvent(this, 1); // Code �v�nement 1 = d�marrer ench�re
                if (agent instanceof AgentVendeur) {
                    ((AgentVendeur) agent).handleGuiEvent(event);
                }
                logMessage("Ench�re d�marr�e !");
            }
        });

        add(startAuctionButton, BorderLayout.SOUTH);
    }

    // M�thode pour afficher les messages dans la zone de log
    public void logMessage(String message) {
        SwingUtilities.invokeLater(() -> logArea.append(message + "\n"));
    }
}
