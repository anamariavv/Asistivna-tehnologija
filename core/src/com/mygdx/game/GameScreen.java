package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameScreen implements Screen {
    private final Matematko game;
    private Stage stage;

    public GameScreen(final Matematko game) {
        this.game = game;
        this.stage = new Stage(new StretchViewport(Matematko.W_WIDTH, Matematko.W_HEIGHT, game.camera));
    }

    public void update(float delta) {
        stage.act(delta);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        //fix this
        game.playerMatko.characterImg.setPosition(game.camera.viewportHeight/2, game.camera.viewportWidth/2);
        game.playerMatko.characterImg.setHeight(128);
        game.playerMatko.characterImg.setWidth(128);
        stage.addActor(game.playerMatko.characterImg);

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        //fix this
        TiledMap map = new TmxMapLoader().load(("Maps/Level 1.tmx"));
        OrthogonalTiledMapRenderer rend = new OrthogonalTiledMapRenderer(map);
        rend.setView(game.camera);
        rend.render();


        update(delta);

        stage.draw();
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

    }

    @Override
    public void dispose() {

    }
}
