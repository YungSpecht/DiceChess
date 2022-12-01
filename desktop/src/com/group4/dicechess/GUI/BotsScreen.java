package com.group4.dicechess.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;

public class BotsScreen implements Screen {

    Texture backArrow;
    Sprite spriteArrow;
    TextureUtils textureUtils;
    DiceChessGame game;
    Texture greedyb;
    Sprite sgreedyb;
    Texture randomb;
    Sprite srandomb;
    Texture mctsb;
    Sprite smctsb;
    Texture expectimaxb;
    Sprite sexpectimaxb;
    Texture nnb;
    Sprite snnb;

    public BotsScreen(DiceChessGame currentGame){
        this.game = currentGame;
        this.textureUtils = new TextureUtils();
        greedyb = new Texture("FinalAssets/fre.png");
        randomb = new Texture("FinalAssets/ran.png");
        mctsb = new Texture("FinalAssets/mc.png");
        expectimaxb = new Texture("FinalAssets/ex.png");
        nnb = new Texture("FinalAssets/nn.png");
        sgreedyb = new Sprite(greedyb);
        srandomb = new Sprite(randomb);
        smctsb = new Sprite(mctsb);
        sexpectimaxb = new Sprite(expectimaxb);
        snnb = new Sprite(nnb);
        srandomb.setSize(180, 43);
        sgreedyb.setSize(170, 45);
        sexpectimaxb.setSize(200, 45);
        sexpectimaxb.setPosition(353, 180);
        snnb.setSize(245, 47);
        snnb.setPosition(330, 100);
        srandomb.setPosition(360, 420);
        sgreedyb.setPosition(365, 340);
        smctsb.setSize(150, 45);
        smctsb.setPosition(375, 260);
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
                    game.setScreen(new GameChoiceScreen(game));
                    Gdx.input.setInputProcessor(null);
                }
                // Random
                if(screenX >= 360 && screenX <= 539 && screenY >= 138 && screenY <= 180){
                    game.setScreen(new GameScreen(game, 1));
                    Gdx.input.setInputProcessor(null);
                }
                // Greedy
                if(screenX >= 365 && screenX <= 535 && screenY >= 216 && screenY <= 260){
                    game.setScreen(new GameScreen(game, 2));
                    Gdx.input.setInputProcessor(null);
                }
                // MCTS
                if(screenX >= 375 && screenX <= 523 && screenY >= 295 && screenY <= 339){
                    //game.setScreen(new GameChoiceScreen(game));
                    //Gdx.input.setInputProcessor(null);
                    System.out.println("Working on mcts bot");
                }
                // Expectimax
                if(screenX >= 353 && screenX <= 553 && screenY >= 375 && screenY <= 420){
                    //game.setScreen(new GameChoiceScreen(game));
                    //Gdx.input.setInputProcessor(null);
                    System.out.println("Working on Expectimax");
                }
                // Neural network
                if(screenX >= 327 && screenX <= 572 && screenY >= 453 && screenY <= 500){
                    //game.setScreen(new GameChoiceScreen(game));
                    //Gdx.input.setInputProcessor(null);
                    System.out.println("Working on Neural network");
                }
                return true;
            }
            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                if(screenX >= 360 && screenX <= 539 && screenY >= 138 && screenY <= 180){
                    srandomb.setSize(184, 47);
                    srandomb.setPosition(358, 418);
                } else{
                    srandomb.setSize(180, 43);
                    srandomb.setPosition(360, 420);
                }
                if(screenX >= 365 && screenX <= 535 && screenY >= 216 && screenY <= 260){
                    sgreedyb.setSize(174, 49);
                    sgreedyb.setPosition(363, 338);
                } else{
                    sgreedyb.setSize(170, 45);
                    sgreedyb.setPosition(365, 340);
                }
                if(screenX >= 375 && screenX <= 523 && screenY >= 295 && screenY <= 339){
                    smctsb.setSize(154, 49);
                    smctsb.setPosition(373, 258);
                } else{
                    smctsb.setSize(150, 45);
                    smctsb.setPosition(375, 260);
                }
                if(screenX >= 353 && screenX <= 553 && screenY >= 375 && screenY <= 420){
                    sexpectimaxb.setSize(204, 49);
                    sexpectimaxb.setPosition(351, 178);
                } else{
                    sexpectimaxb.setSize(200, 45);
                    sexpectimaxb.setPosition(353, 180);
                }
                if(screenX >= 327 && screenX <= 572 && screenY >= 453 && screenY <= 500){
                    snnb.setSize(249, 51);
                    snnb.setPosition(328, 98);
                } else{
                    snnb.setSize(245, 47);
                    snnb.setPosition(330, 100);
                }
                return true;
            }
        });
        ScreenUtils.clear(1, 1, 1, 1);
        game.batch.begin();
        game.batch.draw(sgreedyb, sgreedyb.getX(), sgreedyb.getY(), sgreedyb.getWidth(), sgreedyb.getHeight());
        game.batch.draw(srandomb, srandomb.getX(), srandomb.getY(), srandomb.getWidth(), srandomb.getHeight());
        game.batch.draw(smctsb, smctsb.getX(), smctsb.getY(), smctsb.getWidth(), smctsb.getHeight());
        game.batch.draw(sexpectimaxb, sexpectimaxb.getX(), sexpectimaxb.getY(), sexpectimaxb.getWidth(), sexpectimaxb.getHeight());
        game.batch.draw(snnb, snnb.getX(), snnb.getY(), snnb.getWidth(), snnb.getHeight());
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