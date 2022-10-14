package com.group4.dicechess.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;

public class SettingsScreen implements Screen {

    Texture backArrow;
    Sprite spriteArrow;
    TextureUtils textureUtils;
    DiceChessGame game;

    public SettingsScreen(DiceChessGame currentGame){
        this.game = currentGame;
        this.textureUtils = new TextureUtils();
    }

    @Override
    public void show() {
        backArrow = new Texture("FinalAssets/backArrow.png");
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
                if(screenX >= 350 && screenX <= 550 && screenY >= 310 && screenY <= 360){
                    System.out.println("Working on Help...");
                    //game.setScreen(new HelpScreen(game));
                    //Gdx.input.setInputProcessor(null);
                }
                if(screenX >= 350 && screenX <= 550 && screenY >= 400 && screenY <= 450){
                    System.out.println("Working on sound...");
                }
                if(screenX >= 350 && screenX <= 550 && screenY >= 220 && screenY <= 270){
                    System.out.println("Working on bots...");
                }
                return true;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                if(screenX >= 350 && screenX <= 550 && screenY >= 310 && screenY <= 360){
                    textureUtils.spriteHelp.setPosition(347, 237);
                    textureUtils.spriteHelp.setSize(204, 54);
                } else{
                    textureUtils.spriteHelp.setPosition(350, 240);
                    textureUtils.spriteHelp.setSize(200, 50);
                }
                if(screenX >= 350 && screenX <= 550 && screenY >= 400 && screenY <= 450){
                    textureUtils.spriteSound.setPosition(347, 147);
                    textureUtils.spriteSound.setSize(204, 54);
                } else{
                    textureUtils.spriteSound.setPosition(350, 150);
                    textureUtils.spriteSound.setSize(200, 50);
                }
                if(screenX >= 350 && screenX <= 550 && screenY >= 220 && screenY <= 270){
                    textureUtils.spriteBots.setPosition(347, 327);
                    textureUtils.spriteBots.setSize(204, 54);
                } else{
                    textureUtils.spriteBots.setPosition(350, 330);
                    textureUtils.spriteBots.setSize(200, 50);
                }
                return true;
            }
        });
        ScreenUtils.clear(1, 1, 1, 1);
        game.batch.begin();
        game.batch.draw(textureUtils.bots, textureUtils.spriteBots.getX(), textureUtils.spriteBots.getY(), textureUtils.spriteBots.getWidth(), textureUtils.spriteBots.getHeight());
        game.batch.draw(textureUtils.help, textureUtils.spriteHelp.getX(), textureUtils.spriteHelp.getY(), textureUtils.spriteHelp.getWidth(), textureUtils.spriteHelp.getHeight());
        game.batch.draw(textureUtils.sound, textureUtils.spriteSound.getX(), textureUtils.spriteSound.getY(), textureUtils.spriteSound.getWidth(), textureUtils.spriteSound.getHeight());
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