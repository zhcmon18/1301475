package ca.cours5b5.pavelzaharciuc.modeles;

import java.util.ArrayList;
import java.util.List;

import ca.cours5b5.pavelzaharciuc.serialisation.AttributSerialisable;

public class MParametres extends Modele {

    public static MParametres instance;

    @AttributSerialisable
    public Integer hauteur;
    @AttributSerialisable
    public Integer largeur;
    @AttributSerialisable
    public Integer pourGagner;

    private List<Integer> choixHauteur;
    private List<Integer> choixLargeur;
    private List<Integer> choixPourGagner;

    public MParametres() {

    }

    private List<Integer> genererListeChoix(int min, int max) {
        List<Integer> listeChoix = new ArrayList();

        return listeChoix;
    }

    private void genererListesDeChoix() {

    }

    private void genererListesChoixHauteur() {

    }

    private void genererListesChoixLargeur() {

    }

    private void genererListesChoixPourGagner() {

    }

    public void setHauteur(Integer hauteur) {
        this.hauteur = hauteur;
    }

    public void setLargeur(Integer largeur) {
        this.largeur = largeur;
    }

    public void setPourGagner(Integer pourGagner) {
        this.pourGagner = pourGagner;
    }


    public List<Integer> getChoixHauteur() {
        return choixHauteur;
    }

    public List<Integer> getChoixLargeur() {
        return choixLargeur;
    }

    public List<Integer> getChoixPourGagner() {
        return choixPourGagner;
    }
}
