package ca.cours5b5.pavelzaharciuc.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import ca.cours5b5.pavelzaharciuc.R;

import ca.cours5b5.pavelzaharciuc.controleurs.Action;
import ca.cours5b5.pavelzaharciuc.controleurs.ControleurAction;
import ca.cours5b5.pavelzaharciuc.global.GCommande;
import ca.cours5b5.pavelzaharciuc.usagers.UsagerCourant;




public class VMenuPrincipal extends Vue {

    private Button boutonParametres;
    private Action actionParametres;

    private Button boutonPartie;
    private Action actionPartie;

    private static Button boutonAuth;
    private Action actionConnexion;
    private Action actionDeconnexion;

    private static Button boutonPartieReseau;
    private Action actionPartieReseau;

    public VMenuPrincipal(Context context) {
        super(context);
    }

    public VMenuPrincipal(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VMenuPrincipal(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();

        recupererControles();

        demanderActions();

        installerListeners();

    }

    private void recupererControles() {

        boutonParametres = findViewById(R.id.bouton_parametres);

        boutonPartie = findViewById(R.id.bouton_partie);

        boutonAuth = findViewById((R.id.bouton_auth));

        boutonPartieReseau = findViewById(R.id.bouton_partieLigne);

    }

    private void demanderActions() {

        actionParametres = ControleurAction.demanderAction(GCommande.OUVRIR_MENU_PARAMETRES);

        actionPartie = ControleurAction.demanderAction(GCommande.DEMARRER_PARTIE);

        actionConnexion = ControleurAction.demanderAction(GCommande.CONNEXION);

        actionDeconnexion = ControleurAction.demanderAction(GCommande.DECONNEXION);

        actionPartieReseau = ControleurAction.demanderAction(GCommande.JOINDRE_OU_CREER_PARTIE_RESEAU);
    }


    private void installerListeners() {

        installerListenerParametres();

        installerListenerPartie();

        installerListenerAuthent();

        installerListenePartieReseau();
    }

    private void installerListenerPartie() {

        boutonPartie.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                actionPartie.executerDesQuePossible();
            }
        });

    }

    private void installerListenerParametres() {

        boutonParametres.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                actionParametres.executerDesQuePossible();
            }
        });

    }

    private void installerListenerAuthent() {
        boutonAuth.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!UsagerCourant.siUsagerConnecte()) {
                    actionConnexion.executerDesQuePossible();
                } else {
                    actionDeconnexion.executerDesQuePossible();
                }
            }
        });
    }

    private void installerListenePartieReseau() {
        boutonPartieReseau.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                actionPartieReseau.executerDesQuePossible();

            }
        });
    }

    public static void modifierTexteBouton(String texte) {
        boutonAuth.setText(texte);
    }

    public static void afficherOuCacherBoutonReseau(int visibilite) {
        boutonPartieReseau.setVisibility(visibilite);
    }

}
