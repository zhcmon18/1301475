package ca.cours5b5.pavelzaharciuc.donnees;

import java.util.Map;

public abstract class SourceDeDonnees {

    public abstract void chargerModele(final String cheminSauvegarde, final ListenerChargement listenerChargement);

    public abstract void sauvegarderModele(final String cheminSauvegarde, final Map<String, Object> objetJson);

    public abstract void detruireSauvegarde(String cheminSauvegarde);

    public String getNomModele(String cheminSauvegarde) {
        String[] chaines = cheminSauvegarde.split("/");
        return  chaines[0];
    }

}
