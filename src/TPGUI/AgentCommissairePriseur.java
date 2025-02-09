package TPGUI;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import java.util.HashMap;

public class AgentCommissairePriseur extends Agent {
    private AuctioneerGUI gui;
    private HashMap<String, Integer> offres = new HashMap<>();
    private boolean appelDOuvert = false;

    @Override
    protected void setup() {
        gui = new AuctioneerGUI(this);
        gui.setVisible(true);
        registerWithDF("commissaire"); // Register in DF

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage message = receive();
                if (message != null) {
                    if (message.getPerformative() == ACLMessage.REQUEST) {
                        ouvrirAppelDOffres();
                    } else if (message.getPerformative() == ACLMessage.PROPOSE) {
                        traiterProposition(message);
                    }
                } else {
                    block();
                }
            }

            private void ouvrirAppelDOffres() {
                appelDOuvert = true;
                ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
                cfp.setContent("Appel d'offres : Faites vos propositions.");

                DFAgentDescription[] acheteurs = searchAgentsByService("acheteur");
                if (acheteurs.length == 0) {
                    gui.logMessage("Aucun acheteur trouvé !");
                    return;
                }

                for (DFAgentDescription acheteur : acheteurs) {
                    cfp.addReceiver(acheteur.getName());
                }
                send(cfp);
                gui.logMessage("Appel d'offres ouvert et message envoyé.");
            }
        });
    }

    private void traiterProposition(ACLMessage message) {
        if (!appelDOuvert) {
            gui.logMessage("Offre ignorée : Appel d'offres non ouvert.");
            return;
        }

        String acheteur = message.getSender().getLocalName();
        int prix = Integer.parseInt(message.getContent());
        offres.put(acheteur, prix);
        gui.logMessage("Offre reçue de " + acheteur + " : " + prix);

        if (offres.size() == 3) { // Toutes les propositions reçues
            choisirMeilleurAcheteur();
        }
    }

    private void choisirMeilleurAcheteur() {
        String meilleurAcheteur = null;
        int meilleurPrix = 0;

        for (String acheteur : offres.keySet()) {
            int prix = offres.get(acheteur);
            if (prix > meilleurPrix) {
                meilleurPrix = prix;
                meilleurAcheteur = acheteur;
            }
        }

        ACLMessage inform = new ACLMessage(ACLMessage.INFORM);
        inform.addReceiver(new AID("Vendeur", AID.ISLOCALNAME));
        inform.setContent("Gagnant : " + meilleurAcheteur + " avec " + meilleurPrix);
        send(inform);
        gui.logMessage("Meilleure offre : " + meilleurAcheteur + " avec " + meilleurPrix);
        appelDOuvert = false;
        offres.clear();
    }

    private DFAgentDescription[] searchAgentsByService(String serviceType) {
        DFAgentDescription template = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType(serviceType);
        template.addServices(sd);

        try {
            return DFService.search(this, template);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        return new DFAgentDescription[0];
    }

    private void registerWithDF(String serviceType) {
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType(serviceType);
        sd.setName(getLocalName());
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }
}
