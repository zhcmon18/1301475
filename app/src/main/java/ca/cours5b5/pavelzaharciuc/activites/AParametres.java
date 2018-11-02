package ca.cours5b5.pavelzaharciuc.activites;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import ca.cours5b5.pavelzaharciuc.R;
import ca.cours5b5.pavelzaharciuc.controleurs.ControleurAction;
import ca.cours5b5.pavelzaharciuc.controleurs.ControleurModeles;
import ca.cours5b5.pavelzaharciuc.controleurs.interfaces.Fournisseur;
import ca.cours5b5.pavelzaharciuc.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.pavelzaharciuc.donnees.Serveur;
import ca.cours5b5.pavelzaharciuc.global.GCommande;
import ca.cours5b5.pavelzaharciuc.modeles.MParametres;
import ca.cours5b5.pavelzaharciuc.modeles.MPartie;

public class AParametres extends Activite implements Fournisseur{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        creerVue();

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
                ControleurModeles.detruireSauvegarde(MParametres.class.getSimpleName());
                ControleurModeles.detruireSauvegarde(MPartie.class.getSimpleName());

                ControleurModeles.detruireModele(MParametres.class.getSimpleName());
                ControleurModeles.detruireModele(MPartie.class.getSimpleName());

                creerVue();
            }
        });
    }

    private void creerVue () {
        setContentView(R.layout.activity_parametres);
    }
}
