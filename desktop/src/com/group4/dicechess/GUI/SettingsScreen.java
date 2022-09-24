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
                return true;
            }
        });
        ScreenUtils.clear(1, 1, 1, 1);
        game.batch.begin();
        game.batch.draw(textureUtils.PVPInActive, 350,510, textureUtils.PVP_WIDTH,textureUtils.PVP_HEIGHT);
        game.batch.draw(textureUtils.PVAInActive, 350, 420, textureUtils.PVA_WIDTH, textureUtils.PVA_HEIGHT);
        game.batch.draw(textureUtils.AVAInActive, 350, 330, textureUtils.AVA_WIDTH, textureUtils.AVA_HEIGHT);
        game.batch.draw(textureUtils.HELPInActive, 350, 240, textureUtils.HELP_WIDTH, textureUtils.HELP_HEIGHT);
        game.batch.draw(textureUtils.SoundInActive, 350, 150, textureUtils.SOUND_WIDTH, textureUtils.SOUND_HEIGHT);
        game.batch.draw(textureUtils.noSoundInActive, 350, 60, textureUtils.NOSOUND_WIDTH, textureUtils.NOSOUND_HEIGHT);
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