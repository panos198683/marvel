package com.example.marvel;

public class ListItem {
    private String characterImageResource;
    private int favouriteImageResource;
    private String charName;
    public ListItem(String characterImageResource,int favouriteImageResource, String charName){
        this.characterImageResource = characterImageResource;
        this.favouriteImageResource = favouriteImageResource;
        this.charName = charName;
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
