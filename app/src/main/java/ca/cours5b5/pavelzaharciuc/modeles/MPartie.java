package ca.cours5b5.pavelzaharciuc.modeles;

import java.util.Map;

import ca.cours5b5.pavelzaharciuc.global.GCouleur;
import ca.cours5b5.pavelzaharciuc.serialisation.AttributSerialisable;

public class MPartie extends Modele {

    @AttributSerialisable
    public MParametresPartie parametres;
    private final String __parametres = "parametres";

    private  MGrille grille;
    private GCouleur couleurCourante;

    public MPartie(MParametresPartie parametres){
        this.parametres = parametres;
    }

    public MParametresPartie getParametres(){
        return parametres;
    }

    private void initaliserCouleurCourante() {

    }

    private void fournirActionPlacerJeton() {

    }

    protected void jouerCoup(int colonne) {

    }

    private void prochaineCouleurCourante() {

    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) {}

    @Override
    public Map<String, Object> enObjetJson() {return null;}
}
