package com.group4.dicechess.GUI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.Random;

public class TextureUtils {

    int currentSide;
    Texture dice1;
    Texture dice2;
    Texture dice3;
    Texture dice4;
    Texture dice5;
    Texture dice6;
    Sprite spriteDice1;
    Sprite spriteDice2;
    Sprite spriteDice3;
    Sprite spriteDice4;
    Sprite spriteDice5;
    Sprite spriteDice6;
    Texture backArrow;
    Sprite spriteArrow;

    public TextureUtils(){
        this.currentSide = 1;
        dice1 = new Texture("1.jpg");
        dice2 = new Texture("2.jpg");
        dice3 = new Texture("3.jpg");
        dice4 = new Texture("4.jpg");
        dice5 = new Texture("5.jpg");
        dice6 = new Texture("6.jpg");
        backArrow = new Texture("backArrow.png");
        spriteDice1 = new Sprite(dice1);
        spriteDice2 = new Sprite(dice2);
        spriteDice3 = new Sprite(dice3);
        spriteDice4 = new Sprite(dice4);
        spriteDice5 = new Sprite(dice5);
        spriteDice6 = new Sprite(dice6);
        spriteArrow = new Sprite(backArrow);
        spriteArrow.setPosition(10, 550);
        spriteArrow.setSize(50, 50);
        spriteDice1.setPosition(710, 450);
        spriteDice1.setSize(80, 80);
        spriteDice2.setPosition(710, 450);
        spriteDice2.setSize(80, 80);
        spriteDice3.setPosition(710, 450);
        spriteDice3.setSize(80, 80);
        spriteDice4.setPosition(710, 450);
        spriteDice4.setSize(80, 80);
        spriteDice5.setPosition(710, 450);
        spriteDice5.setSize(80, 80);
        spriteDice6.setPosition(710, 450);
        spriteDice6.setSize(80, 80);
    }

    public void Roll() {
        int max = 6;
        Random random = new Random();
        currentSide = (int) random.nextInt(max) + 1;
    }
}
