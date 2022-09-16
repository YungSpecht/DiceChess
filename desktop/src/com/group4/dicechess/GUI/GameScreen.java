package com.group4.dicechess.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {

    DiceChessGame game;
    TextureUtils dice;
    public boolean rolled = false;
    public boolean rolledBefore = false;

    public GameScreen(DiceChessGame currentGame){
        this.game = currentGame;
        this.dice = new TextureUtils();
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
        if (rolled) {
            dice.Roll();
            rolledBefore = true;
        } else if (!rolledBefore) {
            game.batch.draw(dice.spriteDice1, dice.spriteDice1.getX(), dice.spriteDice1.getY(), dice.spriteDice1.getWidth(), dice.spriteDice1.getHeight());
        }
        rolled = false;
        if (dice.currentSide == 1) {
            game.batch.draw(dice.spriteDice1, dice.spriteDice1.getX(), dice.spriteDice1.getY(), dice.spriteDice1.getWidth(), dice.spriteDice1.getHeight());
        } else if (dice.currentSide == 2) {
            game.batch.draw(dice.spriteDice2, dice.spriteDice2.getX(), dice.spriteDice2.getY(), dice.spriteDice2.getWidth(), dice.spriteDice2.getHeight());
        } else if (dice.currentSide == 3) {
            game.batch.draw(dice.spriteDice3, dice.spriteDice3.getX(), dice.spriteDice3.getY(), dice.spriteDice3.getWidth(), dice.spriteDice3.getHeight());
        } else if (dice.currentSide == 4) {
            game.batch.draw(dice.spriteDice4, dice.spriteDice4.getX(), dice.spriteDice4.getY(), dice.spriteDice4.getWidth(), dice.spriteDice4.getHeight());
        } else if (dice.currentSide == 5) {
            game.batch.draw(dice.spriteDice5, dice.spriteDice5.getX(), dice.spriteDice5.getY(), dice.spriteDice5.getWidth(), dice.spriteDice5.getHeight());
        } else if (dice.currentSide == 6) {
            game.batch.draw(dice.spriteDice6, dice.spriteDice6.getX(), dice.spriteDice6.getY(), dice.spriteDice6.getWidth(), dice.spriteDice6.getHeight());
        }
        game.batch.draw(dice.spriteArrow, dice.spriteArrow.getX(), dice.spriteArrow.getY(), dice.spriteArrow.getWidth(), dice.spriteArrow.getHeight());
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
        dice.backArrow.dispose();
        dice.dice1.dispose();
    }
}