package ca.cours5b5.pavelzaharciuc.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import ca.cours5b5.pavelzaharciuc.R;
import ca.cours5b5.pavelzaharciuc.vues.VParametres;

public class AParametres extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);

        Log.d("MonEtiquette", this.getResources().getString(R.string.message));

        if(getResources().getBoolean(R.bool.paysage))
        {
            Log.d("MonEtiquette", this.getResources().getString(R.string.message) + " " +
                    this.getResources().getString(R.string.paysage));
        }else {
            Log.d("MonEtiquette", this.getResources().getString(R.string.message) + " " +
                    this.getResources().getString(R.string.portrait));
        }
    }
}
