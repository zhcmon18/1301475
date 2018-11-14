package ca.cours5b5.pavelzaharciuc.modeles;

import java.util.Map;

import ca.cours5b5.pavelzaharciuc.controleurs.interfaces.Fournisseur;
import ca.cours5b5.pavelzaharciuc.exceptions.ErreurSerialisation;
import ca.cours5b5.pavelzaharciuc.serialisation.AttributSerialisable;

public class MPartieReseau extends MPartie implements Fournisseur, Identifiable {

    @AttributSerialisable
    public String idJoueurInvite;
    private String __idJoueurInvite;

    @AttributSerialisable
    public String idJoueurHote;
    private String __idJoueurHote;

    public MPartieReseau(MParametresPartie parametres) {
        super(parametres);
    }

    @Override
    public String getId() {
        return idJoueurHote;
    }

    /*TODO*/
    private void fournirActionRecevoirCoup() {

    }

    /*TODO*/
    @Override
    protected void fournirActionPlacerJeton() {

    }

    /*TODO*/
    private void recevoirCoupReseau(int colonne) {

    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation {
        super.aPartirObjetJson(objetJson);
        idJoueurHote = (String) objetJson.get(__idJoueurHote);
        idJoueurInvite = (String) objetJson.get(__idJoueurInvite);
    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {
        Map<String, Object> objetJson = super.enObjetJson();
        objetJson.put(__idJoueurHote, idJoueurHote);
        objetJson.put(__idJoueurInvite, idJoueurInvite);

        return objetJson;
    }
}
