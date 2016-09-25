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

public class ListaAppsAdapter extends RecyclerView.Adapter<ListaAppsAdapter.AppViewHolder> {
    private List<App> appList;
    private Activity boasVindasActivity;

    public ListaAppsAdapter(List<App> appList, Activity activity){
        this.appList = appList;
        this.boasVindasActivity = activity;
    }

    @Override
    public ListaAppsAdapter.AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_app, parent, false);
        AppViewHolder viewHolder = new AppViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListaAppsAdapter.AppViewHolder holder, int position) {
        App app = appList.get(position);
        holder.nomeApp.setText(app.getNome());
    }

    @Override
    public int getItemCount() {
        return this.appList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class AppViewHolder extends RecyclerView.ViewHolder {
        protected TextView nomeApp;

        public AppViewHolder(View itemView) {
            super(itemView);
            nomeApp = (TextView) itemView.findViewById(R.id.nome_app);
        }
    }
}
