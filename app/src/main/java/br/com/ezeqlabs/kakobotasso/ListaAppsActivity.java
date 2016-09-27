package br.com.ezeqlabs.kakobotasso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.ezeqlabs.kakobotasso.adapters.ListaAppsAdapter;
import br.com.ezeqlabs.kakobotasso.model.App;

public class ListaAppsActivity extends AppCompatActivity {
    private List<App> appList = new ArrayList<>();
    private RecyclerView listaApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boas_vindas);

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        listaApps = (RecyclerView) findViewById(R.id.lista_apps);
        listaApps.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listaApps.setLayoutManager(linearLayoutManager);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("apps");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot appSnapshot : dataSnapshot.getChildren()){
                    App app = appSnapshot.getValue(App.class);
                    appList.add(app);
                }

                ListaAppsAdapter adapater = new ListaAppsAdapter(appList, ListaAppsActivity.this);
                listaApps.setAdapter(adapater);

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                FirebaseCrash.report(new Exception("Erro ao carregar projetos. >>>> " + error));
            }
        });
    }

    @Override
    protected void onPause(){
        super.onPause();
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }
}
