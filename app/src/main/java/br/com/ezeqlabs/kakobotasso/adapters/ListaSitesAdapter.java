package br.com.ezeqlabs.kakobotasso.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.ezeqlabs.kakobotasso.R;
import br.com.ezeqlabs.kakobotasso.model.App;
import br.com.ezeqlabs.kakobotasso.model.Site;

public class ListaSitesAdapter extends RecyclerView.Adapter<ListaSitesAdapter.SiteViewHolder>{
    private List<Site> siteList;
    private Activity listaSitesActivity;

    public ListaSitesAdapter(List<Site> siteList, Activity activity){
        this.siteList = siteList;
        this.listaSitesActivity = activity;
    }

    @Override
    public ListaSitesAdapter.SiteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_site, parent, false);
        SiteViewHolder viewHolder = new SiteViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListaSitesAdapter.SiteViewHolder holder, int position) {
        final Site site = siteList.get(position);
        holder.nomeSite.setText(site.getNome());
        holder.tecnologiaSite.setText(site.getTecnologias());
    }

    @Override
    public int getItemCount() {
        return this.siteList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class SiteViewHolder extends RecyclerView.ViewHolder{
        protected TextView nomeSite;
        protected TextView tecnologiaSite;

        public SiteViewHolder(View itemView) {
            super(itemView);

            nomeSite = (TextView) itemView.findViewById(R.id.nome_site);
            tecnologiaSite = (TextView) itemView.findViewById(R.id.tecnologia_site);
        }
    }
}
