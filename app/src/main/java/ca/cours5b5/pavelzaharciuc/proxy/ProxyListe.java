package ca.cours5b5.pavelzaharciuc.proxy;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import ca.cours5b5.pavelzaharciuc.controleurs.Action;
import ca.cours5b5.pavelzaharciuc.controleurs.ControleurAction;
import ca.cours5b5.pavelzaharciuc.controleurs.interfaces.Fournisseur;
import ca.cours5b5.pavelzaharciuc.global.GCommande;
import ca.cours5b5.pavelzaharciuc.global.GConstantes;

public class ProxyListe extends Proxy implements Fournisseur {


    private ChildEventListener childEventListener;

    private Query requete;

    private Action actionNouvelItem;

    private List<DatabaseReference> noeudsAjouets;

    public ProxyListe(String cheminServeur) {
        super(cheminServeur);

        noeudsAjouets = new ArrayList<>();
    }

    public void setActionNouvelItem(GCommande commande) {
        actionNouvelItem = ControleurAction.demanderAction(commande);
    }

    public void ajouterValeur(Object valeur) {
        DatabaseReference reference = super.noeudServeur.push();
        reference.setValue(valeur);
        noeudsAjouets.add(reference);

    }

    @Override
    public void connecterAuServeur() {
        super.connecterAuServeur();
        creerListener();
        requete = getRequete();
        requete.addChildEventListener(childEventListener);

    }

    private void creerListener() {
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(actionNouvelItem != null) {
                    actionNouvelItem.setArguments(dataSnapshot.getValue().toString());
                    actionNouvelItem.executerDesQuePossible();

                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }

    protected Query getRequete() {
        return noeudServeur.orderByKey().limitToFirst(GConstantes.NOMBRE_DE_VALEURS_A_CHARGER_DU_SERVEUR_PAR_DEFAUT);
    }

    @Override
    public void deconnecterDuServeur() {
        super.deconnecterDuServeur();
        requete.removeEventListener(childEventListener);
    }

    @Override
    public void detruireValeurs() {
        for(DatabaseReference reference : noeudsAjouets) {
            reference.removeValue();
        }
    }
}
