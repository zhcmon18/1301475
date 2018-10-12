package ca.cours5b5.pavelzaharciuc.modeles;

import java.util.List;
import java.util.Map;

import ca.cours5b5.pavelzaharciuc.exceptions.ErreurSerialisation;
import ca.cours5b5.pavelzaharciuc.global.GCouleur;

public class MColonne extends Modele {

    private List<GCouleur> jetons;

    public MColonne() {}

    public List<GCouleur> getJetons() {
        return jetons;
    }

    public void placerJeton(GCouleur couleur) {

    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation {}

    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {
        return null;
    }
}
