package ca.cours5b5.pavelzaharciuc.controleurs;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import ca.cours5b5.pavelzaharciuc.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.pavelzaharciuc.modeles.MParametres;
import ca.cours5b5.pavelzaharciuc.modeles.MPartie;
import ca.cours5b5.pavelzaharciuc.modeles.Modele;

public class ControleurObservation {

    private static Map<Modele, ListenerObservateur> observations;
    private static MPartie partie;

    static {
        observations = new HashMap<>();
    }

    public static void observerModele(String nomModele, final ListenerObservateur listenerObservateur) {
        Log.d("Atelier07", "ControleurObservation::observerModele");

        if(nomModele.equals("MParametres")) {
            observations.put(MParametres.instance, listenerObservateur);
            lancerObservationPremiereFois(MParametres.instance);
        } else {
            partie = new MPartie(MParametres.instance.getParametresPartie().cloner());
            observations.put(ControleurObservation.partie, listenerObservateur);
            lancerObservationPremiereFois(ControleurObservation.partie);
        }
    }

    public static void lancerObservationPremiereFois(Modele modele) {
        ListenerObservateur listenerObservateur = observations.get(modele);

        if(listenerObservateur != null) {
            listenerObservateur.reagirNouveauModele(modele);
        }
    }

    public static void lancerObservation (Modele modele) {
        ListenerObservateur listenerObservateur = observations.get(modele);

        if(listenerObservateur != null) {
            listenerObservateur.reagirChangementAuModele(modele);
        }
    }
}
