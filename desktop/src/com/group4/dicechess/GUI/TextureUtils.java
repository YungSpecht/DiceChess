package com.group4.dicechess.GUI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.util.Random;

public class TextureUtils {

    Texture bishop;
    Texture bishopBlack;
    Texture king;
    Texture kingBlack;
    Texture knight;
    Texture knightBlack;
    Texture pawn;
    Texture pawnBlack;
    Texture queen;
    Texture queenBlack;
    Texture rook;
    Texture rookBlack;
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
    Sprite sbishop;
    Sprite sbishopBlack;
    Sprite sking;
    Sprite skingBlack;
    Sprite sknight;
    Sprite sknightBlack;
    Sprite spawn;
    Sprite spawnBlack;
    Sprite squeen;
    Sprite squeenBlack;
    Sprite srook;
    Sprite srookBlack;
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
        bishop = new Texture("TestAssets/Bishop.png");
        bishopBlack= new Texture("TestAssets/Bishop_Black.png");
        king= new Texture("TestAssets/King.png");
        kingBlack= new Texture("TestAssets/King_Black.png");
        knight= new Texture("TestAssets/Knight.png");
        knightBlack= new Texture("TestAssets/Knight_Black.png");
        pawn= new Texture("TestAssets/Pawn.png");
        pawnBlack= new Texture("TestAssets/Pawn_Black.png");
        queen= new Texture("TestAssets/Queen.png");
        queenBlack= new Texture("TestAssets/Queen_Black.png");
        rook= new Texture("TestAssets/Rook.png");
        rookBlack= new Texture("TestAssets/Rook_Black.png");
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
        sbishop = new Sprite(bishop);
        sbishopBlack = new Sprite(bishopBlack);
        sking= new Sprite(king);
        skingBlack= new Sprite(kingBlack);
        sknight = new Sprite(knight);
        sknightBlack = new Sprite(knightBlack);
        spawn = new Sprite(pawn);
        spawnBlack = new Sprite(pawnBlack);
        squeen = new Sprite(queen);
        squeenBlack = new Sprite(queenBlack);
        srook = new Sprite(rook);
        srookBlack = new Sprite(rookBlack);
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

        spawn.setSize(33, 47);
        spawn.setPosition(112, 135);

        srook.setSize( 33, 47);
        srook.setPosition(493, 80);

        srookBlack.setSize(32, 54);
        srookBlack.setPosition(493, 450);

        sknightBlack.setSize(36, 54);
        sknightBlack.setPosition(436, 447);

        sknight.setSize(32, 54);
        sknight.setPosition(437, 82);

        sbishop.setSize(32, 52);
        sbishop.setPosition(383, 80);

        sbishopBlack.setSize(32, 46);
        sbishopBlack.setPosition(383, 451);

        sking.setSize(33, 47);
        sking.setPosition(331, 80);

        skingBlack.setSize(25, 51);
        skingBlack.setPosition(334, 447);

        squeen.setSize(33, 47);
        squeen.setPosition(276, 81);

        squeenBlack.setSize(34, 53);
        squeenBlack.setPosition(276, 447);

        spawnBlack.setSize(33, 47);

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
