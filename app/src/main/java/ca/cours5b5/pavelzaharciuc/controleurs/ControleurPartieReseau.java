package ca.cours5b5.pavelzaharciuc.controleurs;

import ca.cours5b5.pavelzaharciuc.controleurs.interfaces.ListenerGetModele;
import ca.cours5b5.pavelzaharciuc.donnees.Serveur;
import ca.cours5b5.pavelzaharciuc.global.GCommande;
import ca.cours5b5.pavelzaharciuc.global.GConstantes;
import ca.cours5b5.pavelzaharciuc.modeles.MPartieReseau;
import ca.cours5b5.pavelzaharciuc.modeles.Modele;
import ca.cours5b5.pavelzaharciuc.proxy.ProxyListe;
import ca.cours5b5.pavelzaharciuc.usagers.UsagerCourant;

public final class ControleurPartieReseau  {


    private static final ControleurPartieReseau instance = new ControleurPartieReseau();
    public static ControleurPartieReseau getInstance () {
        return instance;
    }

    private ProxyListe proxyEmettreCoups;
    private  ProxyListe proxyRecevoirCoups;
    private String idJoueurHote;

    public void connecterAuServeur() {
        ControleurModeles.getModele(MPartieReseau.class.getSimpleName(), new ListenerGetModele() {
            @Override
            public void reagirAuModele(Modele modele) {
                MPartieReseau mPartieReseau = (MPartieReseau) modele;

                idJoueurHote = mPartieReseau.getId();

                connecterAuServeur(idJoueurHote);
            }
        });
    }

    private void connecterAuServeur (String idJouerHote) {
        String cheminHote = getCheminCoupsJoueurHote(idJouerHote);
        String cheminInvite = getCheminCoupsJoueurInvite(idJouerHote);

        if(UsagerCourant.getId().equals(idJouerHote)) {
            connecterEnTantQueJoueurHote(cheminHote, cheminInvite);
        } else {
            connecterEnTantQueJoueurInvite(cheminHote, cheminInvite);
        }

        proxyRecevoirCoups.setActionNouvelItem(GCommande.RECEVOIR_COUP_RESEAU);
        proxyRecevoirCoups.connecterAuServeur();
        proxyEmettreCoups.connecterAuServeur();
    }

    private void connecterEnTantQueJoueurHote (String cheminCoupsJoueurHote, String cheminCoupsJoueurInvite) {
        proxyEmettreCoups = new ProxyListe(cheminCoupsJoueurHote);
        proxyRecevoirCoups = new ProxyListe(cheminCoupsJoueurInvite);
    }

    private void connecterEnTantQueJoueurInvite (String cheminCoupsJoueurHote, String cheminCoupsJoueurInvite) {
        proxyEmettreCoups = new ProxyListe(cheminCoupsJoueurInvite);
        proxyRecevoirCoups = new ProxyListe(cheminCoupsJoueurHote);
    }

    public void deconnecterDuServeur() {
        proxyEmettreCoups.deconnecterDuServeur();
        proxyRecevoirCoups.deconnecterDuServeur();
    }

    public void transmettreCoup(Integer idColonne) {
        proxyEmettreCoups.ajouterValeur(idColonne);
    }

    private String getCheminCoupsJoueurInvite (String idJoueurHote) {
        return getCheminPartie(idJoueurHote) + "/" + GConstantes.CLE_COUPS_JOUEUR_INVITE;
    }

    private String getCheminCoupsJoueurHote (String idJoueurHote) {
        return getCheminPartie(idJoueurHote) + "/" + GConstantes.CLE_COUPS_JOUEUR_HOTE;
    }

    private String getCheminPartie (String idJoueurHote) {
        return MPartieReseau.class.getSimpleName() + "/" + idJoueurHote;
    }

    public void detruireSauvegardeServeur () {
        Serveur.getInstance().detruireSauvegarde(getCheminCoupsJoueurHote(idJoueurHote));
        Serveur.getInstance().detruireSauvegarde((getCheminCoupsJoueurInvite(idJoueurHote)));
    }
}
