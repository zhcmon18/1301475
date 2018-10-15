package ca.cours5b5.pavelzaharciuc.modeles;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.cours5b5.pavelzaharciuc.exceptions.ErreurSerialisation;
import ca.cours5b5.pavelzaharciuc.global.GCouleur;

public class MColonne extends Modele {

    private List<GCouleur> jetons;

    public MColonne() {
        jetons = new ArrayList<>();
    }

    public List<GCouleur> getJetons() {
        return jetons;
    }

    public void placerJeton(GCouleur couleur) {
        jetons.add(couleur);
        //Log.d("Size", "" + jetons.size());
    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation {}

    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {
        return null;
    }
}
