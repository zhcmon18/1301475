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

        initialiser();

        observerPartie();

        Log.d("Atelier06", "VPartie::onFinishInflate");
    }


    private void initialiser(){
        grille = this.findViewById(R.id.grille);
    }

    private void observerPartie(){
        String nomModele = MPartie.class.getSimpleName();

        ControleurObservation.observerModele(nomModele, new ListenerObservateur() {
            @Override
            public void reagirNouveauModele(Modele modele) {
                super.reagirNouveauModele(modele);

                MPartie partie = (MPartie) modele;

                initialiserGrille(partie);

                Log.d("Atelier06", "VPartie::reagirNouveauModele");
            }

            @Override
            public void reagirChangementAuModele(Modele modele){
                afficherParametres((MPartie) modele);
            }
        });
        Log.d("Atelier06", "VPartie::observerPartie");
    }

    private MPartie getPartie(Modele modele){
        return null;
    }

    private void initialiserGrille(MPartie mPartie){
        int hauteur = mPartie.getParametres().getHauteur();
        int largeur = mPartie.getParametres().getLargeur();

        grille.creerGrille(hauteur, largeur);
    }

    private void afficherParametres(MPartie partie){}

    private void miseAJourGrille(MPartie partie) {

    }
}
