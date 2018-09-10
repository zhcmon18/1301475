package ca.cours5b5.pavelzaharciuc.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Map;

import ca.cours5b5.pavelzaharciuc.R;
import ca.cours5b5.pavelzaharciuc.modeles.MParametres;
import ca.cours5b5.pavelzaharciuc.serialisation.Jsonification;

public class AParametres extends Activite {

    static { Log.d("Atelier04", AParametres.class.getSimpleName() + "::static"); }

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

        String json = savedInstanceState.getString("MParametres");

        Map<String, Object> objetJson = Jsonification.enObjetJson(json);

        MParametres.instance.aPartirObjetJson(objetJson);
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        sauvegarderParametres(outState);

    }

    private void sauvegarderParametres(Bundle outState) {
        Map<String, Object> objetJson = MParametres.instance.enObjetJson();

        String json = Jsonification.enChaine(objetJson);

        Log.d("MParametres", json);

        outState.putString("MParametres", json);
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

