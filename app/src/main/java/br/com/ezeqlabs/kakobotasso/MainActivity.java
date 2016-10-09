package br.com.ezeqlabs.kakobotasso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnApps = (Button) findViewById(R.id.btn_apps);
        btnApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listaApps = new Intent(MainActivity.this, ListaAppsActivity.class);
                startActivity(listaApps);
            }
        });

        Button btnSites = (Button) findViewById(R.id.btn_sites);
        btnSites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listaSites = new Intent(MainActivity.this, ListaSitesActivity.class);
                startActivity(listaSites);
            }
        });
    }

    @Override
    protected void onPause(){
        super.onPause();
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }
}
