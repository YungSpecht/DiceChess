package com.group4.dicechess.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameChoiceScreen implements  Screen{

    Texture backArrow;
    Sprite spriteArrow;
    Texture v1human;
    Sprite sv1human;
    Texture v1bot;
    Sprite sv1bot;
    TextureUtils textureUtils;
    DiceChessGame game;

    public GameChoiceScreen(DiceChessGame currentGame){
        this.game = currentGame;
        this.textureUtils = new TextureUtils();
        v1human = new Texture("FinalAssets/1v1human.png");
        backArrow = new Texture("FinalAssets/backArrow.png");
        v1bot = new Texture("FinalAssets/1v1bot.png");
        sv1human = new Sprite(v1human);
        sv1bot = new Sprite(v1bot);
        sv1bot.setSize(200, 43);
        sv1bot.setPosition(347, 230);
        sv1human.setSize(208, 48);
        sv1human.setPosition(685/2, 300);
        spriteArrow = new Sprite(backArrow);
        spriteArrow.setPosition(10, 550);
        spriteArrow.setSize(50, 50);
    }

    @Override
    public void show() {}


    @Override
    public void render(float delta) {
        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if(screenX >= 15 && screenX <= 45 && screenY >= 10 && screenY <= 40){
                    game.setScreen(new MenuScreen(game));
                    Gdx.input.setInputProcessor(null);
                }
                if(screenX >= 341 && screenX <= 550 && screenY >= 252 && screenY <= 301){
                    game.setScreen(new GameScreen(game, 0));
                    Gdx.input.setInputProcessor(null);
                }
                if(screenX >= 348 && screenX <= 546 && screenY >= 328 && screenY <= 369){
                    game.setScreen(new BotsScreen(game));
                    Gdx.input.setInputProcessor(null);
                }
                return true;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                if(screenX >= 341 && screenX <= 550 && screenY >= 252 && screenY <= 301){
                    sv1human.setPosition(681/2, 298);
                    sv1human.setSize(212, 52);
                } else{
                    sv1human.setPosition(685/2, 300);
                    sv1human.setSize(208, 48);
                }
                if(screenX >= 348 && screenX <= 546 && screenY >= 328 && screenY <= 369){
                    sv1bot.setPosition(343, 228);
                    sv1bot.setSize(204, 47);
                } else{
                    sv1bot.setPosition(347, 230);
                    sv1bot.setSize(200, 43);
                }
                return true;
            }
        });
        ScreenUtils.clear(1, 1, 1, 1);
        game.batch.begin();
        game.batch.draw(sv1human, sv1human.getX(), sv1human.getY(), sv1human.getWidth(), sv1human.getHeight());
        game.batch.draw(sv1bot, sv1bot.getX(), sv1bot.getY(), sv1bot.getWidth(), sv1bot.getHeight());
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