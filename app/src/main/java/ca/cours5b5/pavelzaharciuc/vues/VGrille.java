package ca.cours5b5.pavelzaharciuc.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

import ca.cours5b5.pavelzaharciuc.global.GCouleur;
import ca.cours5b5.pavelzaharciuc.modeles.MGrille;

public class VGrille extends GridLayout {

    public VGrille(Context context) {
        super(context);
    }

    public VGrille(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VGrille(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int nombreRangees;

    private class Colonne extends ArrayList<VCase> {}

    private List<Colonne> colonnesDeCases;
    private List<VEntete> entetes;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }


    void creerGrille (int hauteur, int largeur) {
        ajouterEnTetes(largeur);
        ajouterCases(hauteur,largeur);
    }

    private void initialiserColonnes (int largeur) {}

    private void ajouterEnTetes(int largeur) {
        for(int i = 0; i < largeur; ++i) {
            VEntete vEntete = new VEntete(getContext(), i);
            addView(vEntete, getMiseEnPageEntete(i));
        }
    }

    private LayoutParams getMiseEnPageEntete(int colonne) {
        float nbRangees = 3;
        float nbColonnes = 3;

        Spec specRangee = GridLayout.spec(0, nbRangees);
        Spec specColonne = GridLayout.spec(colonne, nbColonnes);

        LayoutParams layoutParams = new LayoutParams(specRangee, specColonne);

        layoutParams.width = 0;
        layoutParams.height = 0;
        layoutParams.setGravity(Gravity.FILL);

        return layoutParams;
    }

    private void ajouterCases(int hauteur, int largeur){
        for(int i = 1; i < hauteur + 1; ++i){
            for(int j = 0; j < largeur; ++j) {
                VCase temp = new VCase(getContext(), j, (hauteur - i));
                addView(temp, getMiseEnPageCase(i, j));
            }
        }
    }

    private LayoutParams getMiseEnPageCase(int rangee, int colonne){
        float nbRangees = 1;
        float nbColonnes = 1;

        Spec specRangee = GridLayout.spec(rangee, nbRangees);
        Spec specColonne = GridLayout.spec(colonne, nbColonnes);

        LayoutParams layoutParams = new LayoutParams(specRangee, specColonne);

        layoutParams.width = 0;
        layoutParams.height = 0;
        layoutParams.setGravity(Gravity.FILL);

        return layoutParams;
    }

    private void demanderActionEntete() {

    }

    private void installerListenerEntete(VEntete entete, final int colonne) {

    }

    void afficherJetons(MGrille grille) {

    }

    private void afficherJeton(int colonne, int rangee, GCouleur jeon) {

    }
}
