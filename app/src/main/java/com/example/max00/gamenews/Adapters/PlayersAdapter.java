package com.example.max00.gamenews.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.max00.gamenews.Activities.ViewNewsActivity;
import com.example.max00.gamenews.Activities.ViewPlayersActivity;
import com.example.max00.gamenews.R;
import com.example.max00.gamenews.RoomArchitecture.Entity.PlayersEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder> {
    public List<PlayersEntity> list;
    private Context context;
    private LayoutInflater layoutInflater;

    public static class PlayersViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView name,bio;
        ImageView imagen;

        public PlayersViewHolder(View view){
            super(view);
            cardView = view.findViewById(R.id.card_view_players);
            name = view.findViewById(R.id.Players_name_Cardview);
            bio = view.findViewById(R.id.Players_Bio_Cardview);
            imagen = view.findViewById(R.id.imagen_Players_Cardview);
        }
    }

    public PlayersAdapter(List<PlayersEntity> players,Context context){
        this.list = players;
        this.context = context;
    }

    @NonNull
    @Override
    public PlayersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.card_view_players,parent,false);
        PlayersViewHolder playersViewHolder = new PlayersViewHolder(view);
        return playersViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlayersViewHolder holder, final int position) {
        final PlayersEntity playersEntity = list.get(position);

        holder.name.setText(playersEntity.getName());
        holder.bio.setText(playersEntity.getBiografia());

        if (!(playersEntity.getAvatar() == null)) {
            Picasso.with(context).load(playersEntity.getAvatar()).error(R.drawable.chino).into(holder.imagen);
        } else {
            Picasso.with(context).load(R.drawable.chino).error(R.drawable.chino).into(holder.imagen);
        }

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity(v,playersEntity);
            }
        });

        holder.bio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity(v,playersEntity);
            }
        });

        holder.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity(v,playersEntity);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }else {
            return 0;
        }
    }

    private void openactivity(View v, PlayersEntity playersEntity){
        Intent intent = new Intent(v.getContext(), ViewPlayersActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("PlayersInformation",playersEntity);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
