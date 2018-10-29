package ca.cours5b5.pavelzaharciuc.activites;

import android.os.Bundle;
import android.util.Log;

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
        setContentView(R.layout.activity_parametres);

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
                Serveur.getInstance().detruireSauvegarde(MParametres.class.getSimpleName());
                Serveur.getInstance().detruireSauvegarde(MPartie.class.getSimpleName());
            }
        });
    }
}
