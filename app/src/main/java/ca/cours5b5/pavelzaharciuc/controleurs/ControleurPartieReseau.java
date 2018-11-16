package ca.cours5b5.pavelzaharciuc.controleurs;

import ca.cours5b5.pavelzaharciuc.proxy.ProxyListe;

public final class ControleurPartieReseau  {

    private static final ControleurPartieReseau instance = new ControleurPartieReseau();
    public static ControleurPartieReseau getInstance () {
        return instance;
    }

    private ProxyListe proxyEmettreCoups;
    private  ProxyListe proxyRecevoirCoups;

    private void connecterAuServeur() {

    }

    private void connecterAuServeur (String idJouerHote) {

    }

    private void connecterEnTantQueJoueurHote (String cheminCoupsJoueurHote, String cheminCoupsJoueurInvite) {

    }

    private void connecterEnTantQueJoueurInvite (String cheminCoupsJoueurHote, String cheminCoupsJoueurInvite) {

    }

    private void deconnecterDuServeur() {

    }

    public void transmettreCoup(Integer idColonne) {

    }

    private String getCheminCoupsJoueurInvite (String idJoueurHote) {
        return null;
    }

    private String getCheminCoupsJoueurHote (String idJoueurHote) {
        return null;
    }

    private String getCheminPartie (String idJoueurHote) {
        return null;
    }

    public void detruireSauvegardeServeur () {

    }
}
