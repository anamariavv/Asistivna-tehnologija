package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
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
    }

    public void update(float delta) {
        stage.act(delta);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        //stage.addActor(game.playerMatko.characterImg);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        elapsed += Gdx.graphics.getDeltaTime();

        game.mapRenderer.setView(game.camera);
        game.mapRenderer.render();

        game.batch.begin();

        game.batch.draw(game.playerMatko.walk_right.getKeyFrame(elapsed), game.playerMatko.characterRect.x, game.playerMatko.characterRect.y, 128,128);

        //movement->play currently teleports...
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            System.out.println("Click");

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
