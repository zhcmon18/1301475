package ca.cours5b5.pavelzaharciuc.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import ca.cours5b5.pavelzaharciuc.R;
import ca.cours5b5.pavelzaharciuc.controleurs.ControleurAction;
import ca.cours5b5.pavelzaharciuc.controleurs.interfaces.Fournisseur;
import ca.cours5b5.pavelzaharciuc.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.pavelzaharciuc.global.GCommande;
import ca.cours5b5.pavelzaharciuc.global.GConstantes;
import ca.cours5b5.pavelzaharciuc.vues.VMenuPrincipal;

public class AMenuPrincipal extends Activite implements Fournisseur {

    private static List<AuthUI.IdpConfig> fournisseurDeConnexion;
    private Button boutonAuth;

    static{
        fournisseurDeConnexion = new ArrayList<>();
        fournisseurDeConnexion.add(new  AuthUI.IdpConfig.GoogleBuilder().build());
        fournisseurDeConnexion.add(new  AuthUI.IdpConfig.EmailBuilder().build());
        fournisseurDeConnexion.add(new  AuthUI.IdpConfig.PhoneBuilder().build());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        boutonAuth = findViewById(R.id.bouton_auth);

        fournirActions();

    }

    private void fournirActions() {

        fournirActionOuvrirMenuParametres();

        fournirActionDemarrerPartie();

        fournirActionConnexion();

        fournirActionDeconnexion();
    }


    private void fournirActionConnexion() {
        ControleurAction.fournirAction(this, GCommande.CONNEXION, new ListenerFournisseur() {
            @Override
            public void executer(Object... args) {
                connexion();
            }
        });
    }

    private void fournirActionDeconnexion() {
        ControleurAction.fournirAction(this, GCommande.DECONNEXION, new ListenerFournisseur() {
            @Override
            public void executer(Object... args) {
                deconnexion();
            }
        });
    }

    private void fournirActionOuvrirMenuParametres() {

        ControleurAction.fournirAction(this,
                GCommande.OUVRIR_MENU_PARAMETRES,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {
                        transitionParametres();
                    }
                });
    }

    private void fournirActionDemarrerPartie() {

        ControleurAction.fournirAction(this,
                GCommande.DEMARRER_PARTIE,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {
                        transitionPartie();
                    }
                });
    }

    private void connexion() {
        Intent intentionConnexion = AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(fournisseurDeConnexion).build();
        startActivityForResult(intentionConnexion, GConstantes.CODE_CONNEXION_FIREBASE);
    }

    private void deconnexion() {
        AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                boutonAuth.setText(getString(R.string.login));
            }
        });
    }

    private void transitionParametres(){

        Intent intentionParametres = new Intent(this, AParametres.class);
        startActivity(intentionParametres);

    }

    private void transitionPartie(){

        Intent intentionParametres = new Intent(this, APartie.class);
        startActivity(intentionParametres);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == GConstantes.CODE_CONNEXION_FIREBASE) {
            if(resultCode == RESULT_OK) {
                boutonAuth.setText(getString(R.string.logout));
                Log.d("Atelier11", "onActivityResult: réussie");
            } else {
                Log.d("Atelier11", "onActivityResult: échouée");
            }
        }
    }
}
