package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameScreen implements Screen {
    private final Matematko game;
    private Stage stage;
    private HUD hud;
    private SpriteBatch hudBatch;

    public GameScreen(final Matematko game) {
        this.game = game;
        stage = new Stage(new StretchViewport(Matematko.W_WIDTH, Matematko.W_HEIGHT, game.camera));
        MapManager.loadMap("Maps/Level 1.tmx");
        hudBatch = new SpriteBatch();
        hud = new HUD(game.batch, 0, game);
    }

    @Override
    public void show() {
        game.currentMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/music_2.wav"));
        game.currentMusic.setVolume(game.musicVolume);
        game.currentMusic.play();
        game.multiplexer.addProcessor(hud.stage);
        game.multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(game.multiplexer);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        MapManager.renderMap(game.camera);

        game.batch.setProjectionMatrix(game.camera.combined);

        game.batch.begin();
        //Todo player still moves even thought HUD button was pressed -> fix this
        if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
            game.playerMatko.findPath(game.camera.unproject(new Vector3(Gdx.input.getX(),Gdx.input.getY(), 0)));
        }
        game.playerMatko.update(delta);
        cameraUpdate();
        game.batch.draw(game.playerMatko.characterTex, game.playerMatko.currentPosition.x, game.playerMatko.currentPosition.y, 128,128);
        game.batch.end();

        stage.act(delta);
        stage.draw();

        //Todo udate hud coins based on player coin count
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.act(delta);
        hud.stage.draw();

    }

    private void cameraUpdate() {
       /* if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            game.camera.translate(0, 10, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            game.camera.translate(0, -10, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            game.camera.translate(-10, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            game.camera.translate(10, 0, 0);
        }*/

        CameraTools.lerpToPlayer(game.camera, game.playerMatko.currentPosition);
        float startX = game.camera.viewportWidth / 2;
        float startY = game.camera.viewportHeight / 2;
        CameraTools.boundary(game.camera, startX, startY, MapManager.lvlTileWidth * MapManager.tilePixelWidth - startX*2,
                MapManager.lvlTileHeight * MapManager.tilePixelHeight - startY*2);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        stage.clear();
        game.currentMusic.pause();
    }

    @Override
    public void dispose() {
        stage.dispose();
        hud.dispose();
    }
}
