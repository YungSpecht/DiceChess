package com.group4.dicechess.GUI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.util.Random;

public class TextureUtils {

    Texture board;
    Texture dice1;
    Texture dice2;
    Texture dice3;
    Texture dice4;
    Texture dice5;
    Texture dice6;
    Texture notation;
    Texture backArrow;
    Texture sound;
    Texture help;
    Texture bots;
    Texture highlight;
    Sprite spriteHighlight;
    Sprite spriteNotation;
    Sprite spriteDice1;
    Sprite spriteDice2;
    Sprite spriteBoard;
    Sprite spriteDice3;
    Sprite spriteDice4;
    Sprite spriteDice5;
    Sprite spriteDice6;
    Sprite spriteArrow;
    Sprite spriteHelp;
    Sprite spriteSound;
    Sprite spriteBots;
    int currentSide;

    public TextureUtils(){
        this.currentSide = 1;
        notation = new Texture("TestAssets/nt.png");
        board = new Texture("TestAssets/board.jpg");
        dice1 = new Texture("FinalAssets/1.jpg");
        dice2 = new Texture("FinalAssets/2.jpg");
        dice3 = new Texture("FinalAssets/3.jpg");
        dice4 = new Texture("FinalAssets/4.jpg");
        highlight = new Texture("TestAssets/highlight.png");
        dice5 = new Texture("FinalAssets/5.jpg");
        dice6 = new Texture("FinalAssets/6.jpg");
        backArrow = new Texture("FinalAssets/backArrow.png");
        help = new Texture("TestAssets/help.png");
        sound = new Texture("TestAssets/soundON.png");
        bots = new Texture("TestAssets/bots.png");
        spriteHighlight = new Sprite(highlight);
        spriteHelp = new Sprite(help);
        spriteSound = new Sprite(sound);
        spriteBots = new Sprite(bots);
        spriteNotation = new Sprite(notation);
        spriteDice1 = new Sprite(dice1);
        spriteDice2 = new Sprite(dice2);
        spriteDice3 = new Sprite(dice3);
        spriteDice4 = new Sprite(dice4);
        spriteDice5 = new Sprite(dice5);
        spriteDice6 = new Sprite(dice6);
        spriteArrow = new Sprite(backArrow);
        spriteBoard = new Sprite(board);
        spriteNotation.setPosition(625, 50);
        spriteNotation.setSize(250, 350);
        spriteHighlight.setSize(140, 90);
        spriteArrow.setPosition(10, 550);
        spriteArrow.setSize(50, 50);
        spriteBoard.setPosition(55, 30);
        spriteBoard.setSize(530, 520);
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
        spriteHelp.setPosition(350, 240);
        spriteHelp.setSize(200, 50);
        spriteSound.setPosition(350, 150);
        spriteSound.setSize(200, 50);
        spriteBots.setPosition(350, 330);
        spriteBots.setSize(200, 50);
    }

    public void Roll() {
        int max = 6;
        Random random = new Random();
        currentSide = random.nextInt(max) + 1;
    }
}
