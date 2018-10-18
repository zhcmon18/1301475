package ca.cours5b5.pavelzaharciuc.modeles;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.pavelzaharciuc.controleurs.ControleurAction;
import ca.cours5b5.pavelzaharciuc.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.pavelzaharciuc.exceptions.ErreurSerialisation;
import ca.cours5b5.pavelzaharciuc.global.GCommande;
import ca.cours5b5.pavelzaharciuc.global.GCouleur;
import ca.cours5b5.pavelzaharciuc.serialisation.AttributSerialisable;

public class MPartie extends Modele {

    @AttributSerialisable
    public MParametresPartie parametres;
    private final String __parametres = "parametres";

    @AttributSerialisable
    public List<Integer> coups;
    private final String __coups = "coups";

    private  MGrille grille;
    private GCouleur couleurCourante;

    public MPartie(MParametresPartie parametres){
        this.parametres = parametres;

        grille = new MGrille(this.parametres.getLargeur());

        coups = new ArrayList<>();

        initaliserCouleurCourante();
        fournirActionPlacerJeton();
    }

    private void initaliserCouleurCourante() {
        couleurCourante = GCouleur.ROUGE;
    }

    private void fournirActionPlacerJeton() {
        ControleurAction.fournirAction(this, GCommande.JOUER_COUP_ICI, new ListenerFournisseur() {
            @Override
            public void executer(Object... args) {
                Log.d("Atelier07",  MPartie.this.getClass().getSimpleName() + "." + "executer");
                jouerCoup((int) args[0]);
            }
        });
    }

    protected void jouerCoup(int colonne) {
        coups.add(colonne);
        grille.placerJeton(colonne, couleurCourante);
        prochaineCouleurCourante();

    }

    private void prochaineCouleurCourante() {
        if(couleurCourante.equals(GCouleur.ROUGE)) {
            couleurCourante = GCouleur.JAUNE;
        } else {
            couleurCourante = GCouleur.ROUGE;
        }
    }

    public MParametresPartie getParametres(){
        return parametres;
    }

    public MGrille getGrille() {
        return grille;
    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation {
        List<String> listeCoupsJson = new ArrayList<>();

        for(Map.Entry<String, Object> entry: objetJson.entrySet()) {
            String cle = entry.getKey();

            if(cle.equals(__parametres)) {
                parametres.aPartirObjetJson((Map<String, Object>) entry.getValue());
            } else if (cle.equals(__coups)) {
                listeCoupsJson = (List<String>) entry.getValue();
            }
        }

        grille = new MGrille(parametres.getLargeur());

        initaliserCouleurCourante();

        rejouerLesCoups(listeCoupsAPartirJSon(listeCoupsJson));
    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {
        Map<String, Object> objetJson = new HashMap<>();

        objetJson.put(__parametres, parametres.enObjetJson());
        objetJson.put(__coups, listeCoupsEnObjetJson(coups));

        return objetJson;
    }

    private void rejouerLesCoups(List<Integer> coupsARejouer) {
        coups.clear();

        for(Integer coup : coupsARejouer) {
            jouerCoup(coup);
        }
    }

    private List<Integer> listeCoupsAPartirJSon(List<String> listeCoupsObjetJson) {
        final List<Integer> listeCoups = new ArrayList<>();

        for (String coupJson : listeCoupsObjetJson) {
            listeCoups.add(Integer.parseInt(coupJson));
        }
        return  listeCoups;
    }

    private List<String> listeCoupsEnObjetJson(List<Integer> listeCoups) {
        final List<String> listeCoupsJson = new ArrayList<>();

        for (Integer coup : listeCoups) {
            listeCoupsJson.add(Integer.toString(coup));
        }
        return listeCoupsJson;
    }
}
