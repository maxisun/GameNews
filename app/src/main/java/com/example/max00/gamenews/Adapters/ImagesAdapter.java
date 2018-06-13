package com.example.max00.gamenews.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.max00.gamenews.R;
import com.example.max00.gamenews.RoomArchitecture.Entity.NewsEntity;
import com.example.max00.gamenews.RoomArchitecture.Entity.PlayersEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImagesViewHolder> {

    private List<NewsEntity> list;
    private Context context;
    private LayoutInflater layoutInflater;

    public static class ImagesViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView imageView;

        public ImagesViewHolder(View view){
            super(view);
            cardView = view.findViewById(R.id.pure_images_cardview_id);
            imageView = view.findViewById(R.id.imagen_pure_image_cardview);

        }
    }

    public ImagesAdapter(List<NewsEntity> images,Context context){
        this.list = images;
        this.context = context;
    }

    @NonNull
    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.pure_images_cardview,parent,false);
        ImagesViewHolder imagesViewHolder = new ImagesViewHolder(view);
        return imagesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesViewHolder holder, final int position) {
        final NewsEntity newsEntity = list.get(position);

        if (!(newsEntity.getCoverImage() == null)) {
            Picasso.with(context).load(newsEntity.getCoverImage()).error(R.drawable.chino).into(holder.imageView);
        } else {
            Picasso.with(context).load(R.drawable.chino).error(R.drawable.chino).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }else {
            return 0;
        }
    }
}
