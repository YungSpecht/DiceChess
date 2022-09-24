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
    Sprite spriteNotation;
    Sprite spriteDice1;
    Sprite spriteDice2;
    Sprite spriteBoard;
    Sprite spriteDice3;
    Sprite spriteDice4;
    Sprite spriteDice5;
    Sprite spriteDice6;
    Texture backArrow;
    Sprite spriteArrow;
    int currentSide;
    int PVA_WIDTH= 200;
    int PVA_HEIGHT=50;
    int PVP_WIDTH= 200;
    int PVP_HEIGHT = 50;
    int AVA_WIDTH= 200;
    int AVA_HEIGHT = 50;
    int HELP_WIDTH = 200;
    int HELP_HEIGHT = 50;
    int SOUND_WIDTH = 200;
    int SOUND_HEIGHT = 50;
    int NOSOUND_WIDTH = 200;
    int NOSOUND_HEIGHT = 50;
    Texture PVAActive;
    Texture PVPActive;
    Texture AVAActive;
    Texture HELPActive;
    Texture SoundActive;
    Texture PVAInActive;
    Texture PVPInActive;
    Texture AVAInActive;
    Texture HELPInActive;
    Texture SoundInActive;
    Texture noSoundInActive;
    Texture NOSoundActive;

    public TextureUtils(){
        this.currentSide = 1;
        notation = new Texture("TestAssets/nt.png");
        board = new Texture("TestAssets/board.jpg");
        dice1 = new Texture("FinalAssets/1.jpg");
        dice2 = new Texture("FinalAssets/2.jpg");
        dice3 = new Texture("FinalAssets/3.jpg");
        dice4 = new Texture("FinalAssets/4.jpg");
        dice5 = new Texture("FinalAssets/5.jpg");
        dice6 = new Texture("FinalAssets/6.jpg");
        backArrow = new Texture("FinalAssets/backArrow.png");
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
        PVPActive = new Texture("TestAssets/PVP.jpg");
        PVPInActive = new Texture("TestAssets/PVP.jpg");
        PVAActive=new Texture("TestAssets/PVA.jpg");
        PVAInActive=new Texture("TestAssets/PVA.jpg");
        HELPActive = new Texture("TestAssets/HELP.jpg");
        HELPInActive = new Texture("TestAssets/HELP.jpg");
        SoundActive= new Texture("TestAssets/soundON.png");
        SoundInActive= new Texture("TestAssets/soundON.png");
        AVAActive= new Texture("TestAssets/bots.png");
        AVAInActive= new Texture("TestAssets/bots.png");
        HELPActive=new Texture("TestAssets/helpm.png");
        HELPInActive=new Texture("TestAssets/helpm.png");
        noSoundInActive=new Texture("TestAssets/soundOFF.png");
        NOSoundActive=new Texture("TestAssets/soundOFF.png");
    }

    public void Roll() {
        int max = 6;
        Random random = new Random();
        currentSide = (int) random.nextInt(max) + 1;
    }
}
