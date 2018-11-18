package ca.cours5b5.pavelzaharciuc.activites;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import ca.cours5b5.pavelzaharciuc.R;
import ca.cours5b5.pavelzaharciuc.controleurs.ControleurAction;
import ca.cours5b5.pavelzaharciuc.controleurs.ControleurModeles;
import ca.cours5b5.pavelzaharciuc.controleurs.ControleurPartieReseau;
import ca.cours5b5.pavelzaharciuc.controleurs.interfaces.Fournisseur;
import ca.cours5b5.pavelzaharciuc.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.pavelzaharciuc.global.GCommande;
import ca.cours5b5.pavelzaharciuc.modeles.MParametres;
import ca.cours5b5.pavelzaharciuc.modeles.MPartie;
import ca.cours5b5.pavelzaharciuc.modeles.MPartieReseau;
import ca.cours5b5.pavelzaharciuc.usagers.UsagerCourant;
import ca.cours5b5.pavelzaharciuc.vues.VParametres;

public class AParametres extends Activite implements Fournisseur{

    private String[] modelesADetruire = {MPartieReseau.class.getSimpleName(), MPartie.class.getSimpleName(), MParametres.class.getSimpleName()};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_parametres);

        if(!UsagerCourant.siUsagerConnecte()) {
            VParametres.afficherOuCacherBoutonDetruire(View.GONE);
        }

        fournirActions();

    }

    @Override
    protected void onPause() {
        super.onPause();

        ControleurModeles.sauvegarderModele(MParametres.class.getSimpleName());
    }

    private void fournirActions() {
        fournirActionDetruire();
    }

    private void fournirActionDetruire() {
        ControleurAction.fournirAction(this, GCommande.DETRUIRE_PARTIE, new ListenerFournisseur() {
            @Override
            public void executer(Object... args) {

                for (String modele : modelesADetruire) {
                    ControleurModeles.detruireSauvegarde(modele);

                    ControleurModeles.detruireModele(modele);
                }

                ControleurPartieReseau.getInstance().detruireSauvegardeServeur();
            }
        });
    }
}
