package ca.cours5b5.pavelzaharciuc.controleurs;

import java.util.HashMap;
import java.util.Map;

import ca.cours5b5.pavelzaharciuc.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.pavelzaharciuc.modeles.MParametres;
import ca.cours5b5.pavelzaharciuc.modeles.Modele;

public class ControleurObservation {

    private static Map<Modele, ListenerObservateur> observations = new HashMap<>();

    static {

    }

    public static void observerModele(String noModele, final ListenerObservateur listenerObservateur) {
        observations.put(MParametres.instance, listenerObservateur);
        lancerObservation(MParametres.instance);
    }

    public static void lancerObservation (Modele modele) {
        (observations.get(modele)).reagirChangementAuModele(modele);
    }
}
