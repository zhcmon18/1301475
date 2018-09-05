package ca.cours5b5.pavelzaharciuc.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;

import ca.cours5b5.pavelzaharciuc.R;

public class AParametres extends Activite {

    static { Log.d("Atelier04", AParametres.class.getSimpleName() + "::static"); }

    private int valeurHauteur;
    private int valeurLargeur;
    private int valeurPourGagner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_parametres);

        messageBonjour();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Spinner spinnerHauteur = this.findViewById(R.id.spinner_height);
        Spinner spinnerLargeur = this.findViewById(R.id.spinner_width);
        Spinner spinnerPourGagner = this.findViewById(R.id.spinner_toWin);

        valeurHauteur = spinnerHauteur.getSelectedItemPosition();
        valeurLargeur = spinnerLargeur.getSelectedItemPosition();
        valeurPourGagner = spinnerPourGagner.getSelectedItemPosition();
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("valeurHauteur", valeurHauteur);
        outState.putInt("valeurLargeur", valeurLargeur);
        outState.putInt("valeurPourGagner", valeurPourGagner);
    }


    private void messageBonjour() {
        Log.d("MonEtiquette", this.getResources().getString(R.string.message));

        if(getResources().getBoolean(R.bool.paysage))
        {
            Log.d("MonEtiquette", this.getResources().getString(R.string.message) + " " +
                    this.getResources().getString(R.string.paysage));
        }else {
            Log.d("MonEtiquette", this.getResources().getString(R.string.message) + " " +
                    this.getResources().getString(R.string.portrait));
        }
    }
}

