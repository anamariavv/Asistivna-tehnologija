package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameScreen implements Screen {
    private final Matematko game;
    private Stage stage;
    float elapsed;

    public GameScreen(final Matematko game) {
        this.game = game;
        this.stage = new Stage(new StretchViewport(Matematko.W_WIDTH, Matematko.W_HEIGHT, game.camera));
        MapManager.loadMap("Maps/Level 1.tmx");
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        elapsed += Gdx.graphics.getDeltaTime();

        MapManager.renderMap(game.camera);

        game.batch.begin();

        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            game.playerMatko.findPath(game.camera.unproject(new Vector3(Gdx.input.getX(),Gdx.input.getY(), 0)));
        }
        game.playerMatko.update(elapsed);
        game.batch.draw(game.playerMatko.walk_right.getKeyFrame(elapsed), game.playerMatko.currentPosition.x, game.playerMatko.currentPosition.y, 128,128);

        game.batch.end();

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
