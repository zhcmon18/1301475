package ca.cours5b5.pavelzaharciuc.modeles;

import java.util.Map;

import ca.cours5b5.pavelzaharciuc.controleurs.ControleurAction;
import ca.cours5b5.pavelzaharciuc.controleurs.ControleurPartieReseau;
import ca.cours5b5.pavelzaharciuc.controleurs.interfaces.Fournisseur;
import ca.cours5b5.pavelzaharciuc.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.pavelzaharciuc.exceptions.ErreurAction;
import ca.cours5b5.pavelzaharciuc.exceptions.ErreurSerialisation;
import ca.cours5b5.pavelzaharciuc.global.GCommande;
import ca.cours5b5.pavelzaharciuc.serialisation.AttributSerialisable;

public class MPartieReseau extends MPartie implements Fournisseur, Identifiable {

    @AttributSerialisable
    public String idJoueurInvite;
    private String __idJoueurInvite = "idJoueurInvite";

    @AttributSerialisable
    public String idJoueurHote;
    private String __idJoueurHote =  "idJoueurHote";

    public MPartieReseau(MParametresPartie parametres) {
        super(parametres);
        fournirActionRecevoirCoup();
    }

    @Override
    public String getId() {
        return idJoueurHote;
    }

    private void fournirActionRecevoirCoup() {
        ControleurAction.fournirAction(this, GCommande.RECEVOIR_COUP_RESEAU, new ListenerFournisseur() {
            @Override
            public void executer(Object... args) {
                try{

                    recevoirCoupReseau(Integer.parseInt((String)args[0]));

                } catch (ClassCastException ex) {
                    throw  new ErreurAction(ex);
                }
            }
        });
    }

    @Override
    protected void fournirActionPlacerJeton() {
        ControleurAction.fournirAction(this, GCommande.JOUER_COUP_ICI, new ListenerFournisseur() {
            @Override
            public void executer(Object... args) {
                try{

                    int colonne = (Integer) args[0];

                    jouerCoup(colonne);

                    ControleurPartieReseau.getInstance().transmettreCoup(colonne);

                } catch (ClassCastException ex) {
                    throw  new ErreurAction(ex);
                }
            }
        });
    }

    private void recevoirCoupReseau(int colonne) {
        jouerCoup(colonne);
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
