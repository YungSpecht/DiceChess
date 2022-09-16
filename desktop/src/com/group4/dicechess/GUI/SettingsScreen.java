package com.group4.dicechess.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class SettingsScreen implements Screen {

    Texture backArrow;
    Sprite spriteArrow;
    DiceChessGame game;

    public SettingsScreen(DiceChessGame currentGame){
        this.game = currentGame;
    }

    @Override
    public void show() {
        backArrow = new Texture("backArrow.png");
        spriteArrow = new Sprite(backArrow);
        spriteArrow.setPosition(10, 550);
        spriteArrow.setSize(50, 50);
    }

    @Override
    public void render(float delta) {
        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if(screenX >= 15 && screenX <= 45 && screenY >= 10 && screenY <= 40){
                    game.setScreen(new MenuScreen(game));
                    Gdx.input.setInputProcessor(null);
                }
                return true;
            }
        });
        ScreenUtils.clear(1, 1, 1, 1);
        game.batch.begin();
        game.batch.draw(spriteArrow, spriteArrow.getX(), spriteArrow.getY(), spriteArrow.getWidth(), spriteArrow.getHeight());
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
    @Override
    public void dispose() {backArrow.dispose();}
}