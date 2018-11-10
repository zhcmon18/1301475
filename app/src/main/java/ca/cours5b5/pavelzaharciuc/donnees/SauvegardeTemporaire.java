package ca.cours5b5.pavelzaharciuc.donnees;

import android.os.Bundle;
import android.util.Log;

import java.util.Map;


import ca.cours5b5.pavelzaharciuc.exceptions.ErreurModele;
import ca.cours5b5.pavelzaharciuc.serialisation.Jsonification;

public class SauvegardeTemporaire extends SourceDeDonnees {

    private Bundle bundle;

    public SauvegardeTemporaire(Bundle bundle){
        this.bundle = bundle;
    }

    @Override
    public void chargerModele(final String cheminSauvegarde, final ListenerChargement listenerChargement) {

        String cle  = getCle(cheminSauvegarde);

        if(bundle != null && bundle.containsKey(cle)){

            String json = bundle.getString(cle);

            Map<String, Object> objetJson = Jsonification.aPartirChaineJson(json);

            listenerChargement.reagirSucces(objetJson);

        }else{
            listenerChargement.reagirErreur(new ErreurModele("Il n'y a pas de données dans cette source."));

        }
    }

    @Override
    public void sauvegarderModele(String cheminSauvegarde, Map<String, Object> objetJson) {
        Log.d("Atelier11", "sauvegarderModele: sauvegardeTemporaire ");
        if(bundle != null){

            String json = Jsonification.enChaineJson(objetJson);
            bundle.putString(getCle(cheminSauvegarde), json);

        }
    }

    @Override
    public void detruireSauvegarde(String cheminSauvegarde) {

    }

    private String getCle(String cheminSauvegarde) {
        return super.getNomModele(cheminSauvegarde);
    }
}