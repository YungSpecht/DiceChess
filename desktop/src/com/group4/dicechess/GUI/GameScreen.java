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
                return true;
            }
        });
        ScreenUtils.clear(1, 1, 1, 1);
        game.batch.begin();
        game.batch.draw(textureUtils.board, textureUtils.spriteBoard.getX(), textureUtils.spriteBoard.getY(), textureUtils.spriteBoard.getWidth(), textureUtils.spriteBoard.getHeight());
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