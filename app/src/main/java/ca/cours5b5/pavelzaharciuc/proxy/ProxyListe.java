package ca.cours5b5.pavelzaharciuc.proxy;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.List;

import ca.cours5b5.pavelzaharciuc.controleurs.Action;
import ca.cours5b5.pavelzaharciuc.controleurs.interfaces.Fournisseur;
import ca.cours5b5.pavelzaharciuc.global.GCommande;

public class ProxyListe extends Proxy implements Fournisseur {

    private ChildEventListener childEventListener;

    private Query requete;

    private Action actionNouvelItem;

    private List<DatabaseReference> noeudsAjouets;

    public ProxyListe(String cheminServeur) {
        super(cheminServeur);
    }

    /*TODO*/
    public void setActionNouvelItem(GCommande commande) {

    }
    /*TODO*/
    public void ajouterValeur(Object valeur) {

    }
    /*TODO*/
    @Override
    public void connecterAuServeur() {
        super.connecterAuServeur();
    }
    /*TODO*/
    private void creerListener() {

    }
    /*TODO*/
    @Override
    public void deconnecterDuServeur() {
        super.deconnecterDuServeur();
    }
    /*TODO*/
    @Override
    public void detruireValeurs() {

    }
}
