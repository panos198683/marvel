package com.example.marvel;

public class ListItem {
    private int id;
    private String characterImageResource;
    private int favouriteImageResource;
    private String charName;
    private int pos;

    public ListItem(int id, String characterImageResource, String charName, int pos) {
        this.id = id;
        this.characterImageResource = characterImageResource;
        this.favouriteImageResource = 0;
        this.charName = charName;
        this.pos = pos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
