package com.example.marvel;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CharAdapter extends RecyclerView.Adapter<CharAdapter.charViewHolder> implements Filterable {
    private Context context;
    private boolean favselected = false;
    private ArrayList<ListItem> charactersList;
    private ArrayList<ListItem> charactersListAll;
    private ArrayList<ListItem> favoriteCharacter = new ArrayList<>();
    private OnItemClickListener charListener;

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ListItem> filteredList = new ArrayList<>();
            if(constraint.toString().isEmpty()){
                if (favselected){
                    filteredList.addAll(favoriteCharacter);
                }
                else{
                    filteredList.addAll(charactersListAll);
                }

            }else
            {
                if(favselected)
                {
                    for (ListItem character: favoriteCharacter ){
                        if (character.getCharName().toLowerCase().contains(constraint.toString().toLowerCase())){
                            filteredList.add(character);
                        }
                    }
                }
                else{
                    for (ListItem character: charactersListAll ){
                        if (character.getCharName().toLowerCase().contains(constraint.toString().toLowerCase())){
                            filteredList.add(character);
                        }
                    }
                }

            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            charactersList.clear();
            charactersList.addAll((Collection<? extends ListItem>) results.values);
            notifyDataSetChanged();
        }
    };

    public void updateFavorites(List<String> data) {
        favoriteCharacter.clear();

        boolean needUpdate = false;
        for (ListItem item : charactersList) {
            if (data.contains(String.valueOf(item.getId()))) {
                item.setFavouriteImageResource(R.drawable.favouriteicon);
                needUpdate = true;
            }else {
                item.setFavouriteImageResource(0);
            }
        }

        for (ListItem item : charactersListAll) {
            if (data.contains(String.valueOf(item.getId()))) {
                item.setFavouriteImageResource(R.drawable.favouriteicon);
                favoriteCharacter.add(item);
            }else {
                item.setFavouriteImageResource(0);
            }
        }

        if (needUpdate) {
            notifyDataSetChanged();
        }
    }

    public void showOnlyFavorite(boolean action) {
        if (action) {
            favselected = true;
            charactersList.clear();
            charactersList.addAll(favoriteCharacter);
        } else {
            favselected = false;
            charactersList.clear();
            charactersList.addAll(charactersListAll);
        }

        notifyDataSetChanged();
    }


    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public static class charViewHolder extends RecyclerView.ViewHolder{
        public ImageView charImageView;
        public ImageView favImage;
        public TextView charName;
        public Bitmap bitmap;



        public charViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            charImageView = itemView.findViewById(R.id.charimage);
            favImage = itemView.findViewById(R.id.favorite);
            charName = itemView.findViewById(R.id.charnametxt);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if(listener !=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public CharAdapter(ArrayList<ListItem> charsList) {
        charactersList = charsList;
        this.charactersListAll= new ArrayList<>(charsList);
    }


    @NonNull
    @Override
    public charViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.exampleitem, parent, false);
        charViewHolder cvh = new charViewHolder(v, charListener);
        context=parent.getContext();
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull charViewHolder holder, int position) {
        ListItem currentItem = charactersList.get(position);


        Glide.with(context).load(currentItem.getCharacterImageResource()).into(holder.charImageView);
        holder.favImage.setImageResource(currentItem.getFavouriteImageResource());
        holder.charName.setText(currentItem.getCharName());

    }

    @Override
    public int getItemCount() {
        return charactersList.size();
    }


    public void setOnItemClickListener(OnItemClickListener listener){
        charListener = listener;
    }
}
