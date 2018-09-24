package ca.cours5b5.pavelzaharciuc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import ca.cours5b5.pavelzaharciuc.R;
import ca.cours5b5.pavelzaharciuc.controleurs.Action;
import ca.cours5b5.pavelzaharciuc.controleurs.ControleurAction;
import ca.cours5b5.pavelzaharciuc.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.pavelzaharciuc.global.GCommande;

public class AMenuPrincipal extends Activite {

    static { Log.d("Atelier04", AMenuPrincipal.class.getSimpleName() + "::static"); }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_principal);
        Button buttonParam = this.findViewById(R.id.button_param);


        ControleurAction.fournirAction(this, GCommande.OUVRIR_MENU_PARAMET, new ListenerFournisseur() {
            @Override
            public void executer(Object... args) {
                lancerActivite();
            }
        });
    }

    private void lancerActivite() {
        this.startActivity(new Intent(this, AParametres.class));
    }
}
