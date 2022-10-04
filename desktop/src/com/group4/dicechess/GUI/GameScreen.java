package com.group4.dicechess.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {

    DiceChessGame game;
    TextureUtils textureUtils;
    public boolean rolled = false;
    public boolean rolledBefore = false;

    public GameScreen(DiceChessGame currentGame){
        this.game = currentGame;
        this.textureUtils = new TextureUtils();
    }

    public int[] translateToArrayPos(int x, int y){
        int [] tempPos  = new int[2];
        int [] xPos = {105, 158, 211, 264, 317, 370, 423, 476, 529};
        int [] yPos = {523, 470, 417, 364, 311, 258, 205, 152, 99};

        for(int i = 0; i < xPos.length - 1; i++){
            if(x > xPos[i] && x < xPos[i+1]){
                tempPos[0] = i;
                x1 = xPos[i] -40;

            }
        }
        for(int i = 0; i < yPos.length - 1; i++){
            if(y < yPos[i] && y > yPos[i+1]){
                tempPos[1] = i;
                y1 = yPos[i+1] + 10;
                //System.out.println(y1);
            }
        }
        System.out.println(tempPos[0]);
        System.out.println(tempPos[1]);
        return tempPos;
    }

    public int x1 = 0;
    public  int y1 = 0;
    @Override
    public void render(float delta) {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (screenX >= 15 && screenX <= 45 && screenY >= 10 && screenY <= 40) {
                    game.setScreen(new MenuScreen(game));
                    Gdx.input.setInputProcessor(null);
                }
                if (screenX >= 710 && screenX <= 785 && screenY >= 75 && screenY <= 147) {
                    System.out.println("Rolling dice..");
                    rolled = true;
                }
                //System.out.println("X: " + screenX);
                //System.out.println("Y: " + screenY);
                if(screenX >= 105 && screenX <= 529 && screenY <= 523 && screenY >= 99){
                    translateToArrayPos(screenX, screenY);
                }
                return true;
            }
        });
        ScreenUtils.clear(1, 1, 1, 1);
        game.batch.begin();
        game.batch.draw(textureUtils.notation, textureUtils.spriteNotation.getX(), textureUtils.spriteNotation.getY(), textureUtils.spriteNotation.getWidth(), textureUtils.spriteNotation.getHeight());
        game.batch.draw(textureUtils.board, textureUtils.spriteBoard.getX(), textureUtils.spriteBoard.getY(), textureUtils.spriteBoard.getWidth(), textureUtils.spriteBoard.getHeight());
        if(x1 != 0){
            //game.batch.draw(textureUtils.highlight, x1, y1, textureUtils.spriteHighlight.getWidth(), textureUtils.spriteHighlight.getHeight());
        }

        game.batch.draw(textureUtils.pawn, textureUtils.spawn.getX(), textureUtils.spawn.getY(), textureUtils.spawn.getWidth(), textureUtils.spawn.getHeight());
        game.batch.draw(textureUtils.pawn, textureUtils.spawn.getX()+55, textureUtils.spawn.getY(), textureUtils.spawn.getWidth(), textureUtils.spawn.getHeight());
        game.batch.draw(textureUtils.pawn, textureUtils.spawn.getX()+110, textureUtils.spawn.getY(), textureUtils.spawn.getWidth(), textureUtils.spawn.getHeight());
        game.batch.draw(textureUtils.pawn, textureUtils.spawn.getX()+163, textureUtils.spawn.getY(), textureUtils.spawn.getWidth(), textureUtils.spawn.getHeight());
        game.batch.draw(textureUtils.pawn, textureUtils.spawn.getX()+218, textureUtils.spawn.getY(), textureUtils.spawn.getWidth(), textureUtils.spawn.getHeight());
        game.batch.draw(textureUtils.pawn, textureUtils.spawn.getX()+271, textureUtils.spawn.getY(), textureUtils.spawn.getWidth(), textureUtils.spawn.getHeight());
        game.batch.draw(textureUtils.pawn, textureUtils.spawn.getX()+325, textureUtils.spawn.getY(), textureUtils.spawn.getWidth(), textureUtils.spawn.getHeight());
        game.batch.draw(textureUtils.pawn, textureUtils.spawn.getX()+379, textureUtils.spawn.getY(), textureUtils.spawn.getWidth(), textureUtils.spawn.getHeight());
        game.batch.draw(textureUtils.pawnBlack, textureUtils.spawn.getX()+2, textureUtils.spawn.getY()+265, textureUtils.spawn.getWidth(), textureUtils.spawn.getHeight());
        game.batch.draw(textureUtils.pawnBlack, textureUtils.spawn.getX()+58, textureUtils.spawn.getY() +265, textureUtils.spawn.getWidth(), textureUtils.spawn.getHeight());
        game.batch.draw(textureUtils.pawnBlack, textureUtils.spawn.getX()+112, textureUtils.spawn.getY() +265, textureUtils.spawn.getWidth(), textureUtils.spawn.getHeight());
        game.batch.draw(textureUtils.pawnBlack, textureUtils.spawn.getX()+165, textureUtils.spawn.getY() +265, textureUtils.spawn.getWidth(), textureUtils.spawn.getHeight());
        game.batch.draw(textureUtils.pawnBlack, textureUtils.spawn.getX()+220, textureUtils.spawn.getY() +265, textureUtils.spawn.getWidth(), textureUtils.spawn.getHeight());
        game.batch.draw(textureUtils.pawnBlack, textureUtils.spawn.getX()+273, textureUtils.spawn.getY() +265, textureUtils.spawn.getWidth(), textureUtils.spawn.getHeight());
        game.batch.draw(textureUtils.pawnBlack, textureUtils.spawn.getX()+327, textureUtils.spawn.getY() +265, textureUtils.spawn.getWidth(), textureUtils.spawn.getHeight());
        game.batch.draw(textureUtils.pawnBlack, textureUtils.spawn.getX()+381, textureUtils.spawn.getY() +265, textureUtils.spawn.getWidth(), textureUtils.spawn.getHeight());
        game.batch.draw(textureUtils.rook, textureUtils.srook.getX(), textureUtils.srook.getY() , textureUtils.srook.getWidth(), textureUtils.srook.getHeight());
        game.batch.draw(textureUtils.rook, textureUtils.srook.getX()-379, textureUtils.srook.getY() , textureUtils.srook.getWidth(), textureUtils.srook.getHeight());
        game.batch.draw(textureUtils.rookBlack, textureUtils.srookBlack.getX(), textureUtils.srookBlack.getY(), textureUtils.srookBlack.getWidth(), textureUtils.srookBlack.getHeight());
        game.batch.draw(textureUtils.rookBlack, textureUtils.srookBlack.getX()-379, textureUtils.srookBlack.getY(), textureUtils.srookBlack.getWidth(), textureUtils.srookBlack.getHeight());
        game.batch.draw(textureUtils.knight, textureUtils.sknight.getX(), textureUtils.sknight.getY(), textureUtils.sknight.getWidth(), textureUtils.sknight.getHeight());
        game.batch.draw(textureUtils.knightBlack, textureUtils.sknightBlack.getX(), textureUtils.sknightBlack.getY(), textureUtils.sknightBlack.getWidth(), textureUtils.sknightBlack.getHeight());
        game.batch.draw(textureUtils.knight, textureUtils.sknight.getX()-269, textureUtils.sknight.getY(), textureUtils.sknight.getWidth(), textureUtils.sknight.getHeight());
        game.batch.draw(textureUtils.knightBlack, textureUtils.sknightBlack.getX()-269, textureUtils.sknightBlack.getY(), textureUtils.sknightBlack.getWidth(), textureUtils.sknightBlack.getHeight());
        game.batch.draw(textureUtils.bishop, textureUtils.sbishop.getX(), textureUtils.sbishop.getY(), textureUtils.sbishop.getWidth(), textureUtils.sbishop.getHeight());
        game.batch.draw(textureUtils.bishop, textureUtils.sbishop.getX()-162, textureUtils.sbishop.getY(), textureUtils.sbishop.getWidth(), textureUtils.sbishop.getHeight());
        game.batch.draw(textureUtils.bishopBlack, textureUtils.sbishopBlack.getX(), textureUtils.sbishopBlack.getY(), textureUtils.sbishopBlack.getWidth(), textureUtils.sbishopBlack.getHeight());
        game.batch.draw(textureUtils.bishopBlack, textureUtils.sbishopBlack.getX()-162, textureUtils.sbishopBlack.getY(), textureUtils.sbishopBlack.getWidth(), textureUtils.sbishopBlack.getHeight());
        game.batch.draw(textureUtils.king, textureUtils.sking.getX(), textureUtils.sking.getY(), textureUtils.sking.getWidth(), textureUtils.sking.getHeight());
        game.batch.draw(textureUtils.kingBlack, textureUtils.skingBlack.getX(), textureUtils.skingBlack.getY(), textureUtils.skingBlack.getWidth(), textureUtils.skingBlack.getHeight());
        game.batch.draw(textureUtils.queen, textureUtils.squeen.getX(), textureUtils.squeen.getY(), textureUtils.squeen.getWidth(), textureUtils.squeen.getHeight());
        game.batch.draw(textureUtils.queenBlack, textureUtils.squeenBlack.getX(), textureUtils.squeenBlack.getY(), textureUtils.squeenBlack.getWidth(), textureUtils.squeenBlack.getHeight());


        if (rolled) {
            textureUtils.Roll();
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