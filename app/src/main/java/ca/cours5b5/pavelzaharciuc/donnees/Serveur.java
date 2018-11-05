package ca.cours5b5.pavelzaharciuc.donnees;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import ca.cours5b5.pavelzaharciuc.controleurs.ControleurModeles;
import ca.cours5b5.pavelzaharciuc.exceptions.ErreurModele;

public class Serveur extends SourceDeDonnees {

    private Serveur() {}

    private static final Serveur instance = new Serveur();

    public static Serveur getInstance() {
        return  instance;
    }

    @Override
    public void sauvegarderModele(String cheminSauvegarde, Map<String, Object> objetJson) {
        DatabaseReference noeud = FirebaseDatabase.getInstance().getReference(cheminSauvegarde);

        noeud.setValue(objetJson);
    }


    @Override
    public void chargerModele(final String cheminSauvegarde, final ListenerChargement listenerChargement ) {
        DatabaseReference noeud =
                FirebaseDatabase.getInstance().getReference(ControleurModeles.getCheminSauvegarde(cheminSauvegarde));

        noeud.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    Map<String, Object> objetJson = (Map<String, Object>) dataSnapshot.getValue();

                    listenerChargement.reagirSucces(objetJson);
                } else {
                    listenerChargement.reagirErreur(new ErreurModele("Il n'y a pas de données dans cette source."));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listenerChargement.reagirErreur(new ErreurModele(databaseError.getMessage()));
            }
        });
    }

    @Override
    public void detruireSauvegarde(String cheminSauvegarde) {
        DatabaseReference noeud = FirebaseDatabase.getInstance().getReference(cheminSauvegarde);
        noeud.removeValue();
    }
}
