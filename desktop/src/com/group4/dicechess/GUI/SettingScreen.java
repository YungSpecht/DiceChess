package com.group4.dicechess.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;


public class SettingScreen implements Screen {

    private static final int PVA_WIDTH= 200;
    private static final int PVA_HEIGHT=80;
    private static final int PVP_WIDTH= 200;
    private static final int PVP_HEIGHT = 80;
    private static final int AVA_WIDTH= 200;
    private static final int AVA_HEIGHT = 80;
    private static final int HELP_WIDTH = 200;
    private static final int HELP_HEIGHT = 80;
    private static final int SOUND_WIDTH = 200;
    private static final int SOUND_HEIGHT = 80;
    private static final int NOSOUND_WIDTH = 200;
    private static final int NOSOUND_HEIGHT = 80;


    private static final int PVP_Y = 140;
    private static final int PVA_Y = 150;
    private static final int AVA_Y = 150;
    private static final int HELP_Y = 140;
    private static final int SOUND_Y = 150;
    private static final int NOSOUND_Y = 140;




    Texture PVAActive;
    Texture PVPActive;
    Texture AVAActive;
    Texture HELPActive;
    Texture SoundActive;
    Texture noSoundActive;

    Texture PVAInActive;
    Texture PVPInActive;
    Texture AVAInActive;
    Texture HELPInActive;
    Texture SoundInActive;
    Texture noSoundInActive;
    Texture NOSoundActive;



    final DiceChessGame game;
    public SettingScreen(DiceChessGame game) {
        this.game = game;
        PVPActive = new Texture("PVP.jpg");
        PVPInActive = new Texture("PVP.jpg");
        PVAActive=new Texture("PVA.jpg");
        PVAInActive=new Texture("PVA.jpg");
        HELPActive = new Texture("HELP.jpg");
        HELPInActive = new Texture("HELP.jpg");
        SoundActive= new Texture("SOUND.jpg");
        SoundInActive= new Texture("SOUND.jpg");
        AVAActive= new Texture("AVA.jpg");
        AVAInActive= new Texture("AVA.jpg");
        HELPActive=new Texture("HELP.jpg");
        HELPInActive=new Texture("HELP.jpg");
        noSoundInActive=new Texture("NOSOUND.jpg");
        NOSoundActive=new Texture("NOSOUND.jpg");

        final SettingScreen sett= this;

    }

    @Override
    public void show() {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(192 / 255f, 192 / 255f, 192 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
//PLAYER VERSUS PLAYER

        game.batch.draw(PVPInActive, 140,510, PVP_WIDTH,PVP_HEIGHT);

//PLAYER VERSUS AI

        game.batch.draw(PVAInActive, 140, 420, PVA_WIDTH, PVA_HEIGHT);

//AI VERSUS AI
        game.batch.draw(AVAInActive, 140, 330, AVA_WIDTH, AVA_HEIGHT);


//HELP
        game.batch.draw(HELPInActive, 140, 240, HELP_WIDTH, HELP_HEIGHT);



//SOUND
        game.batch.draw(SoundInActive, 140, 150, SOUND_WIDTH, SOUND_HEIGHT);
//NO SOUND
        game.batch.draw(noSoundInActive, 140, 60, NOSOUND_WIDTH, NOSOUND_HEIGHT);

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }


}

