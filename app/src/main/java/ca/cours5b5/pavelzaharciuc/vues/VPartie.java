package ca.cours5b5.pavelzaharciuc.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import ca.cours5b5.pavelzaharciuc.R;
import ca.cours5b5.pavelzaharciuc.controleurs.ControleurObservation;
import ca.cours5b5.pavelzaharciuc.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.pavelzaharciuc.exceptions.ErreurObservation;
import ca.cours5b5.pavelzaharciuc.global.GConstantes;
import ca.cours5b5.pavelzaharciuc.global.GCouleur;
import ca.cours5b5.pavelzaharciuc.modeles.MParametresPartie;
import ca.cours5b5.pavelzaharciuc.modeles.MPartie;
import ca.cours5b5.pavelzaharciuc.modeles.Modele;


public class VPartie extends Vue {

    private static TextView textViewJoueur;
    private static VGrille grille;

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
    protected void onFinishInflate() {
        super.onFinishInflate();

        initialiser();

        observerPartie();

    }

    private void initialiser() {
        textViewJoueur = findViewById(R.id.texte_joueur);

        grille = findViewById(R.id.grille);
    }

    private void observerPartie() {

        ControleurObservation.observerModele(getNomModele(),
                new ListenerObservateur() {
                    @Override
                    public void reagirNouveauModele(Modele modele) {

                        MPartie partie = getPartie(modele);

                        preparerAffichage(partie);

                        miseAJourGrille(partie);

                    }

                    @Override
                    public void reagirChangementAuModele(Modele modele) {

                        MPartie partie = getPartie(modele);

                        miseAJourGrille(partie);

                    }
                });
    }

    private void preparerAffichage(MPartie partie) {

        MParametresPartie parametresPartie = partie.getParametres();

        grille.creerGrille(parametresPartie.getHauteur(), parametresPartie.getLargeur());

    }

    private MPartie getPartie(Modele modele){

        try{

            return (MPartie) modele;

        }catch(ClassCastException e){

            throw new ErreurObservation(e);

        }

    }

    private void miseAJourGrille(MPartie partie){

        grille.afficherJetons(partie.getGrille());

        setCouleurJoueur(grille.getCouleurCourante());

        for (VEntete entete : grille.getEntetes()) {
            grille.desactiverOuActiverEntetes(entete);
        }

    }

    protected String getNomModele() {
        return MPartie.class.getSimpleName();
    }


    public static  void setCouleurJoueur(GCouleur couleurCourante) {

        if(couleurCourante == null || couleurCourante == GCouleur.ROUGE) {
            textViewJoueur.setBackgroundColor(grille.getResources().getColor(R.color.ROUGE, null));
            setTexteJoueur(GConstantes.JOUEUR_1);

        } else if(couleurCourante == GCouleur.JAUNE) {
            textViewJoueur.setBackgroundColor(grille.getResources().getColor(R.color.JAUNE, null));
            setTexteJoueur(GConstantes.JOUEUR_2);
        }
    }

    private static  void setTexteJoueur(int joueur) {

        if(grille.getResources().getBoolean(R.bool.si_portrait)) {

            if(joueur == GConstantes.JOUEUR_1) {
                textViewJoueur.setText(grille.getResources().getString(R.string.joueur1));

            } else if (joueur == GConstantes.JOUEUR_2) {
                textViewJoueur.setText(grille.getResources().getString(R.string.joueur2));
            }

        } else {
            if(joueur == GConstantes.JOUEUR_1) {
                textViewJoueur.setText(grille.getResources().getString(R.string.joueur1Land));

            } else if (joueur == GConstantes.JOUEUR_2){
                textViewJoueur.setText(grille.getResources().getString(R.string.joueur2Land));
            }
        }
    }
}
