package com.example.max00.gamenews.Adapters;

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

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{
    public List<News> list;

    public static class NewsViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView titulo,subtitulo;
        ImageView imagen;
        CheckBox checkBox;

        public NewsViewHolder(View view){
            super(view);
            cardView = view.findViewById(R.id.card_view);
            titulo = view.findViewById(R.id.titulo_cardview_TV);
            subtitulo = view.findViewById(R.id.subtitulo_cardview_TV);
            imagen = view.findViewById(R.id.imagen_cardview_news);
            checkBox = view.findViewById(R.id.checkbox_cardview);
        }
    }

    public NewsAdapter(List<News> news){
        this.list=news;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_news_view,parent,false);
        return (new NewsViewHolder(v));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder,final int position) {
        final News news = list.get(position);

        holder.imagen.setImageResource(news.getImage());
        holder.titulo.setText(news.getTitulo());
        holder.subtitulo.setText(news.getSubtitulo());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
