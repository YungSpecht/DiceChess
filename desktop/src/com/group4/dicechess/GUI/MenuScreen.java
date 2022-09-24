package com.group4.dicechess.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;

public class MenuScreen implements Screen, InputProcessor {

    Texture startButton;
    Texture exitButton;
    Texture settingsButton;
    Texture diceM;
    Sprite spriteStart;
    Sprite spriteSettings;
    Sprite spriteExit;
    DiceChessGame game;

    public MenuScreen(DiceChessGame currentGame){
        this.game = currentGame;
    }

    @Override
    public void show() {
        startButton = new Texture("FinalAssets/startF.png");
        exitButton = new Texture("FinalAssets/exitF.png");
        settingsButton = new Texture("FinalAssets/settingsF.png");
        diceM = new Texture("FinalAssets/menuP.jpg");

        spriteExit = new Sprite(exitButton);
        spriteExit.setPosition(350, 120);
        spriteExit.setSize(200, 50);

        spriteStart = new Sprite(startButton);
        spriteStart.setPosition(350, 280);
        spriteStart.setSize(200, 50);

        spriteSettings = new Sprite(settingsButton);
        spriteSettings.setPosition(350, 200);
        spriteSettings.setSize(200, 50);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.input.setInputProcessor(this);
        game.batch.begin();
        ScreenUtils.clear(1, 1, 1, 1);
        game.batch.draw(spriteStart, spriteStart.getX(), spriteStart.getY(), spriteStart.getWidth(), spriteStart.getHeight());
        game.batch.draw(spriteSettings,spriteSettings.getX(), spriteSettings.getY(), spriteSettings.getWidth(), spriteSettings.getHeight());
        game.batch.draw(spriteExit,spriteExit.getX(), spriteExit.getY(), spriteExit.getWidth(), spriteExit.getHeight());
        game.batch.draw(diceM, 350, 365, 200, 200);
        game.batch.end();
    }

    @Override
    public void dispose() {
        game.batch.dispose();
        startButton.dispose();
        exitButton.dispose();
        settingsButton.dispose();
        diceM.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(screenX >= 350 && screenX <= 550 && screenY >= 270 && screenY <= 320){
            game.setScreen(new GameScreen(game));
            Gdx.input.setInputProcessor(null);
        }
        if(screenX >= 350 && screenX <= 550 && screenY >= 345 && screenY <= 400){
            game.setScreen(new SettingScreen(game));
            Gdx.input.setInputProcessor(null);
        }
        if(screenX >= 350 && screenX <= 550 && screenY >= 427 && screenY <= 480){
            System.out.println("here");
            Gdx.app.exit();
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        if(screenX >= 350 && screenX <= 550 && screenY >= 270 && screenY <= 320){
            spriteStart.setPosition(347, 277);
            spriteStart.setSize(204, 54);
        } else{
            spriteStart.setSize(200, 50);
            spriteStart.setPosition(350, 280);
        }

        if(screenX >= 350 && screenX <= 550 && screenY >= 345 && screenY <= 400){
            spriteSettings.setPosition(347, 197);
            spriteSettings.setSize(204, 54);
        } else{
            spriteSettings.setSize(200, 50);
            spriteSettings.setPosition(350, 200);
        }

        if(screenX >= 350 && screenX <= 550 && screenY >= 427 && screenY <= 480){
            spriteExit.setPosition(347, 117);
            spriteExit.setSize(204, 54);
        } else{
            spriteExit.setSize(200, 50);
            spriteExit.setPosition(350, 120);
        }
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {return false;}
    @Override
    public boolean keyUp(int keycode) {return false;}
    @Override
    public boolean keyTyped(char character) {return false;}
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {return false;}
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {return false;}
    @Override
    public boolean scrolled(float amountX, float amountY) {return false;}
    @Override
    public void resize(int width, int height) {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
}