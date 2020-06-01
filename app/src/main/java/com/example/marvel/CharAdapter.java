package com.example.marvel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class CharAdapter extends RecyclerView.Adapter<CharAdapter.charViewHolder> {
    private Context context;

    private ArrayList<ListItem> charactersList;
    public static class charViewHolder extends RecyclerView.ViewHolder{
        public ImageView charImageView;
        public ImageView favImage;
        public TextView charName;
        public Bitmap bitmap;


        public charViewHolder(@NonNull View itemView) {
            super(itemView);
            charImageView = itemView.findViewById(R.id.charimage);
            favImage = itemView.findViewById(R.id.favorite);
            charName = itemView.findViewById(R.id.charnametxt);
        }
    }

    public CharAdapter(ArrayList<ListItem> charsList) {
        charactersList = charsList;
    }

    public Bitmap getBitMapFromURL(String src){
        try{
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap charImage = BitmapFactory.decodeStream(input);
            return charImage;
        }catch (Exception e){
            e.printStackTrace();
            return null;

        }
    }

    @NonNull
    @Override
    public charViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.exampleitem, parent, false);
        charViewHolder cvh = new charViewHolder(v);
        context=parent.getContext();
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull charViewHolder holder, int position) {
        ListItem currentItem = charactersList.get(position);


        Glide.with(context).load(currentItem.getCharacterImageResource()).into(holder.charImageView);
//        holder.bitmap = getBitMapFromURL(currentItem.getCharacterImageResource());
//        holder.charImageView.setImageBitmap(holder.bitmap);
        holder.favImage.setImageResource(currentItem.getFavouriteImageResource());
        holder.charName.setText(currentItem.getCharName());

    }

    @Override
    public int getItemCount() {
        return charactersList.size();
    }
}
