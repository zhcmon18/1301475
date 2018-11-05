package ca.cours5b5.pavelzaharciuc.controleurs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.pavelzaharciuc.controleurs.interfaces.Fournisseur;
import ca.cours5b5.pavelzaharciuc.controleurs.interfaces.ListenerGetModele;
import ca.cours5b5.pavelzaharciuc.donnees.Serveur;
import ca.cours5b5.pavelzaharciuc.donnees.SourceDeDonnees;
import ca.cours5b5.pavelzaharciuc.exceptions.ErreurModele;
import ca.cours5b5.pavelzaharciuc.modeles.MParametres;
import ca.cours5b5.pavelzaharciuc.modeles.MPartie;
import ca.cours5b5.pavelzaharciuc.modeles.Modele;
import ca.cours5b5.pavelzaharciuc.donnees.Disque;
import ca.cours5b5.pavelzaharciuc.usagers.UsagerCourant;

public final class ControleurModeles {

    private ControleurModeles(){}

    private static Map<String, Modele> modelesEnMemoire;

    private static SourceDeDonnees[] sequenceDeChargement;

    private static List<SourceDeDonnees> listeDeSauvegardes;


    static {

        modelesEnMemoire = new HashMap<>();

        listeDeSauvegardes = new ArrayList<>();
        listeDeSauvegardes.add(Disque.getInstance());
        listeDeSauvegardes.add(Serveur.getInstance());

    }


    private static void creerModeleEtChargerDonnees(final String nomModele, final ListenerGetModele listenerGetModele) {
        creerModeleSelonNom(nomModele, new ListenerGetModele() {
            @Override
            public void reagirAuModele(Modele modele) {
                chargerDonnees(modele, nomModele, listenerGetModele);
                modelesEnMemoire.put(nomModele, modele);
            }
        });

    }

    private static void chargerDonnees(Modele modele, String nomModele, ListenerGetModele listenerGetModele) {

    }

    private static void chargementViaSequence(Modele modele, String cheminSauvegarde, ListenerGetModele listenerGetModele,
                                              int indiceSourceCourante) {


    }

    private static void chargementViaSourceCourranteOuSouvante(final Modele modele,
                                                               final String cheminDeSauvegarde,
                                                               final ListenerGetModele listenerGetModele,
                                                               final int indiceSourceCourante) {

    }

    static void getModele(String nomModele, ListenerGetModele listenerGetModele) {
        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele != null) {
            creerModeleEtChargerDonnees(nomModele, listenerGetModele);
        } else {
            listenerGetModele.reagirAuModele(modele);
        }
    }

    private static void creerModeleSelonNom(final String nomModele,
                                            final ListenerGetModele listenerGetModele) throws ErreurModele {


    }



    public static void setSequenceDeChargement(SourceDeDonnees... sequenceDeChargement){

        ControleurModeles.sequenceDeChargement = sequenceDeChargement;

    }

    public static void sauvegarderModeleDansCetteSource(String nomModele, SourceDeDonnees sourceDeDonnees) {

        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele != null){

            Map<String, Object> objetJson = modele.enObjetJson();

            sourceDeDonnees.sauvegarderModele(getCheminSauvegarde(nomModele), objetJson);

        }
    }

    public static void detruireSauvegarde(String nomModele) {
        String cheminSauvegarde = getCheminSauvegarde(nomModele);

        for(SourceDeDonnees source : listeDeSauvegardes ) {
            source.detruireSauvegarde(cheminSauvegarde);
        }
    }

    static Modele getModele(final String nomModele){

        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele == null){

            modele =  chargerViaSequenceDeChargement(nomModele);

        }

        return modele;
    }


    private static Modele chargerViaSequenceDeChargement(final String nomModele){

        Modele modele = creerModeleSelonNom(nomModele);

        modelesEnMemoire.put(nomModele, modele);

        for(SourceDeDonnees sourceDeDonnees : sequenceDeChargement){

            /*Map<String, Object> objetJson = sourceDeDonnees.chargerModele(nomModele);

            if(objetJson != null){

                modele.aPartirObjetJson(objetJson);
                break;

            }
            */
        }

        return modele;
    }


    public static void sauvegarderModele(String nomModele) throws ErreurModele {

        for(SourceDeDonnees source : listeDeSauvegardes){

            sauvegarderModeleDansCetteSource(nomModele, source);

        }

    }


    private static Modele creerModeleSelonNom(String nomModele) throws ErreurModele {

        if(nomModele.equals(MParametres.class.getSimpleName())){

            return new MParametres();

        }else if(nomModele.equals(MPartie.class.getSimpleName())){

            MParametres mParametres = (MParametres) getModele(MParametres.class.getSimpleName());

            return new MPartie(mParametres.getParametresPartie().cloner());

        }else{

            throw new ErreurModele("Modèle inconnu: " + nomModele);

        }
    }

    public static void detruireModele(String nomModele) {

        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele != null){

            modelesEnMemoire.remove(nomModele);

            ControleurObservation.detruireObservation(modele);

            if(modele instanceof Fournisseur){

                ControleurAction.oublierFournisseur((Fournisseur) modele);

            }
        }
    }

    public static String getCheminSauvegarde(String nomModele) {
        return nomModele + "/" + UsagerCourant.getId();
    }
}
