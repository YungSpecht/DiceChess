package com.group4.dicechess;

public class Player {
    private boolean isWhite;
    private boolean isHuman;

    public Player(boolean isWhite, boolean isHuman) {
        this.isWhite = isWhite;
        this.isHuman = isHuman;
    }

    public boolean getColour() {
        return isWhite;
    }

    public boolean getType() {
        return isHuman;
    } 

}
