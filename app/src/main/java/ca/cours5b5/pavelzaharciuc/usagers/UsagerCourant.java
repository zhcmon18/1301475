package ca.cours5b5.pavelzaharciuc.usagers;

import com.google.firebase.auth.FirebaseAuth;

public class UsagerCourant {

    public static boolean siUsagerConnecte() {
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }

    public static String getId() {
        if(siUsagerConnecte()) {
            return FirebaseAuth.getInstance().getCurrentUser().getUid();
        } else {
            return FirebaseAuth.getInstance().getUid();
        }
    }
}
