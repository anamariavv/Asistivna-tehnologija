package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class PauseScreen implements Screen {
    private final Matematko game;
    private Stage stage;
    private Table table;
    private TextButton resumeButton, mainMenuButton;
    private Label pauseLabel;

    public PauseScreen(final  Matematko game) {
        this.game = game;
        this.stage = new Stage(new StretchViewport(Matematko.W_WIDTH, Matematko.W_HEIGHT, new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())));
    }

    private void createUI() {
        pauseLabel = new Label("Igrica je pauzirana", game.skin, "default");
        resumeButton = new TextButton("Nastavi", game.skin, "default");
        mainMenuButton = new TextButton("Glavni Izbornik", game.skin, "default");

        pauseLabel.setFontScale(1.2f);

        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.sfx.get(0).play(game.sfxVolume);
                game.setScreen(game.gameScreen);
            }
        });

        mainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.sfx.get(0).play(game.sfxVolume);
                game.currentMusic.stop();
                game.setScreen(game.mainScreen);
            }
        });

        table = new Table();
        table.setDebug(false);
        table.setPosition(Matematko.W_WIDTH/2 - table.getHeight(),Matematko.W_HEIGHT/2 - table.getWidth());
        table.defaults().minWidth(350).minHeight(80).padBottom(100);
        table.add(pauseLabel).colspan(2);
        table.row();
        table.add(resumeButton).padRight(30).padLeft(65);
        table.row();
        table.add(mainMenuButton).padRight(30).padLeft(65);
        stage.addActor(table);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        createUI();
    }

    public void update(float delta) {
        stage.act(delta);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        update(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false); //false is for centring camera
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
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
