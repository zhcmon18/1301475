package ca.cours5b5.pavelzaharciuc.activites;

import android.os.Bundle;

import ca.cours5b5.pavelzaharciuc.controleurs.ControleurModeles;
import ca.cours5b5.pavelzaharciuc.controleurs.interfaces.Fournisseur;
import ca.cours5b5.pavelzaharciuc.modeles.MPartieReseau;

public class APartieReseau extends Activite implements Fournisseur {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ControleurModeles.detruireModele(MPartieReseau.class.getSimpleName());
    }
}
