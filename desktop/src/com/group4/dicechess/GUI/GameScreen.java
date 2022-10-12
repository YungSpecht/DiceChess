package com.group4.dicechess.GUI;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import com.group4.dicechess.Representation.Square;
import com.group4.dicechess.UIInterface;

public class GameScreen implements Screen {

    DiceChessGame game;
    TextureUtils textureUtils;
    public boolean rolled = false;
    public boolean rolledBefore = false;
    int [] tempPoss = {-1,-1};
    int [] tempPoss2 = {-1,-1};
    String [] helperNot;
    UIInterface gameLoop;
    int diceN = 0;
    int x1 = 0;
    int y1 = 0;
    int cnt = -1;
    int txtTracker = 1;
    int decider = 0;
    boolean playerSwitch = true;
    boolean turnActive = false;
    boolean isPlayable = false;
    boolean justSelected = false;
    ArrayList<Square> possibleMoves;
    ArrayList<String>  txtOtp;
    BitmapFont font = new BitmapFont();
    String moveN;


    public GameScreen(DiceChessGame currentGame){
        this.game = currentGame;
        this.textureUtils = new TextureUtils();
        gameLoop = new UIInterface();
        enableTxt();
    }

    public void enableTxt(){
        txtOtp = new ArrayList<>();
        txtOtp.add("Welcome!");
    }

    @Override
    public void render(float delta) {
        chess();
        
        ScreenUtils.clear(1, 1, 1, 1);
        game.batch.begin();
        game.batch.draw(textureUtils.notation, textureUtils.spriteNotation.getX(), textureUtils.spriteNotation.getY(), textureUtils.spriteNotation.getWidth(), textureUtils.spriteNotation.getHeight());
        game.batch.draw(textureUtils.board, textureUtils.spriteBoard.getX(), textureUtils.spriteBoard.getY(), textureUtils.spriteBoard.getWidth(), textureUtils.spriteBoard.getHeight());

        int xTxt = 650;
        int yTxt = 380;
        if(txtTracker > 18){
            decider = txtTracker -18;
        }
        for (int i = decider; i < txtOtp.size(); i++) {
            font.draw(game.batch, txtOtp.get(i), xTxt, yTxt+((i-decider)*-15));
        }

        for (int i = 0; i < textureUtils.pieceStorage.length; i++) {
            for (int j = 0; j < textureUtils.pieceStorage[0].length; j++) {
                if(textureUtils.pieceStorage[i][j] != null){
                    game.batch.draw(textureUtils.pieceStorage[i][j], textureUtils.pieceStorage[i][j].getX(), textureUtils.pieceStorage[i][j].getY(), textureUtils.pieceStorage[i][j].getWidth(), textureUtils.pieceStorage[i][j].getHeight());
                }
            }
        }
        if(justSelected){
            for (int i = 0; i < possibleMoves.size(); i++) {
                if(possibleMoves.get(i).getRow() == 0 && possibleMoves.get(i).getCol() == 0){
                    game.batch.draw(textureUtils.highlight, 103, 449, 55, 54);
                } else {
                    game.batch.draw(textureUtils.highlight, 103 + possibleMoves.get(i).getCol()*(54), 449 - possibleMoves.get(i).getRow()*(53), 55, 54);
                }
            }
        }

        if (rolled) {
            textureUtils.Roll(diceN);
            rolledBefore = true;
        } else if (!rolledBefore) {
            game.batch.draw(textureUtils.spriteDice1, textureUtils.spriteDice1.getX(), textureUtils.spriteDice1.getY(), textureUtils.spriteDice1.getWidth(), textureUtils.spriteDice1.getHeight());
        }
        rolled = false;
        if (textureUtils.currentSide == 1) {
            game.batch.draw(textureUtils.spriteDice1, textureUtils.spriteDice1.getX(), textureUtils.spriteDice1.getY(), textureUtils.spriteDice1.getWidth(), textureUtils.spriteDice1.getHeight());
        } else if (textureUtils.currentSide == 2) {
            game.batch.draw(textureUtils.spriteDice2, textureUtils.spriteDice2.getX(), textureUtils.spriteDice2.getY(), textureUtils.spriteDice2.getWidth(), textureUtils.spriteDice2.getHeight());
        } else if (textureUtils.currentSide == 3) {
            game.batch.draw(textureUtils.spriteDice3, textureUtils.spriteDice3.getX(), textureUtils.spriteDice3.getY(), textureUtils.spriteDice3.getWidth(), textureUtils.spriteDice3.getHeight());
        } else if (textureUtils.currentSide == 4) {
            game.batch.draw(textureUtils.spriteDice4, textureUtils.spriteDice4.getX(), textureUtils.spriteDice4.getY(), textureUtils.spriteDice4.getWidth(), textureUtils.spriteDice4.getHeight());
        } else if (textureUtils.currentSide == 5) {
            game.batch.draw(textureUtils.spriteDice5, textureUtils.spriteDice5.getX(), textureUtils.spriteDice5.getY(), textureUtils.spriteDice5.getWidth(), textureUtils.spriteDice5.getHeight());
        } else if (textureUtils.currentSide == 6) {
            game.batch.draw(textureUtils.spriteDice6, textureUtils.spriteDice6.getX(), textureUtils.spriteDice6.getY(), textureUtils.spriteDice6.getWidth(), textureUtils.spriteDice6.getHeight());
        }
        game.batch.draw(textureUtils.spriteArrow, textureUtils.spriteArrow.getX(), textureUtils.spriteArrow.getY(), textureUtils.spriteArrow.getWidth(), textureUtils.spriteArrow.getHeight());
        game.batch.end();
    }

    private void diceChess1(){
        if(!gameLoop.gameOver()){
            if(playerSwitch && !turnActive){
                cnt++;
                gameLoop.turnCounter++;
                if(cnt % 2 == 0){
                    txtOtp.add("------------White's turn------------");
                    txtTracker++;
                    txtOtp.add("Please roll the dice..");
                    txtTracker++;
                } else {
                    txtOtp.add("------------Black's turn------------");
                    txtTracker++;
                    txtOtp.add("Please roll the dice..");
                    txtTracker++;
                }
                playerSwitch = false;
            }
    
            Gdx.input.setInputProcessor(new InputAdapter() {
                @Override
                public boolean touchDown(int screenX, int screenY, int pointer, int button) {

                    if (screenX >= 15 && screenX <= 45 && screenY >= 10 && screenY <= 40) {
                        game.setScreen(new MenuScreen(game));
                        Gdx.input.setInputProcessor(null);
                    }
                    if (screenX >= 710 && screenX <= 785 && screenY >= 75 && screenY <= 147 && !turnActive) {
                        Random random = new Random();
                        diceN = random.nextInt(5) + 1;
                        rolled = true;
                        txtOtp.add("Dice rolled. Result is " + diceN);
                        txtTracker++;
                        isPlayable = gameLoop.legalMovesAreAvailable(diceN);
                        if(isPlayable){
                            txtOtp.add("Valid! Please select your piece..");
                            txtTracker++;
                            turnActive = true;
                        }
                        else{
                            txtOtp.add("Not valid. Next Player!");
                            txtTracker++;
                            playerSwitch = true;
                        }
                        rolled = true;
                    }
                    if(screenX >= 105 && screenX <= 529 && screenY <= 523 && screenY >= 99 && turnActive){
                        if(tempPoss[0] == -1){
                            tempPoss = translateToArrayPos(screenX, screenY);
                            if(gameLoop.isLegalPiece(tempPoss[1], tempPoss[0])){
                                helperNot = intoCoorNotation(tempPoss[0], tempPoss[1]);
                                moveN = "Selected: " + helperNot[0] + helperNot[1];
                                txtOtp.add(moveN);
                                txtTracker++;
                                possibleMoves = gameLoop.getLegalMoves(tempPoss[1], tempPoss[0]);
                                justSelected = true;
                            }
                        }
                        else if(gameLoop.isLegalPiece(tempPoss[1], tempPoss[0])){
                            tempPoss2 = translateToArrayPos(screenX, screenY);
                            justSelected = false;
                            if(gameLoop.isLegalMove(tempPoss[1], tempPoss[0], tempPoss2[1], tempPoss2[0])){
                                helperNot = intoCoorNotation(tempPoss[0], tempPoss[1]);
                                String [] helperNot2 = intoCoorNotation(tempPoss2[0], tempPoss2[1]);
                                moveN = helperNot[0] + helperNot[1] + " -> " + helperNot2[0] + helperNot2[1];
                                txtOtp.add(moveN);
                                txtTracker++;
                                gameLoop.movePiece(tempPoss[1], tempPoss[0], tempPoss2[1], tempPoss2[0]);
                                textureUtils.pieceStorage[tempPoss2[1]][tempPoss2[0]] = textureUtils.pieceStorage[tempPoss[1]][tempPoss[0]] ;
                                textureUtils.pieceStorage[tempPoss[1]][tempPoss[0]] = null;
                                textureUtils.pieceStorage[tempPoss2[1]][tempPoss2[0]].setPosition(textureUtils.pieceStorage[tempPoss2[1]][tempPoss2[0]].getX() + 54*(tempPoss2[0] - tempPoss[0]) , textureUtils.pieceStorage[tempPoss2[1]][tempPoss2[0]].getY() - 53*(tempPoss2[1] - tempPoss[1]));
                                tempPoss[0] = -1;
                                tempPoss[1] = -1;
                                System.out.println("Valid!");
                                turnActive = false;
                                playerSwitch = true;
                                diceN = 0;
                            }
                            else {
                                txtOtp.add("Please try another cell as destination!");
                                txtTracker++;
                                justSelected = true;
                            }
                        }
                        else{
                            txtOtp.add("Try a new initial piece!");
                            txtTracker++;
                            tempPoss[0] = -1;
                            tempPoss[1] = -1;
                        }
                    }
                    return true;
                }
            });
        }
        else{
            String winner = cnt%2==0 ? "Black" : "White";
            game.setScreen(new GameOverScreen(game, winner));
        }
    }

    private void diceChess2(){
        if(!gameLoop.gameOver()){
            if(playerSwitch && !turnActive){
                cnt++;
                gameLoop.turnCounter++;
                if(cnt % 2 == 0){
                    txtOtp.add("------------White's turn------------");
                    txtTracker++;
                    txtOtp.add("Please roll the dice..");
                    txtTracker++;
                } else {
                    txtOtp.add("------------Black's turn------------");
                    txtTracker++;
                    txtOtp.add("Please roll the dice..");
                    txtTracker++;
                }
                playerSwitch = false;
            }
    
            Gdx.input.setInputProcessor(new InputAdapter() {
                @Override
                public boolean touchDown(int screenX, int screenY, int pointer, int button) {

                    if (screenX >= 15 && screenX <= 45 && screenY >= 10 && screenY <= 40) {
                        game.setScreen(new MenuScreen(game));
                        Gdx.input.setInputProcessor(null);
                    }
                    if (screenX >= 710 && screenX <= 785 && screenY >= 75 && screenY <= 147 && !turnActive) {
                        diceN = gameLoop.diceRoll();
                        rolled = true;
                        txtOtp.add("Dice rolled. Result is " + diceN);
                        txtTracker++;
                        isPlayable = gameLoop.legalMovesAreAvailable(diceN);
                        if(isPlayable){
                            txtOtp.add("Valid! Please select your piece..");
                            txtTracker++;
                            turnActive = true;
                        }
                        else{
                            txtOtp.add("Not valid. Next Player!");
                            txtTracker++;
                            playerSwitch = true;
                        }
                    }
                    if(screenX >= 105 && screenX <= 529 && screenY <= 523 && screenY >= 99 && turnActive){
                        if(tempPoss[0] == -1){
                            tempPoss = translateToArrayPos(screenX, screenY);
                            if(gameLoop.isLegalPiece(tempPoss[1], tempPoss[0])){
                                helperNot = intoCoorNotation(tempPoss[0], tempPoss[1]);
                                moveN = "Selected: " + helperNot[0] + helperNot[1];
                                txtOtp.add(moveN);
                                txtTracker++;
                                possibleMoves = gameLoop.getLegalMoves(tempPoss[1], tempPoss[0]);
                                justSelected = true;
                            }
                        }
                        else if(gameLoop.isLegalPiece(tempPoss[1], tempPoss[0])){
                            tempPoss2 = translateToArrayPos(screenX, screenY);
                            justSelected = false;
                            if(gameLoop.isLegalMove(tempPoss[1], tempPoss[0], tempPoss2[1], tempPoss2[0])){
                                helperNot = intoCoorNotation(tempPoss[0], tempPoss[1]);
                                String [] helperNot2 = intoCoorNotation(tempPoss2[0], tempPoss2[1]);
                                moveN = helperNot[0] + helperNot[1] + " -> " + helperNot2[0] + helperNot2[1];
                                txtOtp.add(moveN);
                                txtTracker++;
                                gameLoop.movePiece(tempPoss[1], tempPoss[0], tempPoss2[1], tempPoss2[0]);
                                textureUtils.pieceStorage[tempPoss2[1]][tempPoss2[0]] = textureUtils.pieceStorage[tempPoss[1]][tempPoss[0]] ;
                                textureUtils.pieceStorage[tempPoss[1]][tempPoss[0]] = null;
                                textureUtils.pieceStorage[tempPoss2[1]][tempPoss2[0]].setPosition(textureUtils.pieceStorage[tempPoss2[1]][tempPoss2[0]].getX() + 54*(tempPoss2[0] - tempPoss[0]) , textureUtils.pieceStorage[tempPoss2[1]][tempPoss2[0]].getY() - 53*(tempPoss2[1] - tempPoss[1]));
                                tempPoss[0] = -1;
                                tempPoss[1] = -1;
                                System.out.println("Valid!");
                                turnActive = false;
                                playerSwitch = true;
                                diceN = 0;
                            }
                            else {
                                txtOtp.add("Please try another cell as destination!");
                                txtTracker++;
                                justSelected = true;
                            }
                        }
                        else{
                            txtOtp.add("Try a new initial piece!");
                            txtTracker++;
                            tempPoss[0] = -1;
                            tempPoss[1] = -1;
                        }
                    }
                    return true;
                }
            });
        }
        else{
            String winner = cnt%2==0 ? "Black" : "White";
            game.setScreen(new GameOverScreen(game, winner));
        }
    }

    private void chess(){
        if(!gameLoop.gameOver()){
            if(playerSwitch && !turnActive){
                cnt++;
                gameLoop.turnCounter++;
                if(cnt % 2 == 0){
                    txtOtp.add("------------White's turn------------");
                    txtTracker++;
                } else {
                    txtOtp.add("------------Black's turn------------");
                    txtTracker++;
                }
                gameLoop.prepareTurn();
                playerSwitch = false;
                turnActive = true;
            }
    
            Gdx.input.setInputProcessor(new InputAdapter() {
                @Override
                public boolean touchDown(int screenX, int screenY, int pointer, int button) {

                    if (screenX >= 15 && screenX <= 45 && screenY >= 10 && screenY <= 40) {
                        game.setScreen(new MenuScreen(game));
                        Gdx.input.setInputProcessor(null);
                    }
                    if(screenX >= 105 && screenX <= 529 && screenY <= 523 && screenY >= 99 && turnActive){
                        if(tempPoss[0] == -1){
                            tempPoss = translateToArrayPos(screenX, screenY);
                            if(gameLoop.isLegalPieceChess(tempPoss[1], tempPoss[0])){
                                helperNot = intoCoorNotation(tempPoss[0], tempPoss[1]);
                                moveN = "Selected: " + helperNot[0] + helperNot[1];
                                txtOtp.add(moveN);
                                txtTracker++;
                                possibleMoves = gameLoop.getLegalMoves(tempPoss[1], tempPoss[0]);
                                justSelected = true;
                            }
                        }
                        else if(gameLoop.isLegalPieceChess(tempPoss[1], tempPoss[0])){
                            tempPoss2 = translateToArrayPos(screenX, screenY);
                            justSelected = false;
                            if(gameLoop.isLegalMove(tempPoss[1], tempPoss[0], tempPoss2[1], tempPoss2[0])){
                                helperNot = intoCoorNotation(tempPoss[0], tempPoss[1]);
                                String [] helperNot2 = intoCoorNotation(tempPoss2[0], tempPoss2[1]);
                                moveN = helperNot[0] + helperNot[1] + " -> " + helperNot2[0] + helperNot2[1];
                                txtOtp.add(moveN);
                                txtTracker++;
                                gameLoop.movePiece(tempPoss[1], tempPoss[0], tempPoss2[1], tempPoss2[0]);
                                textureUtils.pieceStorage[tempPoss2[1]][tempPoss2[0]] = textureUtils.pieceStorage[tempPoss[1]][tempPoss[0]] ;
                                textureUtils.pieceStorage[tempPoss[1]][tempPoss[0]] = null;
                                textureUtils.pieceStorage[tempPoss2[1]][tempPoss2[0]].setPosition(textureUtils.pieceStorage[tempPoss2[1]][tempPoss2[0]].getX() + 54*(tempPoss2[0] - tempPoss[0]) , textureUtils.pieceStorage[tempPoss2[1]][tempPoss2[0]].getY() - 53*(tempPoss2[1] - tempPoss[1]));
                                tempPoss[0] = -1;
                                tempPoss[1] = -1;
                                System.out.println("Valid!");
                                turnActive = false;
                                playerSwitch = true;
                                diceN = 0;
                            }
                            else {
                                txtOtp.add("Please try another cell as destination!");
                                txtTracker++;
                                justSelected = true;
                            }
                        }
                        else{
                            txtOtp.add("Try a new initial piece!");
                            txtTracker++;
                            tempPoss[0] = -1;
                            tempPoss[1] = -1;
                        }
                    }
                    return true;
                }
            });
        }
        else{
            String winner = cnt%2==0 ? "Black" : "White";
            game.setScreen(new GameOverScreen(game, winner));
        }
    }

    public int[] translateToArrayPos(int x, int y){
        int [] tempPos  = new int[2];
        int [] xPos = {105, 158, 211, 264, 317, 370, 423, 476, 529};
        int [] yPos = {99, 152, 205, 258, 311, 364, 417, 470, 523};

        for(int i = 0; i < xPos.length - 1; i++){
            if(x > xPos[i] && x < xPos[i+1]){
                tempPos[0] = i;
                x1 = xPos[i] -40;
            }
        }
        for(int i = 0; i < yPos.length - 1; i++){
            if(y > yPos[i] && y < yPos[i+1]){
                tempPos[1] = i;
                y1 = yPos[i+1] + 10;
            }
        }
        return tempPos;
    }

    public String [] intoCoorNotation(int n, int z){
        String [] not = new String[2];
        if(n == 0){
            not[0] = "a";
        } else if (n == 1) {
            not[0] = "b";
        } else if (n == 2) {
            not[0] = "c";
        } else if (n == 3) {
            not[0] = "d";
        } else if (n == 4) {
            not[0] = "e";
        } else if (n == 5) {
            not[0] = "f";
        } else if (n==6) {
            not[0] = "g";
        } else if (n == 7) {
            not[0] = "h";
        }
        if(z == 0){
            not[1] = "8";
        } else if (z == 1) {
            not[1] = "7";
        } else if (z == 2) {
            not[1] = "6";
        } else if (z == 3) {
            not[1] = "5";
        } else if (z == 4) {
            not[1] = "4";
        } else if (z == 5) {
            not[1] = "3";
        } else if (z == 6) {
            not[1] = "2";
        } else if (z == 7) {
            not[1] = "1";
        }
        return not;
    }

    @Override
    public void show() {}
    @Override
    public void resize(int width, int height) {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
    @Override
    public void dispose() {}
}