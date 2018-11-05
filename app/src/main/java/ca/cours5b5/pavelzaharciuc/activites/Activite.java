package ca.cours5b5.pavelzaharciuc.activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ca.cours5b5.pavelzaharciuc.controleurs.ControleurModeles;
import ca.cours5b5.pavelzaharciuc.donnees.Disque;
import ca.cours5b5.pavelzaharciuc.donnees.SauvegardeTemporaire;
import ca.cours5b5.pavelzaharciuc.donnees.Serveur;
import ca.cours5b5.pavelzaharciuc.modeles.MParametres;


public abstract class Activite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initialiserControleurModeles(savedInstanceState);
        initialiserApplication();

    }

    protected void initialiserControleurModeles(Bundle savedInstanceState) {

        ControleurModeles.setSequenceDeChargement(
                new SauvegardeTemporaire(savedInstanceState),
                Disque.getInstance(),
                Serveur.getInstance());
        
    }

    protected void initialiserApplication(){

        Disque.getInstance().setRepertoireRacine(getFilesDir());

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        ControleurModeles.sauvegarderModeleDansCetteSource(MParametres.class.getSimpleName(),
                new SauvegardeTemporaire(outState));
    }

}
