package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class HUD {
    public Stage stage;
    private Viewport viewport;
    private int coins;
    private Skin skin;
    private Table table;
    private Label coinLabel, coinAmountLabel;
    //add labels and other scene2d elements here (maybe a container containing elements or a table)

    public HUD(SpriteBatch batch, int coins) {
        viewport = new StretchViewport(Matematko.W_WIDTH, Matematko.W_HEIGHT, new OrthographicCamera());
        this.coins = coins;

        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("Fonts & skins/skin.atlas"));
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts & skins/Pixel NES.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        BitmapFont font36 = fontGenerator.generateFont(params);
        params.size = 28;
        params.color = Color.WHITE;

        this.skin = new Skin();
        skin.addRegions(textureAtlas);
        skin.add("default", font36);
        skin.load(Gdx.files.internal("Fonts & skins/skin.json"));

        stage = new Stage(viewport, batch);
        table = new Table();
        coinLabel = new Label("Broj kovanica: ", skin, "default");
        coinAmountLabel = new Label(String.format("%d", coins), skin, "default");

        table.setPosition(Matematko.W_WIDTH/2,0);
        table.add(coinLabel);
        table.add(coinAmountLabel);
    }
}
