package TPGUI;

import jade.core.AID;
import jade.core.Agent;
import jade.gui.GuiEvent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;


public class AgentVendeur extends Agent {
    private VendeurGUI gui;

    @Override
    protected void setup() {
        gui = new VendeurGUI(this);
        gui.setVisible(true);
        registerWithDF("vendeur"); // Register in DF
    }

    public void handleGuiEvent(GuiEvent event) {
        if (event.getType() == 1) {
            addBehaviour(new OneShotBehaviour() {
                @Override
                public void action() {
                    AID commissaire = searchAgentByService("commissaire");
                    if (commissaire == null) {
                        gui.logMessage("Commissaire-Priseur non trouvé !");
                        return;
                    }

                    ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
                    request.addReceiver(commissaire);
                    request.setContent("Lancer l'enchère.");
                    send(request);
                    gui.logMessage("Demande envoyée au Commissaire-Priseur.");
                }
            });
        }
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

