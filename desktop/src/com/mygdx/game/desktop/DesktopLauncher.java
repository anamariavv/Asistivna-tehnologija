package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Matematko;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Matematko";
		config.width = Matematko.W_WIDTH;
		config.height = Matematko.W_HEIGHT;
		config.forceExit = false;
		new LwjglApplication(new Matematko(), config);
	}
}
