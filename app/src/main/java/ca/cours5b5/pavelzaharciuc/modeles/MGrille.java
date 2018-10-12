package ca.cours5b5.pavelzaharciuc.modeles;

import java.util.List;
import java.util.Map;

import ca.cours5b5.pavelzaharciuc.exceptions.ErreurSerialisation;
import ca.cours5b5.pavelzaharciuc.global.GCouleur;

public class MGrille extends Modele {

    private List<MColonne> colonnes;

    public MGrille(int largeur) {

    }

    public List<MColonne> getColonnes() {
        return colonnes;
    }

    public void placerJetonint(int colonne, GCouleur couleur) {

    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation {}

    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {
        return null;
    }
}
