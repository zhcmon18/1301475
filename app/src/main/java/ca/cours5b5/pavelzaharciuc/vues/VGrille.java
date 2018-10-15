package ca.cours5b5.pavelzaharciuc.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

import ca.cours5b5.pavelzaharciuc.controleurs.Action;
import ca.cours5b5.pavelzaharciuc.controleurs.ControleurAction;
import ca.cours5b5.pavelzaharciuc.global.GCommande;
import ca.cours5b5.pavelzaharciuc.global.GCouleur;
import ca.cours5b5.pavelzaharciuc.modeles.MColonne;
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

    private List<VEntete> entetes;

    private VCase[][] lesCases;

    private Action actionEnTete;

    @Override
    protected void onFinishInflate(){
        Log.d("Atelier07", this.getClass().getSimpleName() + "::" + "onFinishInflate");
        super.onFinishInflate();

    }

    void creerGrille (int hauteur, int largeur){
        initialiserTableauDeCases(hauteur, largeur);
        ajouterEnTetes(largeur);
        ajouterCases(hauteur,largeur);
    }

    private void initialiserTableauDeCases(int hauteur, int largeur) {
        lesCases = new VCase[hauteur][largeur];
    }

    private void demanderActionEntete(){
        actionEnTete = ControleurAction.demanderAction(GCommande.JOUER_COUP_ICI);
    }

    private void ajouterEnTetes(int largeur){
        for(int i = 0; i < largeur; ++i){
            VEntete entete = new VEntete(getContext(), i);
            installerListenerEntete(entete, i);
            addView(entete, getMiseEnPageEntete(i));
        }
        demanderActionEntete();
    }

    private void installerListenerEntete(VEntete entete, final int colonne){
        entete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Atelier07", VGrille.this.getClass().getSimpleName() + "." + "onClick");
                actionEnTete.setArguments(colonne);
                actionEnTete.executerDesQuePossible();
            }
        });
    }

    void afficherJetons(MGrille grille){

        List<MColonne> colonnes = grille.getColonnes();

        for(int colonne = 0; colonne < colonnes.size(); ++colonne){

            MColonne colonneActuelle = colonnes.get(colonne);

            for(int rangee = 0; rangee < colonneActuelle.getJetons().size(); ++rangee){
                afficherJeton(colonne, rangee, colonneActuelle.getJetons().get(rangee));
            }
        }
    }

    private void afficherJeton(int colonne, int rangee, GCouleur jeton){
        if(rangee <= lesCases.length - 1) {
            lesCases[rangee][colonne].afficherJeton(jeton);
        }
    }

    private LayoutParams getMiseEnPageEntete(int colonne){
        float poidsRangee = 3;
        float poidsColonne = 3;

        Spec specRangee = GridLayout.spec(0, poidsRangee);
        Spec specColonne = GridLayout.spec(colonne, poidsColonne);

        LayoutParams mesParams = new LayoutParams(specRangee, specColonne);

        mesParams.width = 0;
        mesParams.height = 0;
        mesParams.setGravity(Gravity.FILL);

        return mesParams;
    }

    private void ajouterCases(int hauteur, int largeur){
        for(int i = 1; i < hauteur + 1; ++i){
            for(int j = 0; j < largeur; ++j) {
                VCase tempCase= new VCase(getContext(), j, (hauteur - i));
                addView(tempCase, getMiseEnPageCase(i, j));
                lesCases[hauteur - i][j] = tempCase;
            }
        }
    }

    private LayoutParams getMiseEnPageCase(int rangee, int colonne){
        float poidsRangee = 1;
        float poidsColonne = 1;

        Spec specRangee = GridLayout.spec(rangee, poidsRangee);
        Spec specColonne = GridLayout.spec(colonne, poidsColonne);

        LayoutParams mesParams = new LayoutParams(specRangee, specColonne);

        mesParams.width = 0;
        mesParams.height = 0;
        mesParams.setGravity(Gravity.FILL);

        return mesParams;
    }
}
