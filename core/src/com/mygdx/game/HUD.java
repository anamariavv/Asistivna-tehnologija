package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class HUD implements Disposable {
    public Stage stage;
    public Viewport viewport;
    public int coins;
    public Skin skin;
    public Label coinText, coinAmountText;
    public TextButton pauseButton;
    public Table table;
    private final Matematko game;

    public HUD(SpriteBatch batch, int coins, final Matematko game) {
        this.game = game;
        viewport = new StretchViewport(Matematko.W_WIDTH, Matematko.W_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);
        this.coins = coins;

        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("Fonts & skins/skin.atlas"));
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts & skins/Pixel NES.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        BitmapFont font36 = fontGenerator.generateFont(params);
        params.size = 36;
        params.color = Color.WHITE;

        this.skin = new Skin();
        skin.addRegions(textureAtlas);
        skin.add("default", font36);
        skin.load(Gdx.files.internal("Fonts & skins/skin.json"));

        coinText = new Label("Broj kovanica: ", skin, "default");
        coinAmountText = new Label(String.format("%d", coins), skin, "default");
        pauseButton = new TextButton("Pauza", skin, "default");

        coinText.setFontScale(1.5f,1.5f);
        coinAmountText.setFontScale(2f,2f);
        coinText.getStyle().fontColor = Color.WHITE;
        pauseButton.getLabel().setFontScale(1.5f,1.5f);

        pauseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.pauseScreen);
                game.sfx.get(0).play(game.sfxVolume);
                event.handle();
            }
        });

        table = new Table();
        table.setPosition( Matematko.W_WIDTH/2 - table.getWidth(), Matematko.W_HEIGHT-50);
        table.add(coinText).width(250).height(80).padRight(30);
        table.add(coinAmountText).width(50).height(80).padRight(30);
        table.add(pauseButton).width(250).height(80);
        stage.addActor(table);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
