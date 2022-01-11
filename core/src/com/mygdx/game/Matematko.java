package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

public class Matematko extends Game {
	public static final int W_WIDTH = 1680;
	public static final int W_HEIGHT = 1050;
	public OrthographicCamera camera;
	public SpriteBatch batch;
	public BitmapFont font36;
	public Player playerMatko;
	public MainMenuScreen mainScreen;
	public OptionsScreen optionsScreen;
	public GameScreen gameScreen;
	public TextureAtlas textureAtlas;
	public Music currentMusic;
	public Skin skin;
	public Array<Sound> sfx;
	public static float musicVolume = 0.9f;
	public static float sfxVolume = 0.5f;
	public static MapManager mapManager;

	/* Todo
	*   change some of the fields to static and clean up code */

	@Override
	public void create () {
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		textureAtlas = new TextureAtlas(Gdx.files.internal("Fonts & skins/skin.atlas"));
		mainScreen = new MainMenuScreen(this);
		optionsScreen = new OptionsScreen(this);
		gameScreen = new GameScreen(this);
		skin = new Skin();
		sfx = new Array<Sound>();
		mapManager = new MapManager();
		playerMatko = new Player();

		addSFX();
		createFonts();

		skin.addRegions(textureAtlas);
		skin.add("default", font36);
		skin.load(Gdx.files.internal("Fonts & skins/skin.json"));

		currentMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/music_4.wav"));
		currentMusic.setLooping(true);
		currentMusic.setVolume(musicVolume);

		this.setScreen(mainScreen);
	}

	private void addSFX() {
		Sound buttonSound = Gdx.audio.newSound(Gdx.files.internal("Sound/button_click_2.wav"));
		Sound startGameSound = Gdx.audio.newSound(Gdx.files.internal(("Sound/start_game.ogg")));

		sfx.add(buttonSound);
		sfx.add(startGameSound);
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
		System.out.println("Matematko disposed");
		batch.dispose();
		font36.dispose();
		mainScreen.dispose();
		optionsScreen.dispose();
		currentMusic.dispose();
		sfx.clear();
		currentMusic.dispose();
	}
}
