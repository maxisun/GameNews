package com.example.max00.gamenews.Adapters;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.max00.gamenews.Classes.News;
import com.example.max00.gamenews.R;
import com.example.max00.gamenews.RoomArchitecture.Entity.NewsEntity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{
    public List<NewsEntity> list;
    private Context context;
    private LayoutInflater layoutInflater;

    public static class NewsViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView titulo,subtitulo;
        ImageView imagen;
        CheckBox checkBox;

        public NewsViewHolder(View view){
            super(view);
            cardView = view.findViewById(R.id.card_view_news);
            titulo = view.findViewById(R.id.titulo_cardview_TV);
            subtitulo = view.findViewById(R.id.subtitulo_cardview_TV);
            imagen = view.findViewById(R.id.imagen_cardview_news);
            checkBox = view.findViewById(R.id.checkbox_cardview);
        }
    }

    public NewsAdapter(List<NewsEntity> news, Context context){
        this.list=news;
        this.context=context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.card_news_view,parent,false);
        NewsViewHolder newsViewHolder = new NewsViewHolder(view);
        return newsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder,final int position) {
        final NewsEntity news = list.get(position);

        holder.titulo.setText(news.getTitle());
        holder.subtitulo.setText(news.getDescription());
        //holder.imagen.setImageResource(R.drawable.chino);
        //holder.subtitulo.setText(news.getSubtitulo());

        if (!(news.getCoverImage() == null)) {
            Picasso.with(context).load(news.getCoverImage()).error(R.drawable.chino).into(holder.imagen);
        } else {
            Picasso.with(context).load(R.drawable.chino).error(R.drawable.chino).into(holder.imagen);
        }
        holder.checkBox.setChecked(false);
    }

    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }else {
            return 0;
        }
    }

    public void setNewList(List<NewsEntity> newsEntities){
        this.list = newsEntities;
        notifyDataSetChanged();
    }
}
