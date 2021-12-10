package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Matematko extends Game {
	public static final int W_WIDTH = 1680;
	public static final int W_HEIGHT = 1050;
	public OrthographicCamera camera;
	public SpriteBatch batch;
	public BitmapFont font36;
	public Player playerMatko;
	public MainMenuScreen mainScreen;
	public OptionsScreen optionsScreen;
	public TextureAtlas textureAtlas;
	public Music currentMusic;
	public Skin skin;

	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1680, 1050); //false->y is up
		batch = new SpriteBatch();
		playerMatko = new Player();
		textureAtlas = new TextureAtlas(Gdx.files.internal("Fonts & skins/skin.atlas"));
		mainScreen = new MainMenuScreen(this);
		optionsScreen = new OptionsScreen(this);
		this.skin = new Skin();

		createFonts();

		skin.addRegions(textureAtlas);
		skin.add("default", font36);
		skin.load(Gdx.files.internal("Fonts & skins/skin.json"));

		currentMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/music_4.wav"));
		currentMusic.setLooping(true);

		this.setScreen(mainScreen);
	}

	private void createFonts() {
		FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts & skins/Pixel NES.otf"));
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
		optionsScreen.dispose();
		currentMusic.dispose();
	}
}
