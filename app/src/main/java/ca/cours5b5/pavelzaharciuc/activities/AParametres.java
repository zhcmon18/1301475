package ca.cours5b5.pavelzaharciuc.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Map;

import ca.cours5b5.pavelzaharciuc.R;
import ca.cours5b5.pavelzaharciuc.controleurs.Action;
import ca.cours5b5.pavelzaharciuc.controleurs.ControleurAction;
import ca.cours5b5.pavelzaharciuc.controleurs.ControleurObservation;
import ca.cours5b5.pavelzaharciuc.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.pavelzaharciuc.global.GCommande;
import ca.cours5b5.pavelzaharciuc.modeles.MParametres;
import ca.cours5b5.pavelzaharciuc.serialisation.Jsonification;

public class AParametres extends Activite {

    static { Log.d("Atelier04", AParametres.class.getSimpleName() + "::static"); }

    private String cle = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null) {
            restaurerParametres(savedInstanceState);
        }

        setContentView(R.layout.activity_parametres);

        messageBonjour();
    }

    private void restaurerParametres(Bundle savedInstanceState) {

        String json = savedInstanceState.getString(cle);

        Map<String, Object> objetJson = Jsonification.enObjetJson(json);

        MParametres.instance.aPartirObjetJson(objetJson);

        Log.d("MParametres::" + this.getResources().getString(R.string.restore), json);
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        sauvegarderParametres(outState);

    }

    private void sauvegarderParametres(Bundle outState) {
        Map<String, Object> objetJson = MParametres.instance.enObjetJson();

        String json = Jsonification.enChaine(objetJson);

        outState.putString(cle, json);

        Log.d("MParametres::" + this.getResources().getString(R.string.save), json);
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

