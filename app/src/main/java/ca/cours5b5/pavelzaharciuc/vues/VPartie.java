package ca.cours5b5.pavelzaharciuc.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import ca.cours5b5.pavelzaharciuc.R;
import ca.cours5b5.pavelzaharciuc.controleurs.ControleurObservation;
import ca.cours5b5.pavelzaharciuc.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.pavelzaharciuc.modeles.MPartie;
import ca.cours5b5.pavelzaharciuc.modeles.Modele;

public class VPartie extends Vue {

    private VGrille grille;

    public VPartie(Context context) {
        super(context);
    }

    public VPartie(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VPartie(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();
        Log.d("Atelier07", this.getClass().getSimpleName() + "." + "onFinishInflate");

        initialiser();

        observerPartie();
    }


    private void initialiser(){
        grille = this.findViewById(R.id.grille);
    }

    private void observerPartie(){
        Log.d("Atelier07", "VPartie.observerPartie");

        String nomModele = MPartie.class.getSimpleName();

        ControleurObservation.observerModele(nomModele, new ListenerObservateur() {
            @Override
            public void reagirNouveauModele(Modele modele) {
                Log.d("Atelier07", "VPartie.reagirNouveauModele");

                MPartie partie = (MPartie) modele;

                initialiserGrille(partie);
            }

            @Override
            public void reagirChangementAuModele(Modele modele){
                Log.d("Atelier07", "VPartie.reagirChangementAuModele");

                miseAJourGrille((MPartie) modele);
            }
        });
    }

    private void initialiserGrille(MPartie mPartie){
        int hauteur = mPartie.getParametres().getHauteur();
        int largeur = mPartie.getParametres().getLargeur();

        grille.creerGrille(hauteur, largeur);
    }

    private void afficherParametres(MPartie partie){}

    private void miseAJourGrille(MPartie partie) {
        grille.afficherJetons(partie.getGrille());
    }
}
