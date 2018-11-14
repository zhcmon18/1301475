package ca.cours5b5.pavelzaharciuc.proxy;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public abstract class Proxy {

    private String cheminServeur;

    protected DatabaseReference noeudServeur;

    public Proxy(String cheminServeur) {
        this.cheminServeur = cheminServeur;
    }

    public void connecterAuServeur() {
        noeudServeur = FirebaseDatabase.getInstance().getReference();
    }

    public void deconnecterDuServeur() {
        noeudServeur = null;
    }

    public abstract void detruireValeurs();

}
