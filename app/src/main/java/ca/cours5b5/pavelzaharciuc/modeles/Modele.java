package ca.cours5b5.pavelzaharciuc.modeles;

import java.util.Map;

import ca.cours5b5.pavelzaharciuc.controleurs.interfaces.Fournisseur;

public abstract class Modele implements Fournisseur {

    public abstract void aPartirObjetJson(Map<String, Object> objetJson);

    public abstract Map<String, Object> enObjetJson();

}
