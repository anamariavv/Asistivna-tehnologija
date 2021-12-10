package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

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

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        settingButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
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

        Gdx.input.setInputProcessor(stage);
        game.currentMusic.play();
        Texture titleTexture = new Texture(Gdx.files.internal("Fonts & skins/Matematko.png"));

        titleImage = new Image(titleTexture);
        titleImage.setPosition(stage.getWidth()/2 - 564/2, stage.getHeight()/2 + 300);
        titleImage.addAction(sequence(alpha(0f), parallel(moveBy(0,-100,3f), fadeIn(3f))));
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
    public void pause() { System.out.println("paused Mainmenu");}

    @Override
    public void resume() { System.out.println("unpasued Mainmenu");}

    @Override
    public void hide() { System.out.println("hidden Mainmenu");
        stage.clear();
    ;}

    @Override
    public void dispose() {
        stage.dispose();
    ;}
}
