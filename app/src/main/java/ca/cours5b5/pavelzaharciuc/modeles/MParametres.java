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
    public MParametresPartie parametresPartie;
    private String __parametresPartie = "parametresPartie";


    public MParametres() {
        parametresPartie = new MParametresPartie();

        parametresPartie.setHauteur(GConstantes.HAUTEUR_DEFAUT);
        parametresPartie.setLargeur(GConstantes.LARGEUR_DEFAUT);
        parametresPartie.setPourGagner(GConstantes.POUR_GAGNER_DEFAUT);

        genererListesDeChoix();

        ControleurAction.fournirAction(this, GCommande.CHOISIR_HAUTEUR, new ListenerFournisseur() {
            @Override
            public void executer(Object... args) {
                parametresPartie.setHauteur((int) args[0]);
                choixPourGagner = genererListeChoix(GConstantes.POUR_GAGNER_MIN,
                        max(parametresPartie.getHauteur(), parametresPartie.getLargeur()) * 75 / 100);
            }
        });

        ControleurAction.fournirAction(this, GCommande.CHOISIR_LARGEUR, new ListenerFournisseur() {
            @Override
            public void executer(Object... args) {
                parametresPartie.setLargeur((int) args[0]);
                choixPourGagner = genererListeChoix(GConstantes.POUR_GAGNER_MIN,
                        max(parametresPartie.getHauteur(), parametresPartie.getLargeur()) * 75 / 100);
            }
        });

        ControleurAction.fournirAction(this, GCommande.CHOISIR_POUR_GAGNER, new ListenerFournisseur() {
            @Override
            public void executer(Object... args) {
                parametresPartie.setPourGagner((int) args[0]);
            }
        });
    }

    public MParametresPartie getParametresPartie() {
        return parametresPartie;
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
        choixPourGagner = genererListeChoix(GConstantes.POUR_GAGNER_MIN, max(parametresPartie.getHauteur(), parametresPartie.getLargeur()) * 75 / 100);
    }

    private List<Integer> genererListeChoix(int min, int max) {
        List<Integer> listeChoix = new ArrayList();

        for(int i = min; i <= max; ++i) {
            listeChoix.add(new Integer(i));
        }
        return listeChoix;
    }

    public static MParametres getInstance() {
        return instance;
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

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) {
        for(Map.Entry<String, Object> entry: objetJson.entrySet()) {
            String cle = entry.getKey();

            if (cle.equals(__parametresPartie)) {
                parametresPartie.aPartirObjetJson((Map<String, Object>) entry.getValue());
            }
        }
    }

    @Override
    public Map<String, Object> enObjetJson() {
        Map<String, Object> objetJson = new HashMap<>();

        objetJson.put(__parametresPartie, getParametresPartie().enObjetJson());

        return objetJson;
    }
}
