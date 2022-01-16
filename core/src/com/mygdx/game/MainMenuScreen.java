package com.mygdx.game;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class MainMenuScreen implements Screen {
    private final Matematko game;
    private Stage stage;
    private Image titleImage;
    private TextButton playButton;
    private TextButton exitButton;
    private TextButton settingButton;
    private Table table;

    public MainMenuScreen(final Matematko game) {
        this.game = game;
        this.stage = new Stage(new StretchViewport(Matematko.W_WIDTH, Matematko.W_HEIGHT, game.camera));
    }

    private void createButtons() {
        playButton = new TextButton("Igraj", game.skin, "default");
        settingButton = new TextButton("Postavke", game.skin, "default");
        exitButton = new TextButton("Izlaz", game.skin, "default");

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                 game.sfx.get(1).play(0.3f);
                 stage.addAction(fadeOut(1f)); //doesn't work, dk why??
                 game.currentMusic.stop();
                 game.setScreen(game.gameScreen);
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.sfx.get(0).play(game.sfxVolume);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Gdx.app.exit();
            }
        });

        settingButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.sfx.get(0).play(game.sfxVolume);
                game.setScreen(game.optionsScreen);
            }
        });

        table = new Table();
        table.setPosition(Matematko.W_WIDTH/2,Matematko.W_HEIGHT/2- 100);
        table.defaults().minWidth(300).minHeight(80).padBottom(50);
        table.add(playButton);
        table.row();
        table.add(settingButton);
        table.row();
        table.add(exitButton);

        table.addAction(sequence(alpha(0f), fadeIn(2f)));

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
    public void show() {
        game.camera.setToOrtho(false);
        Gdx.input.setInputProcessor(stage);
        game.currentMusic.play();
        Texture titleTexture = new Texture(Gdx.files.internal("Fonts & skins/Matematko.png"));

        titleImage = new Image(titleTexture);
        titleImage.setPosition(stage.getWidth()/2 - 564/2, stage.getHeight()/2 + 300);
        titleImage.addAction(sequence(alpha(0f), parallel(moveBy(0,-90,1.5f), fadeIn(2f))));
        game.playerMatko.characterImg.setPosition(Matematko.W_WIDTH/2+200, Matematko.W_HEIGHT/2-300);
        game.playerMatko.characterImg.setWidth(256);
        game.playerMatko.characterImg.setHeight(256);

        createButtons();

        stage.addActor(titleImage);
        stage.addActor(game.playerMatko.characterImg);
    }


    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false); //false is for centring camera
    ;}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        stage.clear();
    ;}

    @Override
    public void dispose() {
        stage.dispose();
    ;}
}
