package TPGUI;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class AgentAcheteur extends Agent {
    private AcheteurGUI gui;
    private boolean appelDOuvert = false;

    @Override
    protected void setup() {
        gui = new AcheteurGUI(this);
        gui.setVisible(true);
        registerWithDF("acheteur"); // Register in DF

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage message = receive();
                if (message != null && message.getPerformative() == ACLMessage.CFP) {
                    gui.logMessage("Appel d'offres reçu : " + message.getContent());
                    appelDOuvert = true;
                } else {
                    block();
                }
            }
        });
    }

    public void envoyerProposition(int prix) {
        if (!appelDOuvert) {
            gui.logMessage("Impossible d'envoyer une offre : aucun appel d'offres ouvert.");
            return;
        }

        AID commissaire = searchAgentByService("commissaire"); // Search in DF
        if (commissaire == null) {
            gui.logMessage("Commissaire-Priseur non trouvé !");
            return;
        }

        ACLMessage proposition = new ACLMessage(ACLMessage.PROPOSE);
        proposition.addReceiver(commissaire);
        proposition.setContent(String.valueOf(prix));
        send(proposition);
        gui.logMessage("Offre envoyée : " + prix);
        appelDOuvert = false;
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

    private AID searchAgentByService(String serviceType) {
        DFAgentDescription template = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType(serviceType);
        template.addServices(sd);
        try {
            DFAgentDescription[] result = DFService.search(this, template);
            if (result.length > 0) {
                return result[0].getName();
            }
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        return null;
    }
}
