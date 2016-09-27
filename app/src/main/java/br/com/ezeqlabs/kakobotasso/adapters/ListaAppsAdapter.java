package br.com.ezeqlabs.kakobotasso.adapters;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.crash.FirebaseCrash;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.ezeqlabs.kakobotasso.R;
import br.com.ezeqlabs.kakobotasso.model.App;

public class ListaAppsAdapter extends RecyclerView.Adapter<ListaAppsAdapter.AppViewHolder> {
    private static List<App> appList;
    private static Activity boasVindasActivity;

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
    public void onBindViewHolder(final ListaAppsAdapter.AppViewHolder holder, int position) {
        final App app = appList.get(position);
        holder.nomeApp.setText(app.getNome());

        if( app.getTecnologia().equalsIgnoreCase("Unity")  ){
            holder.tecnologiaApp.setImageResource(R.mipmap.unity_logo);
        }else if( app.getTecnologia().equalsIgnoreCase("Java")  ){
            holder.tecnologiaApp.setImageResource(R.mipmap.android_logo);
        }else if( app.getTecnologia().equalsIgnoreCase("Swift") ){
            holder.tecnologiaApp.setImageResource(R.mipmap.swift_logo);
        }

        Picasso.with(boasVindasActivity).load(app.getImagem()).into(holder.imagemApp, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBarApp.setVisibility(View.GONE);
                holder.imagemApp.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError() {
                FirebaseCrash.report(new Exception("Erro ao carregar imagem do projeto" + app.getNome()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.appList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static void vaiParaDetalhe(int posicao){
        App app = appList.get(posicao);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=" + app.getPlaystore()));
        boasVindasActivity.startActivity(intent);
    }

    public static class AppViewHolder extends RecyclerView.ViewHolder {
        protected TextView nomeApp;
        protected ImageView tecnologiaApp;
        protected ImageView imagemApp;
        protected ProgressBar progressBarApp;

        public AppViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    vaiParaDetalhe(getAdapterPosition());
                }
            });
            nomeApp = (TextView) itemView.findViewById(R.id.nome_app);
            tecnologiaApp = (ImageView) itemView.findViewById(R.id.tecnologia_app);
            imagemApp = (ImageView) itemView.findViewById(R.id.imagem_app);
            progressBarApp = (ProgressBar) itemView.findViewById(R.id.progressbar_imagem_app);
        }
    }
}
