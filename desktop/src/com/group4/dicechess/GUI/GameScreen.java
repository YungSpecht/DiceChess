package com.group4.dicechess.GUI;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;
import com.group4.dicechess.UIInterface;

public class GameScreen implements Screen {

    DiceChessGame game;
    TextureUtils textureUtils;
    public boolean rolled = false;
    public boolean rolledBefore = false;
    int [] tempPoss = {-1,-1};
    int [] tempPoss2 = {-1,-1};
    UIInterface gameLoop;
    int diceN = 0;
    int x1 = 0;
    int y1 = 0;
    int cnt = -1;
    boolean turnActive = false;
    boolean playerSwitch = true;


    public GameScreen(DiceChessGame currentGame){
        this.game = currentGame;
        this.textureUtils = new TextureUtils();
        gameLoop = new UIInterface();
    }
    boolean isPlayable = false;

    @Override
    public void render(float delta) {
        if(!gameLoop.gameOver()){
            if(playerSwitch && !turnActive){
                cnt++;
                gameLoop.turnCounter++;
                if(cnt % 2 == 0){
                    System.out.println("White's turn! Please roll the dice..");
                    System.out.println();
                } else {
                    System.out.println("Black's turn! Please roll the dice..");
                    System.out.println();
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
                        System.out.println("Rolling dice..");
                        System.out.println();
                        Random random = new Random();
                        diceN = random.nextInt(5) + 1;
                        rolled = true;
                        isPlayable = gameLoop.legalMovesAreAvailable(diceN);
                        if(isPlayable){
                            if(cnt % 2 == 0){
                                System.out.println("White's turn! Please select your piece..");
                                System.out.println();
                            } else {
                                System.out.println("Black's turn! Please select your piece..");
                                System.out.println();
                            }
                            turnActive = true;
                        }
                        else{
                            System.out.println("Your dice roll doesn't grant you any moves. Next Player!");
                            System.out.println();
                            playerSwitch = true;
                        }
                        rolled = true;
                    }
                    if(screenX >= 105 && screenX <= 529 && screenY <= 523 && screenY >= 99 && turnActive){
                        if(tempPoss[0] == -1){
                            tempPoss = translateToArrayPos(screenX, screenY);
                            System.out.println("---------------------------");
                            System.out.println("Piece selected: "+tempPoss[1] + ", " +  tempPoss[0]);
                        }
                        else if(gameLoop.isLegalPiece(tempPoss[1], tempPoss[0])){
                            tempPoss2 = translateToArrayPos(screenX, screenY);
                            System.out.println("Future position selected: "+tempPoss2[1] + ", " +  tempPoss2[0]);
                            if(gameLoop.isLegalMove(tempPoss[1], tempPoss[0], tempPoss2[1], tempPoss2[0])){
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
                                System.out.println("Please try another cell as destination!");
                            }
                        }
                        else{
                            System.out.println("Try a new initial piece!");
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
            System.out.println("The game is over! " + winner + " won.");
        }
        
        ScreenUtils.clear(1, 1, 1, 1);
        game.batch.begin();
        game.batch.draw(textureUtils.notation, textureUtils.spriteNotation.getX(), textureUtils.spriteNotation.getY(), textureUtils.spriteNotation.getWidth(), textureUtils.spriteNotation.getHeight());
        game.batch.draw(textureUtils.board, textureUtils.spriteBoard.getX(), textureUtils.spriteBoard.getY(), textureUtils.spriteBoard.getWidth(), textureUtils.spriteBoard.getHeight());
        if(x1 != 0){
            //game.batch.draw(textureUtils.highlight, x1, y1, textureUtils.spriteHighlight.getWidth(), textureUtils.spriteHighlight.getHeight());
        }

        for (int i = 0; i < textureUtils.pieceStorage.length; i++) {
            for (int j = 0; j < textureUtils.pieceStorage[0].length; j++) {
                if(textureUtils.pieceStorage[i][j] != null){
                    game.batch.draw(textureUtils.pieceStorage[i][j], textureUtils.pieceStorage[i][j].getX(), textureUtils.pieceStorage[i][j].getY(), textureUtils.pieceStorage[i][j].getWidth(), textureUtils.pieceStorage[i][j].getHeight());
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
    public void dispose() {
        textureUtils.backArrow.dispose();
        textureUtils.dice1.dispose();
    }
}