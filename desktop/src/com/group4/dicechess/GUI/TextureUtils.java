package com.group4.dicechess.GUI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
                    boardTranslate[row][col] =  boardN.getMBoard()[row][col].getPiece().getNotation();
                    pieceStorage[row][col] = getCorrectPiece(boardN.getMBoard()[row][col]);
                }else{
                    pieceStorage[row][col] = null;
                }
            }
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
