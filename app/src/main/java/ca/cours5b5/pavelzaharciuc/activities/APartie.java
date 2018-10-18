package ca.cours5b5.pavelzaharciuc.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import java.util.Map;

import ca.cours5b5.pavelzaharciuc.R;
import ca.cours5b5.pavelzaharciuc.controleurs.ControleurObservation;
import ca.cours5b5.pavelzaharciuc.modeles.MPartie;
import ca.cours5b5.pavelzaharciuc.serialisation.Jsonification;

public class APartie  extends  Activite {

    private String cle = MPartie.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_partie);

        if(savedInstanceState != null) {
            restaurerPartie(savedInstanceState);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        sauvegarderPartie(outState);
    }

    private void restaurerPartie(Bundle savedInstanceState) {
        String json = savedInstanceState.getString(cle);

        Map<String, Object> objetJson = Jsonification.enObjetJson(json);

        ControleurObservation.partie.aPartirObjetJson(objetJson);

        Log.d("Atelier08", this.getClass().getSimpleName() + "::restaurerPartie\n" + json);

    }

    private void sauvegarderPartie(Bundle outState) {
        Map<String, Object> objetJson = ControleurObservation.partie.enObjetJson();

        String json = Jsonification.enChaine(objetJson);

        outState.putString(cle, json);

        Log.d("Atelier08", this.getClass().getSimpleName() + "::sauvegarderPartie\n" + json);
    }
}
