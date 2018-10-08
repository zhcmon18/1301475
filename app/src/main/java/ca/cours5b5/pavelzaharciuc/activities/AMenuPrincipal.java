package ca.cours5b5.pavelzaharciuc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import ca.cours5b5.pavelzaharciuc.R;
import ca.cours5b5.pavelzaharciuc.controleurs.ControleurAction;
import ca.cours5b5.pavelzaharciuc.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.pavelzaharciuc.global.GCommande;

public class AMenuPrincipal extends Activite {

    static { Log.d("Atelier04", AMenuPrincipal.class.getSimpleName() + "::static"); }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_principal);


        ControleurAction.fournirAction(this, GCommande.OUVRIR_MENU_PARAMET, new ListenerFournisseur() {
            @Override
            public void executer(Object... args) {
                lancerActiviteParam();
            }
        });

        ControleurAction.fournirAction(this, GCommande.JOUER, new ListenerFournisseur() {
            @Override
            public void executer(Object... args) {
                lancerActiviteJouer();
            }
        });
    }

    private void lancerActiviteParam() {
        this.startActivity(new Intent(this, AParametres.class));
    }

    private void lancerActiviteJouer() {
        this.startActivity(new Intent(this, APartie.class));
    }
}
