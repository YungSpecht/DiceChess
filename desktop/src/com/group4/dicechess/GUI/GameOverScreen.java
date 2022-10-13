package com.group4.dicechess.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOverScreen implements Screen, InputProcessor {

    Texture startButton;
    Texture exitButton;
    Texture wwon;
    Texture bwon;
    Texture settingsButton;
    Texture diceM;
    Sprite swwon;
    Sprite sbwon;
    Sprite spriteStart;
    Sprite spriteSettings;
    Sprite spriteExit;
    String winner;
    DiceChessGame game;

    public GameOverScreen(DiceChessGame currentGame, String winner){
        this.game = currentGame;
        this.winner = winner;
    }

    @Override
    public void show() {
        startButton = new Texture("FinalAssets/startF.png");
        exitButton = new Texture("FinalAssets/exitF.png");
        settingsButton = new Texture("FinalAssets/settingsF.png");
        diceM = new Texture("FinalAssets/menuP.jpg");
        wwon = new Texture("TestAssets/wwon.png");
        bwon = new Texture("TestAssets/bwon.png");
        swwon = new Sprite(wwon);
        swwon.setPosition(350, 200);
        swwon.setSize(200, 50);
        sbwon = new Sprite(bwon);
        sbwon.setPosition(350, 200);
        sbwon.setSize(200, 50);
        spriteExit = new Sprite(exitButton);
        spriteExit.setPosition(350, 120);
        spriteExit.setSize(200, 50);
        spriteStart = new Sprite(startButton);
        spriteStart.setPosition(350, 280);
        spriteStart.setSize(200, 50);
        Gdx.input.setInputProcessor(this);
    }
    boolean ac = false;
    BitmapFont font = new BitmapFont();

    @Override
    public void render(float delta) {
        Gdx.input.setInputProcessor(this);
        game.batch.begin();
        ScreenUtils.clear(1, 1, 1, 1);
        game.batch.draw(spriteStart, spriteStart.getX(), spriteStart.getY(), spriteStart.getWidth(), spriteStart.getHeight());
        game.batch.draw(spriteExit,spriteExit.getX(), spriteExit.getY(), spriteExit.getWidth(), spriteExit.getHeight());
        if(this.winner.equals("Black")){
            game.batch.draw(swwon,swwon.getX()-60, swwon.getY(), swwon.getWidth()+135, swwon.getHeight());
        } else if(this.winner.equals("White")){
            game.batch.draw(sbwon,sbwon.getX()-60, sbwon.getY(), sbwon.getWidth()+135, sbwon.getHeight());
        }
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
            game.setScreen(new MenuScreen(game));
            Gdx.input.setInputProcessor(null);
        }
        if(screenX >= 350 && screenX <= 550 && screenY >= 427 && screenY <= 480){
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