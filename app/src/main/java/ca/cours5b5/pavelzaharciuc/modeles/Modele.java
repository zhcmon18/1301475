package ca.cours5b5.pavelzaharciuc.modeles;

import ca.cours5b5.pavelzaharciuc.donnees.Chemin;
import ca.cours5b5.pavelzaharciuc.donnees.Sauvegardable;
import ca.cours5b5.pavelzaharciuc.donnees.Sauvegarde;

public abstract class Modele implements Sauvegardable {
    public Chemin getCheminDeSauvegarde() {
        return Sauvegarde.getCheminDeSauvegarde(getClass());
    }
}
