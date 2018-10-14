package ca.cours5b5.pavelzaharciuc.modeles;

import android.util.Log;

import java.util.Map;

import ca.cours5b5.pavelzaharciuc.controleurs.ControleurAction;
import ca.cours5b5.pavelzaharciuc.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.pavelzaharciuc.global.GCommande;
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
        initaliserCouleurCourante();
        grille = new MGrille(this.parametres.getLargeur());
        fournirActionPlacerJeton();
    }

    private void initaliserCouleurCourante() {
        couleurCourante = GCouleur.ROUGE;
    }

    private void fournirActionPlacerJeton() {
        ControleurAction.fournirAction(this, GCommande.JOUER_COUP_ICI, new ListenerFournisseur() {
            @Override
            public void executer(Object... args) {
                Log.d("Atelier07",  MPartie.this.getClass().getSimpleName() + "." + "executer");
                jouerCoup((int) args[0]);
            }
        });
    }

    protected void jouerCoup(int colonne) {
        grille.placerJeton(colonne, couleurCourante);
        prochaineCouleurCourante();

    }

    private void prochaineCouleurCourante() {
        if(couleurCourante.equals(GCouleur.ROUGE)) {
            couleurCourante = GCouleur.JAUNE;
        } else if(couleurCourante.equals(GCouleur.JAUNE)) {
            couleurCourante = GCouleur.ROUGE;
        }
    }

    public MParametresPartie getParametres(){
        return parametres;
    }

    public MGrille getGrille() {
        return grille;
    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) {}

    @Override
    public Map<String, Object> enObjetJson() {return null;}
}
