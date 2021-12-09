package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class MainMenuScreen implements Screen {
    private final Matematko game;
    private Stage stage;
    private Image titleImage;
    private Skin skin;
    private TextButton playButton;
    private TextButton exitButton;
    private TextButton settingButton;
    private Table table;

    public MainMenuScreen(final Matematko game) {
        this.game = game;
        this.stage = new Stage(new StretchViewport(Matematko.W_WIDTH, Matematko.W_HEIGHT, game.camera));
        this.skin = new Skin();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        update(delta);

        stage.draw();
    }

    public void update(float delta) {
        stage.act(delta);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        Texture titleTexture = new Texture(Gdx.files.internal("Fonts & skins/Matematko.png"));

        titleImage = new Image(titleTexture);
        titleImage.setPosition(stage.getWidth()/2 - 564/2, stage.getHeight()/2 + 300);
        titleImage.addAction(sequence(alpha(0f), parallel(moveBy(0,-100,3f), fadeIn(3f))));
        game.playerMatko.characterImg.setPosition(Matematko.W_WIDTH/2+200, Matematko.W_HEIGHT/2-300);
        game.playerMatko.characterImg.scaleBy(1);

        this.skin.addRegions(game.textureAtlas);
        this.skin.add("default", game.font36);
        this.skin.load(Gdx.files.internal("Fonts & skins/skin.json"));

        createButtons();

        stage.addActor(titleImage);
        stage.addActor(game.playerMatko.characterImg);
    }

    private void createButtons() {
        playButton = new TextButton("Igraj", skin, "default");
        settingButton = new TextButton("Postavke", skin, "default");
        exitButton = new TextButton("Izlaz", skin, "default");

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

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false); //false is for centring camera
    ;}

    @Override
    public void pause() { System.out.println("paused Mainmenu");}

    @Override
    public void resume() { System.out.println("unpasued Mainmenu");}

    @Override
    public void hide() { System.out.println("hidden Mainmenu");}

    @Override
    public void dispose() {
        stage.dispose();
    ;}
}
