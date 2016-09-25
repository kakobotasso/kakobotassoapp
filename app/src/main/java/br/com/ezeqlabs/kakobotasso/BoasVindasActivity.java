package br.com.ezeqlabs.kakobotasso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.ezeqlabs.kakobotasso.adapters.ListaAppsAdapter;
import br.com.ezeqlabs.kakobotasso.model.App;

public class BoasVindasActivity extends AppCompatActivity {
    private List<App> appList = new ArrayList<>();
    private RecyclerView listaApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boas_vindas);

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

                ListaAppsAdapter adapater = new ListaAppsAdapter(appList, BoasVindasActivity.this);
                listaApps.setAdapter(adapater);

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    @Override
    protected void onPause(){
        super.onPause();
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }
}
