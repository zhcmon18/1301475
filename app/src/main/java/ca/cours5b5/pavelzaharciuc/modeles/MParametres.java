package ca.cours5b5.pavelzaharciuc.modeles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.pavelzaharciuc.controleurs.ControleurAction;
import ca.cours5b5.pavelzaharciuc.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.pavelzaharciuc.global.GCommande;
import ca.cours5b5.pavelzaharciuc.global.GConstantes;
import ca.cours5b5.pavelzaharciuc.serialisation.AttributSerialisable;

import static java.lang.StrictMath.max;

public class MParametres extends Modele {

    public static MParametres instance = new MParametres();

    private List<Integer> choixHauteur = new ArrayList<>();
    private List<Integer> choixLargeur = new ArrayList<>();
    private List<Integer> choixPourGagner = new ArrayList<>();

    @AttributSerialisable
    public Integer hauteur;
    private final String __hauteur = "hauteur";
    @AttributSerialisable
    public Integer largeur;
    private final String __largeur = "largeur";
    @AttributSerialisable
    public Integer pourGagner;
    private final String __pourGagner = "pourGagner";


    public MParametres() {
        setHauteur(GConstantes.HAUTEUR_DEFAUT);
        setLargeur(GConstantes.LARGEUR_DEFAUT);
        setPourGagner(GConstantes.POUR_GAGNER_DEFAUT);

        genererListesDeChoix();

        ControleurAction.fournirAction(this, GCommande.CHOISIR_HAUTEUR, new ListenerFournisseur() {
            @Override
            public void executer(Object... args) {
                setHauteur((int) args[0]);
                choixPourGagner = genererListeChoix(GConstantes.POUR_GAGNER_MIN, max(hauteur, largeur) * 75 / 100);
            }
        });

        ControleurAction.fournirAction(this, GCommande.CHOISIR_LARGEUR, new ListenerFournisseur() {
            @Override
            public void executer(Object... args) {
                setLargeur((int) args[0]);
                choixPourGagner = genererListeChoix(GConstantes.POUR_GAGNER_MIN, max(hauteur, largeur) * 75 / 100);
            }
        });

        ControleurAction.fournirAction(this, GCommande.CHOISIR_POUR_GAGNER, new ListenerFournisseur() {
            @Override
            public void executer(Object... args) {
                setPourGagner((int) args[0]);
            }
        });
    }

    private void genererListesDeChoix() {
        genererListeChoixHauteur();
        genererListeChoixLargeur();
        genererListeChoixPourGagner();
    }

    private void genererListeChoixHauteur() {
        choixHauteur = genererListeChoix(GConstantes.HAUTER_MIN, GConstantes.HAUTEUR_MAX);
    }

    private void genererListeChoixLargeur() {
        choixLargeur = genererListeChoix(GConstantes.LARGEUR_MIN, GConstantes.LARGEUR_MAX);
    }

    private void genererListeChoixPourGagner() {
        choixPourGagner = genererListeChoix(GConstantes.POUR_GAGNER_MIN, max(hauteur, largeur) * 75 / 100);
    }

    private List<Integer> genererListeChoix(int min, int max) {
        List<Integer> listeChoix = new ArrayList();

        for(int i = min; i <= max; ++i) {
            listeChoix.add(new Integer(i));
        }
        return listeChoix;
    }

    public List<Integer> getChoixHauteur() {
        return choixHauteur;
    }

    public List<Integer> getChoixLargeur() {
        return choixLargeur;
    }

    public List<Integer> getChoixPourGagner() {
        return choixPourGagner;
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

    public Integer getHauteur() {
        return hauteur;
    }

    public Integer getLargeur() {
        return largeur;
    }

    public Integer getPourGagner() {
        return pourGagner;
    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) {
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
    public Map<String, Object> enObjetJson() {
        Map<String, Object> objetJson = new HashMap<>();

        objetJson.put(__hauteur, getHauteur().toString());
        objetJson.put(__largeur, getLargeur().toString());
        objetJson.put(__pourGagner, getPourGagner().toString());

        return objetJson;
    }
}
