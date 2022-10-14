package com.group4.dicechess.GUI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.group4.dicechess.Representation.Board;
import com.group4.dicechess.Representation.Piece;
import com.group4.dicechess.Representation.Square;

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
    Sprite sbishop1;
    Sprite sbishopBlack1;
    Sprite sbishopBlack2;
    Sprite sbishop2;
    Sprite sking;
    Sprite skingBlack;
    Sprite sknight1;
    Sprite sknight2;
    Sprite sknightBlack1;
    Sprite sknightBlack2;
    Sprite spawn1;
    Sprite spawn2;
    Sprite spawn3;
    Sprite spawn4;
    Sprite spawn5;
    Sprite spawn6;
    Sprite spawn7;
    Sprite spawn8;
    Sprite spawnBlack1;
    Sprite spawnBlack2;
    Sprite spawnBlack3;
    Sprite spawnBlack4;
    Sprite spawnBlack5;
    Sprite spawnBlack6;
    Sprite spawnBlack7;
    Sprite spawnBlack8;
    Sprite squeen;
    Sprite squeenBlack;
    Sprite srook1;
    Sprite srook2;
    Sprite srookBlack1;
    Sprite srookBlack2;
    Sprite spriteHighlight;
    Sprite spriteNotation;
    Sprite spriteDice1;
    Sprite spriteDice2;
    Sprite spriteBoard;
    Sprite spriteDice3;

    Sprite promotedQ1;
    Sprite promotedQ2;
    Sprite promotedQ3;
    Sprite promotedQ4;
    Sprite promotedQ5;
    Sprite promotedQ6;
    Sprite promotedQ7;
    Sprite promotedQ8;
    Sprite promotedQ9;

    Sprite bpromotedQ1;
    Sprite bpromotedQ2;
    Sprite bpromotedQ3;
    Sprite bpromotedQ4;
    Sprite bpromotedQ5;
    Sprite bpromotedQ6;
    Sprite bpromotedQ7;
    Sprite bpromotedQ8;
    Sprite bpromotedQ9;

    Sprite promotedR1;
    Sprite promotedR2;
    Sprite promotedR3;
    Sprite promotedR4;
    Sprite promotedR5;
    Sprite promotedR6;
    Sprite promotedR7;
    Sprite promotedR8;
    Sprite promotedR9;
    Sprite promotedR10;


    Sprite bpromotedR1;
    Sprite bpromotedR2;
    Sprite bpromotedR3;
    Sprite bpromotedR4;
    Sprite bpromotedR5;
    Sprite bpromotedR6;
    Sprite bpromotedR7;
    Sprite bpromotedR8;
    Sprite bpromotedR9;
    Sprite bpromotedR10;

    Sprite promotedK1;
    Sprite promotedK2;
    Sprite promotedK3;
    Sprite promotedK4;
    Sprite promotedK5;
    Sprite promotedK6;
    Sprite promotedK7;
    Sprite promotedK8;
    Sprite promotedK9;
    Sprite promotedK10;


    Sprite bpromotedK1;
    Sprite bpromotedK2;
    Sprite bpromotedK3;
    Sprite bpromotedK4;
    Sprite bpromotedK5;
    Sprite bpromotedK6;
    Sprite bpromotedK7;
    Sprite bpromotedK8;
    Sprite bpromotedK9;
    Sprite bpromotedK10;

    Sprite promotedB1;
    Sprite promotedB2;
    Sprite promotedB3;
    Sprite promotedB4;
    Sprite promotedB5;
    Sprite promotedB6;
    Sprite promotedB7;
    Sprite promotedB8;
    Sprite promotedB9;
    Sprite promotedB10;

    Sprite bpromotedB1;
    Sprite bpromotedB2;
    Sprite bpromotedB3;
    Sprite bpromotedB4;
    Sprite bpromotedB5;
    Sprite bpromotedB6;
    Sprite bpromotedB7;
    Sprite bpromotedB8;
    Sprite bpromotedB9;
    Sprite bpromotedB10;

    Array<Sprite> promotedQueens = new Array<>();
    Array<Sprite> bpromotedQueens = new Array<>();

    int knightCounter = 1;
    int bknightCounter = 1;
    int queenCounter = 1;
    int bqueenCounter = 1;
    int bishopCounter = 1;
    int bbishopCounter = 1;
    int rookCounter = 1;
    int brookCounter = 1;

    Array<Sprite> promotedKnight = new Array<>();
    Array<Sprite> bpromotedKnight = new Array<>();

    Array<Sprite> promotedBishops = new Array<>();
    Array<Sprite> bpromotedBishops = new Array<>();

    Array<Sprite> promotedRook = new Array<>();
    Array<Sprite> bpromotedRook = new Array<>();


    Sprite spriteDice4;
    Sprite spriteDice5;
    Sprite spriteDice6;
    Sprite spriteArrow;
    Sprite spriteHelp;
    Sprite spriteSound;
    Sprite spriteBots;
    Sprite [][] pieceStorage;

    int currentSide;

    public TextureUtils(){
        this.currentSide = 1;
        pieceStorage = new Sprite[8][8];
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
        sbishop1 = new Sprite(bishop);
        sbishopBlack1 = new Sprite(bishopBlack);
        sbishop2 = new Sprite(bishop);
        sbishopBlack2 = new Sprite(bishopBlack);
        sking= new Sprite(king);
        skingBlack= new Sprite(kingBlack);
        sknight1 = new Sprite(knight);
        sknightBlack1 = new Sprite(knightBlack);
        sknight2 = new Sprite(knight);
        sknightBlack2 = new Sprite(knightBlack);
        spawn1 = new Sprite(pawn);
        spawn2 = new Sprite(pawn);
        spawn3 = new Sprite(pawn);
        spawn4 = new Sprite(pawn);
        spawn5 = new Sprite(pawn);
        spawn6 = new Sprite(pawn);
        spawn7 = new Sprite(pawn);
        spawn8 = new Sprite(pawn);
        spawnBlack1 = new Sprite(pawnBlack);
        spawnBlack2 = new Sprite(pawnBlack);
        spawnBlack3 = new Sprite(pawnBlack);
        spawnBlack4 = new Sprite(pawnBlack);
        spawnBlack5 = new Sprite(pawnBlack);
        spawnBlack6 = new Sprite(pawnBlack);
        spawnBlack7 = new Sprite(pawnBlack);
        spawnBlack8 = new Sprite(pawnBlack);
        squeen = new Sprite(queen);
        squeenBlack = new Sprite(queenBlack);
        srook1 = new Sprite(rook);
        srookBlack1 = new Sprite(rookBlack);
        srook2 = new Sprite(rook);
        srookBlack2 = new Sprite(rookBlack);
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

        promotedB1 = new Sprite(bishop);
        promotedB1.setSize(32, 52);
        promotedB2 = new Sprite(bishop);
        promotedB2.setSize(32, 52);
        promotedB3 = new Sprite(bishop);
        promotedB3.setSize(32, 52);
        promotedB4 = new Sprite(bishop);
        promotedB4.setSize(32, 52);
        promotedB5 = new Sprite(bishop);
        promotedB6 = new Sprite(bishop);
        promotedB7 = new Sprite(bishop);
        promotedB8 = new Sprite(bishop);
        promotedB9 = new Sprite(bishop);
        promotedB10 = new Sprite(bishop);

        bpromotedB1 = new Sprite(bishopBlack);
        bpromotedB1.setSize(32, 52);
        bpromotedB2 = new Sprite(bishopBlack);
        bpromotedB2.setSize(32, 52);
        bpromotedB3 = new Sprite(bishopBlack);
        bpromotedB3.setSize(32, 52);
        bpromotedB4 = new Sprite(bishopBlack);
        bpromotedB4.setSize(32, 52);
        bpromotedB5 = new Sprite(bishopBlack);
        bpromotedB6 = new Sprite(bishopBlack);
        bpromotedB7 = new Sprite(bishopBlack);
        bpromotedB8 = new Sprite(bishopBlack);
        bpromotedB9 = new Sprite(bishopBlack);
        bpromotedB10 = new Sprite(bishopBlack);


        promotedK1 = new Sprite(knight);
        promotedK1.setSize(32, 54);
        promotedK2 = new Sprite(knight);
        promotedK3 = new Sprite(knight);
        promotedK4 = new Sprite(knight);
        promotedK5 = new Sprite(knight);
        promotedK6 = new Sprite(knight);
        promotedK7 = new Sprite(knight);
        promotedK8 = new Sprite(knight);
        promotedK9 = new Sprite(knight);
        promotedK10 = new Sprite(knight);

        bpromotedK1 = new Sprite(knightBlack);
        bpromotedK1.setSize(32, 54);
        bpromotedK2 = new Sprite(knightBlack);
        bpromotedK3 = new Sprite(knightBlack);
        bpromotedK4 = new Sprite(knightBlack);
        bpromotedK5 = new Sprite(knightBlack);
        bpromotedK6 = new Sprite(knightBlack);
        bpromotedK7 = new Sprite(knightBlack);
        bpromotedK8 = new Sprite(knightBlack);
        bpromotedK9 = new Sprite(knightBlack);
        bpromotedK10 = new Sprite(knightBlack);


        bpromotedQ1 = new Sprite(queenBlack);
        bpromotedQ2 = new Sprite(queenBlack);
        bpromotedQ3 = new Sprite(queenBlack);
        bpromotedQ4 = new Sprite(queenBlack);
        bpromotedQ5 = new Sprite(queenBlack);
        bpromotedQ6 = new Sprite(queenBlack);
        bpromotedQ7 = new Sprite(queenBlack);
        bpromotedQ8 = new Sprite(queenBlack);
        bpromotedQ9 = new Sprite(queenBlack);

        promotedQ1 = new Sprite(queen);
        promotedQ2 = new Sprite(queen);
        promotedQ3 = new Sprite(queen);
        promotedQ4 = new Sprite(queen);
        promotedQ5 = new Sprite(queen);
        promotedQ6 = new Sprite(queen);
        promotedQ7 = new Sprite(queen);
        promotedQ8 = new Sprite(queen);
        promotedQ9 = new Sprite(queen);


        bpromotedR1 = new Sprite(rookBlack);
        bpromotedR2 = new Sprite(rookBlack);
        bpromotedR3 = new Sprite(rookBlack);
        bpromotedR4 = new Sprite(rookBlack);
        bpromotedR5 = new Sprite(rookBlack);
        bpromotedR6 = new Sprite(rookBlack);
        bpromotedR7 = new Sprite(rookBlack);
        bpromotedR8 = new Sprite(rookBlack);
        bpromotedR9 = new Sprite(rookBlack);
        bpromotedR10 = new Sprite(rookBlack);

        promotedR1 = new Sprite(rook);
        promotedR2 = new Sprite(rook);
        promotedR3 = new Sprite(rook);
        promotedR4 = new Sprite(rook);
        promotedR5 = new Sprite(rook);
        promotedR6 = new Sprite(rook);
        promotedR7 = new Sprite(rook);
        promotedR8 = new Sprite(rook);
        promotedR9 = new Sprite(rook);
        promotedR10 = new Sprite(rook);

        promotedKnight.add(promotedK1);
        promotedKnight.add(promotedK2);
        promotedKnight.add(promotedK3);
        promotedKnight.add(promotedK4);
        promotedKnight.add(promotedK5);
        promotedKnight.add(promotedK6);
        promotedKnight.add(promotedK7);
        promotedKnight.add(promotedK8);
        promotedKnight.add(promotedK9);
        promotedKnight.add(promotedK10);

        bpromotedKnight.add(bpromotedK1);
        bpromotedKnight.add(bpromotedK2);
        bpromotedKnight.add(bpromotedK3);
        bpromotedKnight.add(bpromotedK4);
        bpromotedKnight.add(bpromotedK5);
        bpromotedKnight.add(bpromotedK6);
        bpromotedKnight.add(bpromotedK7);
        bpromotedKnight.add(bpromotedK8);
        bpromotedKnight.add(bpromotedK9);
        bpromotedKnight.add(bpromotedK10);

        promotedRook.add(promotedR1);
        promotedRook.add(promotedR2);
        promotedRook.add(promotedR3);
        promotedRook.add(promotedR4);
        promotedRook.add(promotedR5);
        promotedRook.add(promotedR6);
        promotedRook.add(promotedR7);
        promotedRook.add(promotedR8);
        promotedRook.add(promotedR9);
        promotedRook.add(promotedR10);
        bpromotedRook.add(bpromotedR1);
        bpromotedRook.add(bpromotedR2);
        bpromotedRook.add(bpromotedR3);
        bpromotedRook.add(bpromotedR4);
        bpromotedRook.add(bpromotedR5);
        bpromotedRook.add(bpromotedR6);
        bpromotedRook.add(bpromotedR7);
        bpromotedRook.add(bpromotedR8);
        bpromotedRook.add(bpromotedR9);
        bpromotedRook.add(bpromotedR10);

        promotedBishops.add(promotedB1);
        promotedBishops.add(promotedB2);
        promotedBishops.add(promotedB3);
        promotedBishops.add(promotedB4);
        promotedBishops.add(promotedB5);
        promotedBishops.add(promotedB6);
        promotedBishops.add(promotedB7);
        promotedBishops.add(promotedB8);
        promotedBishops.add(promotedB9);
        promotedBishops.add(promotedB10);

        bpromotedBishops.add(bpromotedB1);
        bpromotedBishops.add(bpromotedB2);
        bpromotedBishops.add(bpromotedB3);
        bpromotedBishops.add(bpromotedB4);
        bpromotedBishops.add(bpromotedB5);
        bpromotedBishops.add(bpromotedB6);
        bpromotedBishops.add(bpromotedB7);
        bpromotedBishops.add(bpromotedB8);
        bpromotedBishops.add(bpromotedB9);
        bpromotedBishops.add(bpromotedB10);

        promotedQueens.add(bpromotedQ1);
        promotedQueens.add(bpromotedQ2);
        promotedQueens.add(bpromotedQ3);
        promotedQueens.add(bpromotedQ4);
        promotedQueens.add(bpromotedQ5);
        promotedQueens.add(bpromotedQ6);
        promotedQueens.add(promotedQ7);
        promotedQueens.add(promotedQ8);
        promotedQueens.add(promotedQ9);

        spriteNotation.setPosition(625, 50);
        spriteNotation.setSize(250, 350);
        spawn1.setSize(33, 47);
        spawn1.setPosition(112, 135);
        spawnBlack1.setSize(33, 47);
        spawnBlack1.setPosition(114, 400);
        spawn2.setSize(33, 47);
        spawn2.setPosition(167, 135);
        spawnBlack2.setSize(33, 47);
        spawnBlack2.setPosition(169, 400);
        spawn3.setSize(33, 47);
        spawn3.setPosition(222, 135);
        spawnBlack3.setSize(33, 47);
        spawnBlack3.setPosition(224, 400);
        spawn4.setSize(33, 47);
        spawn4.setPosition(275, 135);
        spawnBlack4.setSize(33, 47);
        spawnBlack4.setPosition(277, 400);
        spawn5.setSize(33, 47);
        spawn5.setPosition(329, 135);
        spawnBlack5.setSize(33, 47);
        spawnBlack5.setPosition(331, 400);
        spawn6.setSize(33, 47);
        spawn6.setPosition(385, 135);
        spawnBlack6.setSize(33, 47);
        spawnBlack6.setPosition(387, 400);
        spawn7.setSize(33, 47);
        spawn7.setPosition(437, 135);
        spawnBlack7.setSize(33, 47);
        spawnBlack7.setPosition(439, 400);
        spawn8.setSize(33, 47);
        spawn8.setPosition(491, 135);
        spawnBlack8.setSize(33, 47);
        spawnBlack8.setPosition(493, 400);
        srook1.setSize( 33, 47);
        srook1.setPosition(493, 80);
        srookBlack1.setSize(32, 54);
        srookBlack1.setPosition(493, 450);
        srook2.setSize( 33, 47);
        srook2.setPosition(114, 80);
        srookBlack2.setSize(32, 54);
        srookBlack2.setPosition(114, 450);
        sknightBlack1.setSize(36, 54);
        sknightBlack1.setPosition(436, 447);
        sknight1.setSize(32, 54);
        sknight1.setPosition(437, 82);
        sknightBlack2.setSize(36, 54);
        sknightBlack2.setPosition(167, 447);
        sknight2.setSize(32, 54);
        sknight2.setPosition(168, 82);
        sbishop1.setSize(32, 52);
        sbishop1.setPosition(383, 80);
        sbishopBlack1.setSize(32, 46);
        sbishopBlack1.setPosition(383, 451);
        sbishop2.setSize(32, 52);
        sbishop2.setPosition(221, 80);
        sbishopBlack2.setSize(32, 46);
        sbishopBlack2.setPosition(221, 451);
        sking.setSize(33, 47);
        sking.setPosition(331, 80);
        skingBlack.setSize(25, 51);
        skingBlack.setPosition(334, 447);
        squeen.setSize(33, 47);
        squeen.setPosition(276, 81);
        squeenBlack.setSize(34, 53);
        squeenBlack.setPosition(276, 447);
        spriteHighlight.setSize(55, 55);
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

        pieceStorage[0][0] = srookBlack2;
        pieceStorage[0][1] = sknightBlack2;
        pieceStorage[0][2] = sbishopBlack2;
        pieceStorage[0][3] = squeenBlack;
        pieceStorage[0][4] = skingBlack;
        pieceStorage[0][5] = sbishopBlack1;
        pieceStorage[0][6] = sknightBlack1;
        pieceStorage[0][7] = srookBlack1;
        pieceStorage[1][0] = spawnBlack1;
        pieceStorage[1][1] = spawnBlack2;
        pieceStorage[1][2] = spawnBlack3;
        pieceStorage[1][3] = spawnBlack4;
        pieceStorage[1][4] = spawnBlack5;
        pieceStorage[1][5] = spawnBlack6;
        pieceStorage[1][6] = spawnBlack7;
        pieceStorage[1][7] = spawnBlack8;
        pieceStorage[6][0] = spawn1;
        pieceStorage[6][1] = spawn2;
        pieceStorage[6][2] = spawn3;
        pieceStorage[6][3] = spawn4;
        pieceStorage[6][4] = spawn5;
        pieceStorage[6][5] = spawn6;
        pieceStorage[6][6] = spawn7;
        pieceStorage[6][7] = spawn8;
        pieceStorage[7][0] = srook2;
        pieceStorage[7][1] = sknight2;
        pieceStorage[7][2] = sbishop2;
        pieceStorage[7][3] = squeen;
        pieceStorage[7][4] = sking;
        pieceStorage[7][5] = sbishop1;
        pieceStorage[7][6] = sknight1;
        pieceStorage[7][7] = srook1;
    }

    String [][] boardTranslate ={{"br1","bk1","bb1","bq","bk","bb2","bk2","br2"},
                                {"bp1","bp2","bp3","bp4","bp5","bp6","bp7","bp8"},
                                {" 0 "," 0 "," 0 "," 0 "," 0  "," 0 "," 0 "," 0 "},
                                {" 0 "," 0 "," 0 "," 0 "," 0  "," 0 "," 0 "," 0 "},
                                {" 0 "," 0 "," 0 "," 0 "," 0  "," 0 "," 0 "," 0 "},
                                {" 0 "," 0 "," 0 "," 0 "," 0  "," 0 "," 0 "," 0 "},
                                {"p1","p2","p3","p4","p5","p6","p7","p8"},
                                {"r1","k1","b1","q","k","b2","k2","r2"}};


    public Sprite getCorrectPiece(Square square){
        Piece piece = square.getPiece();
        switch (piece.getNotation()){
            case "0":
                return null;
            case "r1":
                return srook1;
            case "k1":
                return sknight1;
            case "b1":
                return sbishop1;
            case "q":
                return squeen;
            case "k":
                return sking;
            case "b2":
                return sbishop2;
            case "k2":
                return sknight2;
            case "r2":
                return srook2;
            case "br1":
                return srookBlack1;
            case "bk1":
                return sknightBlack1;
            case "bb1":
                return sbishopBlack1;
            case "bq":
                return squeenBlack;
            case "bk":
                return skingBlack;
            case "bb2":
                return sbishopBlack2;
            case "bk2":
                return sknightBlack2;
            case "br2":
                return srookBlack2;
            case "p1":
                return spawn1;
            case "p2":
                return spawn2;
            case "p3":
                return spawn3;
            case "p4":
                return spawn4;
            case "p5":
                return spawn5;
            case "p6":
                return spawn6;
            case "p7":
                return spawn7;
            case "p8":
                return spawn8;
            case "bp1":
                return spawnBlack1;
            case "bp2":
                return spawnBlack2;
            case "bp3":
                return spawnBlack3;
            case "bp4":
                return spawnBlack4;
            case "bp5":
                return spawnBlack5;
            case "bp6":
                return spawnBlack6;
            case "bp7":
                return spawnBlack7;
            case "bp8":
                return spawnBlack8;
            case "r3":
                return promotedR1;
            case "r4":
                return promotedR2;
            case "r5":
                return promotedR3;
            case "r6":
                return promotedR4;

            case "r7":
                return promotedR5;
            case "r8":
                return promotedR6;
            case "r9":
                return promotedR7;
            case "r10":
                return promotedR8;
            case "r11":
                return promotedR9;
            case "r12":
                return promotedR10;
            case "k3":
                return promotedK1;
            case "k4":
                return promotedK2;
            case "k5":
                return promotedK3;
            case "k6":
                return promotedK4;
            case "bk3":
                return bpromotedK1;
            case "bk4":
                return bpromotedK2;
            case "bk5":
                return bpromotedK3;
            case "bk6":
                return bpromotedK4;
            case "k7":
                return promotedK5;
            case "k8":
                return promotedK6;
            case "k9":
                return promotedK7;
            case "k10":
                return promotedK8;
            case "k11":
                return promotedK9;
            case "k12":
                return promotedK10;
            case "q2":
                return promotedQ1;
            case "q3":
                return promotedQ2;
            case "q4":
                return promotedQ3;
            case "q5":
                return promotedQ4;
            case "bq2":
                return bpromotedQ1;
            case "bq3":
                return bpromotedQ2;
            case "bq4":
                return bpromotedQ3;
            case "bq5":
                return bpromotedQ4;
            case "q6":
                return promotedQ5;
            case "q7":
                return promotedQ6;
            case "q8":
                return promotedQ7;
            case "q9":
                return promotedQ8;
            case "q10":
                return promotedQ9;
            case "b3":
                return promotedB1;
            case "b4":
                return promotedB2;
            case "b5":
                return promotedB3;
            case "b6":
                return promotedB4;
            case "b7":
                return promotedB5;
            case "bb3":
                return bpromotedB1;
            case "bb4":
                return bpromotedB2;
            case "bb5":
                return bpromotedB3;
            case "bb6":
                return bpromotedB4;
            case "bb7":
                return bpromotedB5;
            case "b8":
                return promotedB6;
            case "b9":
                return promotedB7;
            case "b10":
                return promotedB8;
            case "b11":
                return promotedB9;
            case "b12":
                return promotedB10;
            case "br3":
                return bpromotedR1;
            case "br4":
                return bpromotedR2;
            case "br5":
                return bpromotedR3;
            case "br6":
                return bpromotedR4;
            case "br7":
                return bpromotedR5;
            case "br8":
                return bpromotedR6;
            case "br9":
                return bpromotedR7;
            case "br10":
                return bpromotedR8;
            case "br11":
                return bpromotedR9;
            case "br12":
                return bpromotedR10;
        }
        return null;
    }

    public void setUpBoard(Board boardN){
        for (int row = 0; row < pieceStorage.length; row++) {
            for (int col = 0; col < pieceStorage[0].length; col++) {
                if(boardN.getMBoard()[row][col].getPiece() != null){
                    boardN.getMBoard()[row][col].getPiece().setNotation(boardTranslate[row][col]);
                }
            }
        }
    }

    public void updateBoard(Board boardN){
        Square[][] tempBoard = boardN.getMBoard();
        for (int row = 0; row < pieceStorage.length; row++) {
            for (int col = 0; col < pieceStorage[0].length; col++) {
                if(tempBoard[row][col].getPiece() != null){
                    if(!boardN.getMBoard()[row][col].getPiece().getNotation().equals("")){
                        boardTranslate[row][col] =  boardN.getMBoard()[row][col].getPiece().getNotation();
                        System.out.print(boardTranslate[row][col] + " ");
                        pieceStorage[row][col] = getCorrectPiece(boardN.getMBoard()[row][col]);
                    } else{
                        System.out.println(boardN.getMBoard()[row][col].getPiece().getIdentifier());
                        switch (boardN.getMBoard()[row][col].getPiece().getIdentifier()){
                            case "R":
                                if(boardN.getMBoard()[row][col].getPiece().getWhiteStatus()){
                                    int tem = rookCounter+2;
                                    String promotedTemp = "r" + tem;
                                    boardN.getMBoard()[row][col].getPiece().setNotation(promotedTemp);
                                    boardTranslate[row][col] =  boardN.getMBoard()[row][col].getPiece().getNotation();
                                    promotedRook.get(rookCounter).setSize(33, 47);
                                    pieceStorage[row][col] = promotedRook.get(rookCounter);
                                    rookCounter++;
                                    break;
                                }else {
                                    int tem = brookCounter+2;
                                    String promotedTemp = "br" + tem;
                                    boardN.getMBoard()[row][col].getPiece().setNotation(promotedTemp);
                                    boardTranslate[row][col] =  boardN.getMBoard()[row][col].getPiece().getNotation();
                                    bpromotedRook.get(brookCounter).setSize(32, 54);
                                    pieceStorage[row][col] = bpromotedRook.get(brookCounter);
                                    brookCounter++;
                                    break;
                                }
                            case "N":
                                if(boardN.getMBoard()[row][col].getPiece().getWhiteStatus()){
                                    int tem1 = knightCounter+2;
                                    String promotedTemp1 = "k" + tem1;
                                    boardN.getMBoard()[row][col].getPiece().setNotation(promotedTemp1);
                                    boardTranslate[row][col] =  boardN.getMBoard()[row][col].getPiece().getNotation();
                                    promotedKnight.get(knightCounter).setSize(32, 54);
                                    pieceStorage[row][col] = promotedKnight.get(knightCounter);
                                    knightCounter++;
                                    break;
                                }else{
                                    int tem1 = bknightCounter+2;
                                    String promotedTemp1 = "bk" + tem1;
                                    boardN.getMBoard()[row][col].getPiece().setNotation(promotedTemp1);
                                    boardTranslate[row][col] =  boardN.getMBoard()[row][col].getPiece().getNotation();
                                    bpromotedKnight.get(bknightCounter).setSize(32, 54);
                                    pieceStorage[row][col] = bpromotedKnight.get(bknightCounter);
                                    bknightCounter++;
                                    break;
                                }

                            case "Q":
                                if(boardN.getMBoard()[row][col].getPiece().getWhiteStatus()){
                                    int tem2 = queenCounter+2;
                                    String promotedTemp2 = "q" + tem2;
                                    boardN.getMBoard()[row][col].getPiece().setNotation(promotedTemp2);
                                    boardTranslate[row][col] =  boardN.getMBoard()[row][col].getPiece().getNotation();
                                    promotedQueens.get(queenCounter).setSize(33, 47);
                                    pieceStorage[row][col] = promotedQueens.get(queenCounter);
                                    queenCounter++;
                                    break;
                                } else{
                                    int tem2 = bqueenCounter+2;
                                    String promotedTemp2 = "bq" + tem2;
                                    boardN.getMBoard()[row][col].getPiece().setNotation(promotedTemp2);
                                    boardTranslate[row][col] =  boardN.getMBoard()[row][col].getPiece().getNotation();
                                    bpromotedQueens.get(bqueenCounter).setSize(33, 47);
                                    pieceStorage[row][col] = bpromotedQueens.get(bqueenCounter);
                                    bqueenCounter++;
                                    break;
                                }
                            case "B":
                                if(boardN.getMBoard()[row][col].getPiece().getWhiteStatus()){
                                    int tem3 = bishopCounter+2;
                                    String promotedTemp3 = "b" + tem3;
                                    boardN.getMBoard()[row][col].getPiece().setNotation(promotedTemp3);
                                    boardTranslate[row][col] =  boardN.getMBoard()[row][col].getPiece().getNotation();
                                    promotedBishops.get(bishopCounter).setSize(32, 52);
                                    pieceStorage[row][col] = promotedBishops.get(bishopCounter);
                                    bishopCounter++;
                                    break;
                                }else {
                                    int tem3 = bbishopCounter+2;
                                    String promotedTemp3 = "bb" + tem3;
                                    boardN.getMBoard()[row][col].getPiece().setNotation(promotedTemp3);
                                    boardTranslate[row][col] =  boardN.getMBoard()[row][col].getPiece().getNotation();
                                    bpromotedBishops.get(bbishopCounter).setSize(32, 52);
                                    pieceStorage[row][col] = bpromotedBishops.get(bbishopCounter);
                                    bbishopCounter++;
                                    break;
                                }
                        }
                    System.out.println(boardN.getMBoard()[row][col].getPiece().getNotation() + " ");
                    }
                }else{
                    pieceStorage[row][col] = null;
                    System.out.print(" 0 ");
                }
            }
            System.out.println();
        }
    }

    public String [] intoCoorNotation(int n, int z){

        String [] not = new String[2];
        switch (n){
            case 0:
                not[0] = "a";
                break;
            case 1:
                not[0] = "b";
                break;
            case 2:
                not[0] = "c";
                break;
            case 3:
                not[0] = "d";
                break;
            case 4:
                not[0] = "e";
                break;
            case 5:
                not[0] = "f";
                break;
            case 6:
                not[0] = "g";
                break;
            case 7:
                not[0] = "h";
                break;
        }
        switch (z){
            case 0:
                not[1] = "8";
                break;
            case 1:
                not[1] = "7";
                break;
            case 2:
                not[1] = "6";
                break;
            case 3:
                not[1] = "5";
                break;
            case 4:
                not[1] = "4";
                break;
            case 5:
                not[1] = "3";
                break;
            case 6:
                not[1] = "2";
                break;
            case 7:
                not[1] = "1";
                break;
        }
        return not;
    }

    public void Roll(int diceN) {
        currentSide = diceN;
    }
}
