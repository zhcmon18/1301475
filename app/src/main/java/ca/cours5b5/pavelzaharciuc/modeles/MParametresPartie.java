package ca.cours5b5.pavelzaharciuc.modeles;

import java.util.HashMap;
import java.util.Map;

import ca.cours5b5.pavelzaharciuc.exceptions.ErreurSerialisation;
import ca.cours5b5.pavelzaharciuc.global.GConstantes;
import ca.cours5b5.pavelzaharciuc.serialisation.AttributSerialisable;

public class MParametresPartie extends Modele {

    @AttributSerialisable
    public Integer hauteur;
    protected final String __hauteur = "hauteur";

    @AttributSerialisable
    public Integer largeur;
    protected final String __largeur = "largeur";

    @AttributSerialisable
    public Integer pourGagner;
    protected final String __pourGagner = "pourGagner";

    public static MParametresPartie aPartirMParametres(MParametres mParametres) {
        MParametresPartie mParametresPartie;
        mParametresPartie = mParametres.getParametresPartie().cloner();
        return  mParametresPartie;
    }

    public MParametresPartie cloner() {
        MParametresPartie mParametresPartie = new MParametresPartie();

        mParametresPartie.setHauteur(hauteur);
        mParametresPartie.setLargeur(largeur);
        mParametresPartie.setPourGagner(pourGagner);

        return mParametresPartie;
    }

    public MParametresPartie() {
        hauteur = GConstantes.HAUTEUR_DEFAUT;
        largeur = GConstantes.LARGEUR_DEFAUT;
        pourGagner = GConstantes.POUR_GAGNER_DEFAUT;
    }

    public Integer getHauteur() {
        return hauteur;
    }

    public Integer getLargeur() {
        return largeur;
    }

    public Integer getPourGagner() {
        return pourGagner;
    }

    public void setHauteur(Integer hauteur) {
        this.hauteur = hauteur;
    }

    public void setLargeur(Integer largeur) {
        this.largeur = largeur;
    }

    public void setPourGagner(Integer pourGagner) {
        this.pourGagner = pourGagner;
    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation {
        for(Map.Entry<String, Object> entry: objetJson.entrySet()) {
            String cle = entry.getKey();

            if (cle.equals(__hauteur)) {
                setHauteur(Integer.valueOf(((String)entry.getValue())));
            } else  if (cle.equals(__largeur)) {
                setLargeur(Integer.valueOf(((String)entry.getValue())));
            } else {
                setPourGagner(Integer.valueOf(((String)entry.getValue())));
            }
        }
    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {
        Map<String, Object> objetJson = new HashMap<>();

        objetJson.put(__hauteur, getHauteur().toString());
        objetJson.put(__largeur, getLargeur().toString());
        objetJson.put(__pourGagner, getPourGagner().toString());

        return objetJson;
    }
}
