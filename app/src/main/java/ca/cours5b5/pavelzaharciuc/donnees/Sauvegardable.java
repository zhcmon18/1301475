package ca.cours5b5.pavelzaharciuc.donnees;

import ca.cours5b5.pavelzaharciuc.serialisation.Constructible;
import ca.cours5b5.pavelzaharciuc.serialisation.Serialisable;

public interface Sauvegardable extends Constructible, Serialisable {
    Chemin getCheminDeSauvegarde();
}
