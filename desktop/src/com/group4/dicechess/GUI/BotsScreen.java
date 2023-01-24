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

    Texture MCTSHybrid;
    Sprite sMCTSHybrid;
    Texture Hybrid;
    Sprite sHybrid;

    public BotsScreen(DiceChessGame currentGame){
        this.game = currentGame;
        this.textureUtils = new TextureUtils();

        MCTSHybrid = new Texture("FinalAssets/MH.png");
        sMCTSHybrid = new Sprite(MCTSHybrid);
        sMCTSHybrid.setSize(245, 45);
        sMCTSHybrid.setPosition(405, 260);
        Hybrid = new Texture("FinalAssets/EH.png");
        sHybrid = new Sprite(Hybrid);
        sHybrid.setSize(295, 45);
        sHybrid.setPosition(403, 180);
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
        sexpectimaxb.setPosition(190, 180);
        snnb.setSize(245, 47);
        snnb.setPosition(330, 100);
        srandomb.setPosition(360, 420);
        sgreedyb.setPosition(365, 340);
        smctsb.setSize(150, 45);
        smctsb.setPosition(245, 260);
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
                System.out.println(" x: " +screenX+ " y: " + screenY);
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
                if(screenX >= 245 && screenX <= 394 && screenY >= 296 && screenY <= 341){
                    game.setScreen(new GameScreen(game, 4));
                    Gdx.input.setInputProcessor(null);
                }

                // Expectimax
                if(screenX >= 191 && screenX <= 390 && screenY >= 376 && screenY <= 420){
                    game.setScreen(new GameScreen(game, 3));
                    Gdx.input.setInputProcessor(null);
                }

                // MCTS Hybrid
                if(screenX >= 406 && screenX <= 650 && screenY >= 296 && screenY <= 341){
                    game.setScreen(new GameScreen(game, 6));
                    Gdx.input.setInputProcessor(null);
                }
                // Expectimax Hybrid
                if(screenX >= 403 && screenX <= 699 && screenY >= 376 && screenY <= 420){
                    //game.setScreen(new GameScreen(game, 3));
                    //Gdx.input.setInputProcessor(null);
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
                if(screenX >= 245 && screenX <= 394 && screenY >= 296 && screenY <= 341){
                    smctsb.setSize(154, 49);
                    smctsb.setPosition(243, 258);
                } else{
                    smctsb.setSize(150, 45);
                    smctsb.setPosition(245, 260);
                }
                if(screenX >= 191 && screenX <= 390 && screenY >= 376 && screenY <= 420){
                    sexpectimaxb.setSize(204, 49);
                    sexpectimaxb.setPosition(188, 178);
                } else{
                    sexpectimaxb.setSize(200, 45);
                    sexpectimaxb.setPosition(190, 180);
                }
                if(screenX >= 406 && screenX <= 650 && screenY >= 296 && screenY <= 341){
                    sMCTSHybrid.setSize(249, 49);
                    sMCTSHybrid.setPosition(403, 258);
                } else{
                    sMCTSHybrid.setSize(245, 45);
                    sMCTSHybrid.setPosition(405, 260);
                }
                if(screenX >= 403 && screenX <= 699 && screenY >= 376 && screenY <= 420){
                    sHybrid.setSize(299, 49);
                    sHybrid.setPosition(401, 178);
                } else{
                    sHybrid.setSize(295, 45);
                    sHybrid.setPosition(403, 180);
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
        //game.batch.draw(snnb, snnb.getX(), snnb.getY(), snnb.getWidth(), snnb.getHeight());
        game.batch.draw(sHybrid, sHybrid.getX(), sHybrid.getY(), sHybrid.getWidth(), sHybrid.getHeight());
        game.batch.draw(sMCTSHybrid, sMCTSHybrid.getX(), sMCTSHybrid.getY(), sMCTSHybrid.getWidth(), sMCTSHybrid.getHeight());
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