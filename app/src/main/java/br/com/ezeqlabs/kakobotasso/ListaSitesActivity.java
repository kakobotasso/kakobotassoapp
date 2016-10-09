package br.com.ezeqlabs.kakobotasso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.ezeqlabs.kakobotasso.adapters.ListaAppsAdapter;
import br.com.ezeqlabs.kakobotasso.adapters.ListaSitesAdapter;
import br.com.ezeqlabs.kakobotasso.model.App;
import br.com.ezeqlabs.kakobotasso.model.Site;

public class ListaSitesActivity extends AppCompatActivity {
    private List<Site> sitesList = new ArrayList<>();
    private ProgressBar progressBar;
    private RecyclerView listaSites;
    private FirebaseDatabase database;
    private DatabaseReference sites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_sites);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        inicializaElementos();
        trataRecyclerView();
        buscaSites();
    }

    @Override
    protected void onPause(){
        super.onPause();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }

    private void inicializaElementos(){
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.listaSites = (RecyclerView) findViewById(R.id.lista_sites);
        this.database = FirebaseDatabase.getInstance();
    }

    private void trataRecyclerView(){
        this.listaSites.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        this.listaSites.setLayoutManager(linearLayoutManager);
    }

    private void buscaSites(){
        sites = this.database.getReference("sites");
        sites.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot appSnapshot : dataSnapshot.getChildren()){
                    Site site = appSnapshot.getValue(Site.class);
                    sitesList.add(site);
                }
                ListaSitesAdapter adapater = new ListaSitesAdapter(sitesList, ListaSitesActivity.this);
                listaSites.setAdapter(adapater);

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                FirebaseCrash.report(new Exception("Erro ao carregar projetos. >>>> " + error));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
