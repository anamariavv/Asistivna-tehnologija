package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class OptionsScreen implements Screen {
    private final Matematko game;
    private Stage stage;
    private TextButton backButton;
    private Label musicLabel;
    private Label sfxLabel;
    private Slider musicSlider;
    private Slider sfxSlider;
    private Table table;

    public OptionsScreen(final Matematko game) {
        this.game = game;
        this.stage = new Stage(new StretchViewport(Matematko.W_WIDTH, Matematko.W_HEIGHT, game.camera));
    }

    private void createUI() {
        backButton = new TextButton("Povratak", game.skin, "default");
        musicLabel = new Label("Glasnoća glazbe", game.skin, "default");
        sfxLabel = new Label("Glasnoca efekata", game.skin, "default");
        musicSlider = new Slider(0.0f, 1.0f, 0.1f, false, game.skin, "default-horizontal");
        sfxSlider = new Slider(0.0f, 1.0f, 0.1f, false, game.skin, "default-horizontal");

        musicSlider.setValue(game.currentMusic.getVolume());
        musicSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.currentMusic.setVolume(musicSlider.getValue());
            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.mainScreen);
            }
        });

        table = new Table();
        table.setPosition(Matematko.W_WIDTH/2,Matematko.W_HEIGHT/2- 100);
        table.defaults().minWidth(350).minHeight(80).padBottom(50);
        table.add(musicLabel);
        table.add(musicSlider);
        table.row();
        table.add(sfxLabel);
        table.add(sfxSlider);
        table.row();
        table.add(backButton).colspan(2);

        stage.addActor(table);
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
    public void show() {
        Gdx.input.setInputProcessor(stage);

        createUI();
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
