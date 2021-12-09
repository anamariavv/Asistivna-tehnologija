package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Matematko extends Game {
	public static final int W_WIDTH = 1680;
	public static final int W_HEIGHT = 1050;
	public OrthographicCamera camera;
	public SpriteBatch batch;
	public BitmapFont font36;
	public Player playerMatko;
	public MainMenuScreen mainScreen;
	public TextureAtlas textureAtlas;

	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1680, 1050); //false->y is up
		batch = new SpriteBatch();
		playerMatko = new Player();
		textureAtlas = new TextureAtlas(Gdx.files.internal("Fonts & skins/skin.atlas"));
		mainScreen = new MainMenuScreen(this);

		createFonts();

		this.setScreen(mainScreen);
	}

	private void createFonts() {
		FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts & skins/Kenney Pixel Square.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();

		params.size = 28;
		params.color = Color.WHITE;
		font36 = fontGenerator.generateFont(params);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font36.dispose();
		mainScreen.dispose();
	}
}
