package TPGUI;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class MainContainer {
    public static void main(String[] args) {
        try {
            Runtime rt = Runtime.instance();
            Profile p = new ProfileImpl();
            p.setParameter(Profile.GUI, "true");

            AgentContainer mainContainer = rt.createMainContainer(p);

            // Démarrer les agents
            AgentController vendeur = mainContainer.createNewAgent("Vendeur", AgentVendeur.class.getName(), null);
            vendeur.start();

            AgentController commissaire = mainContainer.createNewAgent("CommissairePriseur", AgentCommissairePriseur.class.getName(), null);
            commissaire.start();

            for (int i = 1; i <= 3; i++) {
                AgentController acheteur = mainContainer.createNewAgent("Acheteur" + i, AgentAcheteur.class.getName(), null);
                acheteur.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

