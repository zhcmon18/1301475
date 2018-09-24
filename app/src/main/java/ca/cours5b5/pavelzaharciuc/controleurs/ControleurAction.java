package ca.cours5b5.pavelzaharciuc.controleurs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ca.cours5b5.pavelzaharciuc.controleurs.interfaces.Fournisseur;
import ca.cours5b5.pavelzaharciuc.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.pavelzaharciuc.global.GCommande;
import ca.cours5b5.pavelzaharciuc.modeles.Modele;

public class ControleurAction {

    private static Map<GCommande, Action> actions = new HashMap<>();
    private static List<Action> fileAttenteExecution = new ArrayList<>();

    static {

        for (GCommande commande : GCommande.values()) {
            actions.put(commande, new Action());
        }
    }

    public static Action demanderAction (GCommande commande) {
        return actions.get(commande);
    }

    public static void fournirAction(Fournisseur fournisseur, GCommande commande,
                                       ListenerFournisseur listenerFournisseur) {

        enregistrerFournisseur(fournisseur, commande, listenerFournisseur);
        executerActionsExecutables();

    }

    static void executerDesQuePossible(Action action) {
        ajouterActionEnFileDAttente(action);
        executerActionsExecutables();
    }

    private static void executerActionsExecutables() {

        for(Iterator<Action> iterateur = fileAttenteExecution.listIterator(); iterateur.hasNext();) {
            Action action = iterateur.next();

            if (siActionExecutable(action)) {
                iterateur.remove();

                executerMaintenant(action);

                lancerObservationSiApplicable(action);
            }
        }
    }

    static boolean siActionExecutable(Action action) {
        return action.listenerFournisseur != null;
    }

    private static synchronized void executerMaintenant(Action action) {
        action.listenerFournisseur.executer(action);
    }

    private static void lancerObservationSiApplicable(Action action) {
        if (action.fournisseur instanceof Modele) {
            ControleurObservation.lancerObservation((Modele) action.fournisseur);
        }
    }

    private static void enregistrerFournisseur(Fournisseur fournisseur, GCommande commande, ListenerFournisseur listenerFournisseur) {
        Action action = actions.get(commande);

        action.fournisseur = fournisseur;
        action.listenerFournisseur = listenerFournisseur;
    }

    private static void ajouterActionEnFileDAttente(Action action) {
        fileAttenteExecution.add(action.cloner());
    }
}
