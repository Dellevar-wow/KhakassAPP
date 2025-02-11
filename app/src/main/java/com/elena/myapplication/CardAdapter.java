package com.elena.myapplication;


import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.AnimalViewHolder> {
    private Context context;
    private List<AnimalItem> animalList;

    public CardAdapter(Context context, List<AnimalItem> animalList) {
        this.context = context;
        this.animalList = animalList;
    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
        return new AnimalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position) {
        AnimalItem animal = animalList.get(position);
        holder.nameTextView.setText(animal.getName());

        String imageUrl = animal.getImageUrls().get(0).trim();
        imageUrl = imageUrl.replaceAll("^\"|\"$", ""); // Удаляет кавычки в начале и конце
        Log.d("DEBUG", "Image URL: [" + imageUrl + "]");

        // Загружаем изображение
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.placeholder)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

    public static class AnimalViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ImageView imageView;

        public AnimalViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.card_title);
            imageView = itemView.findViewById(R.id.card_image);
        }
    }
}
