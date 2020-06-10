package com.example.marvel;

import androidx.lifecycle.ViewModel;

public class ListItem {
    private String characterImageResource;
    private int favouriteImageResource;
    private String charName;
    private int pos;
    public ListItem(String characterImageResource,int favouriteImageResource, String charName, int pos){
        this.characterImageResource = characterImageResource;
        this.favouriteImageResource = favouriteImageResource;
        this.charName = charName;
        this.pos = pos;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getCharacterImageResource() {
        return characterImageResource;
    }

    public void setCharacterImageResource(String characterImageResource) {
        this.characterImageResource = characterImageResource;
    }

    public int getFavouriteImageResource() {
        return favouriteImageResource;
    }

    public void setFavouriteImageResource(int favouriteImageResource) {
        this.favouriteImageResource = favouriteImageResource;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }
}
