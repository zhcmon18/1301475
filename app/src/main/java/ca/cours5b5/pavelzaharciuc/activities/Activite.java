package ca.cours5b5.pavelzaharciuc.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public abstract class Activite extends AppCompatActivity {

    static { Log.d("Atelier04", Activite.class.getSimpleName() + "::static"); }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        logMessage("onCreate");
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        logMessage("onResume");
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        logMessage("onPause");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        logMessage("onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);

        logMessage("onSaveInstanceState");
    }

    protected void logMessage(String nomMethode) {
        Log.d("Atelier04", this.getClass().getSimpleName() + "::" + nomMethode);
    }
}
