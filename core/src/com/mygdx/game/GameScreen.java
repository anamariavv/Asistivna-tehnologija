package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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

    public void update(float delta) {
        stage.act(delta);
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

        game.batch.draw(game.playerMatko.walk_right.getKeyFrame(elapsed), game.playerMatko.characterRect.x, game.playerMatko.characterRect.y, 128,128);

        //movement
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            float destination_x = Gdx.input.getX();
            float destination_y = Gdx.input.getY();
            game.playerMatko.move(destination_x, destination_y);
        }

        game.batch.end();

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
