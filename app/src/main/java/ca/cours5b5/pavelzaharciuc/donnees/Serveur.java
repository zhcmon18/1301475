package ca.cours5b5.pavelzaharciuc.donnees;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

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

    /*
    @Override
    public Map<String, Object> chargerModele(String cheminSauvegarde, final ListenerServeur listenerServeur) {
        DatabaseReference noeud =
                FirebaseDatabase.getInstance().getReference(ControleurModeles.getCheminSauvegarde(cheminSauvegarde));

        noeud.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    Map<String, Object> objetJson = dataSnapshot.getValue(Map.class);


                    listenerServeur.reagirCharge(objetJson);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return null;
    }
    */

    @Override
    public Map<String, Object> chargerModele(String cheminSauvegarde) {
        return null;
    }

    @Override
    public void detruireSauvegarde(String cheminSauvegarde) {
        DatabaseReference noeud = FirebaseDatabase.getInstance().getReference(cheminSauvegarde);
        noeud.removeValue();
    }
}
